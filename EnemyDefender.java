import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyDefender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyDefender extends Defender
{    
    Enemy me;
    Ally other;
    
    public EnemyDefender(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
        this.me = (Enemy)me;
        this.other = (Ally)other;
        path = "images/Animations/EnemyAnimations/" + name + "Animations/Attack/" + me.weapon + "/";
        critPath = "images/Animations/EnemyAnimations/" + name + "Animations/Crit/" + me.weapon + "/";
    }
    
    public void act() {
        super.act();
    }
    
    public void checkDeath() {
        if (me.health <= 0) {
            die();
            AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
            w.returnWorld.removeEnemy(me);
            other.increaseXp(me.killXp);
        }
    }
    
    public void drawHealthBarAndLabel() {
        super.drawHealthBarAndLabel();
    }
    
    public void animate() {
        super.animate();
    }
}

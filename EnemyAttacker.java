import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DefenderAttacker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyAttacker extends Attacker
{    
    Enemy me;
    Ally other;
    
    public EnemyAttacker(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
        this.me = (Enemy)me;
        this.other = (Ally)other;
        path = "images/Animations/EnemyAnimations/" + name + "Animations/Attack/";
        critPath = "images/Animations/EnemyAnimations/" + name + "Animations/Crit/";
    }
    
    public void act() {
        super.act();
    }
    
    public void checkDeath() {
        if (me.health <= 0) {
            die();
            AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
            w.returnWorld.removeEnemy(me);
            other.increaseXp(me.xpToGive); // increase ally xp
        }
    }
    
    public void animate() {
        super.animate();
    }
}

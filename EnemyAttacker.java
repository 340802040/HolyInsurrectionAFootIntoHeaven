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
        path = "images/Animations/EnemyAnimations/" + className + "Animations/Attack/" + me.weapon + "/";
        critPath = "images/Animations/EnemyAnimations/" + className + "Animations/Crit/" + me.weapon + "/";
    }
    
    public void act() {
        super.act();
    }
    
    public void checkDeath() {
        if (me.health <= 0) {
            die();
            AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
            w.returnWorld.removeEnemy(me);
            if (me.name.equals("The Being")) {
                Dialogue theBeingDying = new Dialogue("images/Text/TheBeingDying/", "clear");
                w.addObject(theBeingDying, 0, 0);
            }
            if (w.returnWorld.enemies.size() == 0) {
                w.returnState = "clear";
                w.returnWorld.saveHighestChapter();
            }
            other.increaseXp(me.killXp);
        }
    }
    
    public void animate() {
        super.animate();
    }
}

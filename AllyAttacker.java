import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AllyAttacker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyAttacker extends Attacker
{    
    Ally me;
    Enemy other;
    
    public AllyAttacker(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
        this.me = (Ally)me;
        this.other = (Enemy)other;
        path = "images/Animations/AllyAnimations/" + name + "Animations/Attack/";
        critPath = "images/Animations/AllyAnimations/" + name + "Animations/Crit/";
    }

    public void act() {
        super.act();
    }

    public void checkDeath() {
        if (me.health <= 0) {
            die();
            AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
            w.returnWorld.removeAlly(me);
        }
    }
    
    public void animate() {
        super.animate();
    }
}

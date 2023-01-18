import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AllyDefender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyDefender extends Defender
{    
    public AllyDefender(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
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
            w.returnWorld.removeAlly((Ally)me);
        }
    }
    
    public void animate() {
        super.animate();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AllyDefender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyDefender extends Defender
{    
    Ally me;
    Enemy other;
    
    public AllyDefender(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
        this.me = (Ally)me;
        this.other = (Enemy)other;
        path = "images/Animations/AllyAnimations/" + className + "Animations/Attack/" + me.weapon + "/";
        critPath = "images/Animations/AllyAnimations/" + className + "Animations/Crit/" + me.weapon + "/";
    }
    
    public void act() {
        super.act();
    }
    
    public void checkDeath() {
        if (me.health <= 0) {
            die();
            AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
            if (me.name.equals("Prodeus") && w.returnWorld instanceof Chapter6) {
                w.returnWorld.saveHighestChapter();
                Greenfoot.setWorld(new Chapter7());
            }
            else if (me.name.equals("Hero") || me.name.equals("Prodeus")) {
                w.returnState = "lost";
            }
            else w.returnWorld.removeAlly(me);
        }
    }
    
    public void animate() {
        super.animate();
    }
}

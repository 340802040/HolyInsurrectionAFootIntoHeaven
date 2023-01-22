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
            w.returnWorld.removeAlly(me);
            if (me.name.equals("Hero") || me.name.equals("Prodeus")) {
                w.returnState = "lost";
            }
        }
    }

    public void animate() {
        super.animate();
    }
}

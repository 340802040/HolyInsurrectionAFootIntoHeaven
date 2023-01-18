import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;

/**
 * Write a description of class AttackAnimationWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimationWorld extends GameWorld
{
    // DATA
    protected int actCount = 0;
    protected BattleWorld returnWorld;
    protected Ally a;
    protected Enemy e;
    protected String attacker_s;
    // IMAGE ACTORS
    protected Image allyHpBg = new Image("AllyHPbg.png");
    protected Image enemyHpBg = new Image("EnemyHPbg.png");
    protected AttackAnimationActor attackerActor, defenderActor;
    
    public AttackAnimationWorld(BattleWorld returnWorld, Ally a, Enemy e, String attacker_s) {
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        this.a = a;
        this.e = e;
        this.attacker_s = attacker_s;
        returnWorld.state = "attack animation";
        setBackground("BattleBackground.png");
        
        setupHpBgs();
        
        if (attacker_s == "ally") {
            attackerActor = new AllyAttacker(a, e);
            defenderActor = new EnemyDefender(e, a);
        }
        else {
            attackerActor = new EnemyAttacker(e, a);
            defenderActor = new AllyDefender(a, e);
        }
        addObject(attackerActor, getWidth() / 2, getHeight() / 2);
        addObject(defenderActor, getWidth() / 2, getHeight() / 2);
    }

    public void act() {
        checkFightFinished();
    }

    public void setupHpBgs() {
        if (attacker_s == "ally") {
             addObject(allyHpBg, getWidth() / 4, getHeight() - allyHpBg.getImage().getHeight() / 2);
             addObject(enemyHpBg, getWidth() / 4 * 3, getHeight() - enemyHpBg.getImage().getHeight() / 2);
         }
         else {
             addObject(enemyHpBg, getWidth() / 4, getHeight() - enemyHpBg.getImage().getHeight() / 2);
             addObject(allyHpBg, getWidth() / 4 * 3, getHeight() - allyHpBg.getImage().getHeight() / 2);
         }
    }
    
    public static int calculateDamageDealtBy(BattleWorldCharacter dealer, BattleWorldCharacter dealtTo) {
        int weaponDmg = 0;
        int damageDealt = (int)((dealer.atk + weaponDmg) * dealer.terrainMultiplier * GameWorld.getWeaponMultiplier(dealer.weapon, dealtTo.weapon) - dealtTo.def);

        return damageDealt;
    }

    public AttackAnimationActor getOtherActor(AttackAnimationActor a) {
        if (a == attackerActor) {
            return defenderActor;
        }
        else return attackerActor;
    }

    public void checkFightFinished() {
        if (attackerActor.finished && defenderActor.finished) {
            Greenfoot.delay(50);
            returnToWorld();
        }
    }

    public void returnToWorld() {
        returnWorld.state = "gameplay";
        Greenfoot.setWorld(returnWorld);
    }
}
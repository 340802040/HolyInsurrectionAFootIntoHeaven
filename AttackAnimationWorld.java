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
    protected String returnState = "gameplay";
    // IMAGE ACTORS
    protected Image allyHpBg = new Image("AllyHPbg.png");
    protected Image enemyHpBg = new Image("EnemyHPbg.png");
    protected AttackAnimationActor attackerActor, defenderActor;
    // MISC
    protected SimpleTimer timer = new SimpleTimer();
    protected boolean timerMarked;

    public AttackAnimationWorld(BattleWorld returnWorld, Ally a, Enemy e, String attacker_s) {
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        this.a = a;
        this.e = e;
        this.attacker_s = attacker_s;
        setBackground(Images.imgs2.get("attack animation world bg"));
        setup();
    }

    public void act() {
        checkFightFinished();
    }

    public void setup() {
        setupHpBgs(); // setup first so bg's are underneath labels and bars
        setupActors();
    }

    public void setupActors() {
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

    public void setupHpBgs() {
        addObject(allyHpBg, getWidth() / 4, getHeight() - allyHpBg.getImage().getHeight() / 2);
        addObject(enemyHpBg, getWidth() / 4 * 3, getHeight() - enemyHpBg.getImage().getHeight() / 2);
    }

    public static int calculateDamageDealtBy(BattleWorldCharacter dealer, BattleWorldCharacter dealtTo, boolean willCrit) {
        int weaponDmg = 0;
        int damageDealt = (int)((dealer.atk + weaponDmg) * (willCrit ? 3 : 1) * dealer.terrainMultiplier * GameWorld.getWeaponMultiplier(dealer.weapon, dealtTo.weapon) - dealtTo.def);
        if (damageDealt <= 0) {
            damageDealt = 1; // minimum
        }

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
            if (!timerMarked) {
                timer.mark();
                timerMarked = true;

                Font font = new Font("Candara", true, false, 50);
                String msg = a.getLevelUpMsg();
                StatWindow sw = new StatWindow(msg, font, Color.YELLOW, Color.BLACK, 240, null);
                addObject(sw, getWidth() / 2, getHeight() / 2);
            }
            else if (timer.millisElapsed() > 2000) {
                returnToWorld();    
            }
        }
    }

    public void returnToWorld() {
        returnWorld.state = returnState;
        if (returnState.equals("lost")) {
            Greenfoot.setWorld(new GameOverWorld());
        }
        else Greenfoot.setWorld(returnWorld);
        
        attackerActor.resetTransparency();
        defenderActor.resetTransparency();
    }
}
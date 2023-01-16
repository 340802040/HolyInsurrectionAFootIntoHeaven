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
    private int actCount = 0;
    private BattleWorld returnWorld;
    private Ally a;
    private Enemy e;
    private String attacker_s;
    private BattleWorldCharacter attacker, defender;
    private int numAttackerFrames, numAttackerCritFrames, numDefenderFrames, numDefenderCritFrames; 
    private boolean attackerWillHit, defenderWillHit;
    private boolean attackerWillCrit, defenderWillCrit;
    private int attackerFrameOfImpact, defenderFrameOfImpact;
    // FRAMES AND ANIMATION
    private ArrayList<GreenfootImage> attackerFrames = new ArrayList<GreenfootImage>();
    private ArrayList<GreenfootImage> defenderFrames = new ArrayList<GreenfootImage>();
    private int attack_i = 0, defend_i = 0; // indexes for aniamtion
    private boolean attackerFinished, defenderFinished; // whether attack and defense animation has finished
    private boolean framesInitialized = false;
    // IMAGE ACTORS
    private Image allyHpBg = new Image("AllyHPbg.png");
    private Image enemyHpBg = new Image("EnemyHPbg.png");
    private Image attackerActor, defenderActor;
    // ESSENTIAL STRINGS
    private String attackerName, defenderName;
    private String attackerPath, attackerCritPath, defenderPath, defenderCritPath; // path to folder with attack frames
    // HEALTH BARS AND LABELS
    private Image tick = new Image("health-tick.png");
    private Image greyTick = new Image("grey-health-tick.png"); 
    private Font font = new Font("Candara", true, false, 70);
    private Text attackerHpLabel, defenderHpLabel;
    private ArrayList<Image> attackerTicks = new ArrayList<Image>();
    private ArrayList<Image> defenderTicks = new ArrayList<Image>();

    public AttackAnimationWorld(BattleWorld returnWorld, Ally a, Enemy e, String attacker_s) {
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        this.a = a;
        this.e = e;
        this.attacker_s = attacker_s;
        returnWorld.state = "attack animation";
        setBackground("BattleBackground.png");

        // setup attacker and defender, their paths, and their starting images
        if (attacker_s == "ally") {
            attacker = a;
            defender = e;
            attackerName = attacker.getClass().getSimpleName(); // ally classes don't start with "Ally"
            defenderName = defender.getClass().getSimpleName();
            attackerPath = "images/Animations/AllyAnimations/Ally" + attackerName + "Animations/Attack/";
            attackerCritPath = "images/Animations/AllyAnimations/Ally" + attackerName + "Animations/Crit/";
            defenderPath = "images/Animations/EnemyAnimations/" + defenderName + "Animations/Attack/";
            defenderCritPath = "images/Animations/EnemyAnimations/" + defenderName + "Animations/Crit/";
            attackerActor = new Image(attackerPath + "Attack000.png");
            defenderActor = new Image(defenderPath + "Attack000.png");  
        }
        else {
            attacker = e;
            defender = a;
            attackerName = attacker.getClass().getSimpleName(); // enemy classes all start with "Enemy"
            defenderName = defender.getClass().getSimpleName();
            attackerPath = "images/Animations/EnemyAnimations/" + attackerName + "Animations/Attack/";
            attackerCritPath = "images/Animations/EnemyAnimations/" + attackerName + "Animations/Crit/";
            defenderPath = "images/Animations/AllyAnimations/Ally" + defenderName + "Animations/Attack/";
            defenderCritPath = "images/Animations/AllyAnimations/Ally" + defenderName + "Animations/Crit/";
            attackerActor = new Image(attackerPath + "Attack000.png");
            defenderActor = new Image(defenderPath + "Attack000.png");
        }
        defenderActor.getImage().mirrorHorizontally();
        addObject(attackerActor, getWidth() / 2, getHeight() / 2);
        addObject(defenderActor, getWidth() / 2, getHeight() / 2);

        determineWillHit();
        determineWillCrit();
        findFrameOfImpact();
        setupHealthBars();
    }

    public void act() {
        if (actCount == 0) {
            initFrames();
        }
        if (framesInitialized) {
            actCount++;    
            animate();  
        }
    }

    public void initFrames() {
        if (attackerWillCrit) {
            numAttackerCritFrames = new File(attackerCritPath).list().length;
            for (int i = 0; i < numAttackerCritFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                attackerFrames.add(new GreenfootImage(attackerCritPath + "Crit" + zeroes + i + ".png"));
            }
        }
        else {
            numAttackerFrames = new File(attackerPath).list().length;
            for (int i = 0; i < numAttackerFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                attackerFrames.add(new GreenfootImage(attackerPath + "Attack" + zeroes + i + ".png"));
            }    
        }
        if (defenderWillCrit) {
            numDefenderCritFrames = new File(defenderCritPath).list().length;
            for (int i = 0; i < numDefenderCritFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                defenderFrames.add(new GreenfootImage(defenderCritPath + "Crit" + zeroes + i + ".png"));
                defenderFrames.get(i).mirrorHorizontally();
            }
        }
        else {
            numDefenderFrames = new File(defenderPath).list().length;
            for (int i = 0; i < numDefenderFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                defenderFrames.add(new GreenfootImage(defenderPath + "Attack" + zeroes + i + ".png"));
                defenderFrames.get(i).mirrorHorizontally();
            }
        }

        framesInitialized = true;
    }

    public void determineWillHit() {
        int attackerHitChance = attacker.hitChance - defender.ev * 3;
        if (attacker.speed > defender.speed) {
            attackerHitChance += (attacker.speed - defender.speed) * 2;
        }
        attackerWillHit = Greenfoot.getRandomNumber(100) < attackerHitChance ? true : false;

        int defenderHitChance = defender.hitChance - attacker.ev * 3;
        if (defender.speed > attacker.speed) {
            defenderHitChance += (defender.speed - attacker.speed) * 2;
        }
        defenderWillHit = Greenfoot.getRandomNumber(100) < defenderHitChance ? true : false;
    }

    public void determineWillCrit() {
        attackerWillCrit = Greenfoot.getRandomNumber(100) <  attacker.crit ? true : false;
        defenderWillCrit = Greenfoot.getRandomNumber(100) <  defender.crit ? true : false;
    }

    public void setupHealthBars() {
        // setup bg's
        if (attacker_s == "ally") {
            addObject(allyHpBg, getWidth() / 4, getHeight() - allyHpBg.getImage().getHeight() / 2);
            addObject(enemyHpBg, getWidth() / 4 * 3, getHeight() - enemyHpBg.getImage().getHeight() / 2);
        }
        else {
            addObject(enemyHpBg, getWidth() / 4, getHeight() - enemyHpBg.getImage().getHeight() / 2);
            addObject(allyHpBg, getWidth() / 4 * 3, getHeight() - allyHpBg.getImage().getHeight() / 2);
        }
        // setup labels
        if (attacker_s == "ally") {
            attackerHpLabel = new Text(Integer.toString(a.health), font, Color.WHITE, null, false);
            defenderHpLabel = new Text(Integer.toString(e.health), font, Color.WHITE, null, false);
        }
        else {
            attackerHpLabel = new Text(Integer.toString(e.health), font, Color.WHITE, null, false);
            defenderHpLabel = new Text(Integer.toString(a.health), font, Color.WHITE, null, false);
        }
        addObject(attackerHpLabel, 50, getHeight() - allyHpBg.getImage().getHeight() / 2 + 8);
        addObject(defenderHpLabel, 600 + 50, getHeight() - allyHpBg.getImage().getHeight() / 2 + 8);

        // setup health bars
        // max 33 ticks in a row
        // always display max health # of ticks with grey ticks representing missing health 
        // ATTACKER HEALTH BAR
        if (attacker.maxHealth <= 33) {
            // only display 1 row
            for (int i = 0, x = 110, y = getHeight() - allyHpBg.getImage().getHeight() / 2; i < attacker.maxHealth; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                attackerTicks.add(t);
                addObject(t, x, y);
            }
        }
        else {
            // display 2 rows
            int y1 = 700; // top row
            int y2 = y1 + tick.getImage().getHeight() + 8; // bottom row
            // top row - length 33
            for (int i = 0, x = 110; i < 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                attackerTicks.add(t);
                addObject(t, x, y1);
            }
            // bottom row - rest
            for (int i = 0, x = 110; i < attacker.maxHealth - 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                attackerTicks.add(t);
                addObject(t, x, y2);
            }
        }
        // make attacker missing health grey
        for (int i = attacker.maxHealth - 1; i >= attacker.health; i--) {
            attackerTicks.get(i).setImage("grey-health-tick.png");
        }

        // DEFENDER HEALTH BAR
        if (defender.maxHealth <= 33) {
            for (int i = 0, x = 600 + 110, y = getHeight() - allyHpBg.getImage().getHeight() / 2; i < defender.maxHealth; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                defenderTicks.add(t);
                addObject(t, x, y);
            }
        }
        else {
            // top and bottom row y
            int y1 = 700;
            int y2 = y1 + tick.getImage().getHeight() + 8;
            // top row - length 33
            for (int i = 0, x = 600 + 110; i < 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                defenderTicks.add(t);
                addObject(t, x, y1);
            }
            // bottom row - rest
            for (int i = 0, x = 600 + 110; i < defender.maxHealth - 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("health-tick.png");
                defenderTicks.add(t);
                addObject(t, x, y2);
            }
        }
        // make defender missing health grey
        for (int i = defender.maxHealth - 1; i >= defender.health; i--) {
            defenderTicks.get(i).setImage("grey-health-tick.png");
        }
    }

    /**
     * Finds the frame when damage is dealt
     */
    public void findFrameOfImpact() {
        // check attacker possible classes
        if (attacker instanceof Hero) {

        }
        else if (attacker instanceof FootSoldier || attacker instanceof EnemyFootSoldier) {
            attackerFrameOfImpact = attackerWillCrit ? 22 : 18;
        }
        else if (attacker instanceof Cavalry || attacker instanceof EnemyCavalry) {

        }
        else if (attacker instanceof Archer || attacker instanceof EnemyArcher) {

        }
        else if (attacker instanceof Sniper || attacker instanceof EnemySniper) {

        }
        else if (attacker instanceof Wizard || attacker instanceof EnemyWizard) {

        }
        else if (attacker instanceof DivineSorceror || attacker instanceof EnemyDivineSorceror) {

        }
        else if (attacker instanceof Chariot || attacker instanceof EnemyChariot) {

        }
        else if (attacker instanceof Crusader || attacker instanceof EnemyCrusader) {

        }
        else if (attacker instanceof KingsGuard || attacker instanceof EnemyKingsGuard) {

        }
        else if (attacker instanceof ElephantRider || attacker instanceof EnemyElephantRider) {

        }
        else if (attacker instanceof TheBlessedOne) {

        }
        // check defender possibilities
        if (defender instanceof FootSoldier || defender instanceof EnemyFootSoldier) {
            defenderFrameOfImpact = defenderWillCrit ? 22 : 18;
        }
        else if (defender instanceof Cavalry || defender instanceof EnemyCavalry) {

        }
        else if (defender instanceof Archer || defender instanceof EnemyArcher) {

        }
        else if (defender instanceof Sniper || defender instanceof EnemySniper) {

        }
        else if (defender instanceof Wizard || defender instanceof EnemyWizard) {

        }
        else if (defender instanceof DivineSorceror || defender instanceof EnemyDivineSorceror) {

        }
        else if (defender instanceof Chariot || defender instanceof EnemyChariot) {

        }
        else if (defender instanceof Crusader || defender instanceof EnemyCrusader) {

        }
        else if (defender instanceof KingsGuard || defender instanceof EnemyKingsGuard) {

        }
        else if (defender instanceof ElephantRider || defender instanceof EnemyElephantRider) {

        }
    }

    public void cutHp(BattleWorldCharacter wounded) {
        if (wounded == defender) {
            int damageDealt = calculateDamageDealt(wounded);
            // turn health ticks grey
            for (int i = defender.health - 1; i >= defender.health - damageDealt; i--) {
                defenderTicks.get(i).setImage("grey-health-tick.png");
            }
            defender.health -= damageDealt;
        }
        else {
            int damageDealt = calculateDamageDealt(wounded);
            // turn health ticks grey
            for (int i = attacker.health - 1; i >= attacker.health - damageDealt; i--) {
                attackerTicks.get(i).setImage("grey-health-tick.png");
            }
            attacker.health -= damageDealt;
        }
        updateHpLabel();
        checkDeath(wounded);
    }

    public int calculateDamageDealt(BattleWorldCharacter wounded) {
        // damage dealth depends on atk, crit, triangle bonus, and defense
        int damageDealt;
        if (wounded == defender) {
            damageDealt = attacker.atk;
        }
        else {
            damageDealt = defender.atk;
        }

        return damageDealt;
    }

    /**
     * Checks whether a character has died during the battle.
     */
    public void checkDeath(BattleWorldCharacter wounded) {
        if (wounded.health <= 0) {
            // flash?
            if (wounded instanceof Ally) {
                returnWorld.allies.remove(wounded);
                returnWorld.removeObject(wounded);
            }
            else if (wounded instanceof Enemy) {
                returnWorld.enemies.remove(wounded);
                returnWorld.removeObject(wounded);
                a.increaseXp(69);
            }
        }
    }

    public void updateHpLabel() {
        attackerHpLabel.setText(Integer.toString(attacker.health));
        defenderHpLabel.setText(Integer.toString(defender.health));
    }

    public void animate() {
        // attacker has first strike, then defender retaliates
        if (actCount % 5 == 0) {
            if (!attackerFinished) {
                if (attack_i == attackerFrameOfImpact) {
                    if (attackerWillHit) {
                        cutHp(defender);
                    }
                    else {
                        // display "Miss!"
                        Text miss = new Text("Miss!", font, Color.WHITE, null, true);
                        addObject(miss, getWidth() / 2, getHeight() / 2 - 200);
                    }
                }
                attackerActor.setImage(attackerFrames.get(attack_i));
                attack_i++;
                if (attack_i == attackerFrames.size()) {
                    attackerFinished = true;
                    Greenfoot.delay(30);
                }
                attack_i %= attackerFrames.size();
            }
            else if (!defenderFinished) {
                if (defend_i == defenderFrameOfImpact) {
                    if (defenderWillHit) {
                        cutHp(attacker);
                    }
                    else {
                        // display "Miss!"
                        Text miss = new Text("Miss!", font, Color.WHITE, null, true);
                        addObject(miss, getWidth() / 2, getHeight() / 2 - 200);
                    }
                }
                defenderActor.setImage(defenderFrames.get(defend_i));
                defend_i++;
                if (defend_i == defenderFrames.size()) {
                    defenderFinished = true;
                    // PLACEHOLDER - add double attack after
                    Greenfoot.delay(50);
                    returnWorld.state = "gameplay";
                    Greenfoot.setWorld(returnWorld);
                }
                defend_i %= defenderFrames.size();
            }
        }
    }
}

/**
 * if (e instanceof EnemyFootSoldier) {

}
else if (e instanceof EnemyCavalry) {

}
else if (e instanceof EnemyArcher) {

}
else if (e instanceof EnemySniper) {

}
else if (e instanceof EnemyWizard || e instanceof EnemyDivineSorceror) {

}
else if (e instanceof EnemyChariot) {

}
else if (e instanceof EnemyCrusader) {

}
else if (e instanceof EnemyKingsGuard) {

}
else if (e instanceof EnemyElephantRider) {

}
 */

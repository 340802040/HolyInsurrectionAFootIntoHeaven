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
    private double attackerWeaponMultiplier, defenderWeaponMultiplier;
    // FRAMES AND ANIMATION
    private ArrayList<GreenfootImage> attackerFrames = new ArrayList<GreenfootImage>();
    private ArrayList<GreenfootImage> defenderFrames = new ArrayList<GreenfootImage>();
    private int attack_i = 0, defend_i = 0; // indexes for aniamtion
    private boolean framesInitialized = false;
    // IMAGE ACTORS
    private Image allyHpBg = new Image("AllyHPbg.png");
    private Image enemyHpBg = new Image("EnemyHPbg.png");
    private BattleWorldCharacterImage attackerActor, defenderActor;
    // ESSENTIAL STRINGS
    private String attackerName, defenderName;
    private String attackerPath, attackerCritPath, defenderPath, defenderCritPath; // path to folder with attack frames
    // HEALTH BARS AND LABELS
    private Image tick = new Image("HealthTick.png");
    private Image greyTick = new Image("GreyHealthTick.png"); 
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
            attackerActor = new BattleWorldCharacterImage(attackerPath + "Attack000.png");
            defenderActor = new BattleWorldCharacterImage(defenderPath + "Attack000.png");  
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
            attackerActor = new BattleWorldCharacterImage(attackerPath + "Attack000.png");
            defenderActor = new BattleWorldCharacterImage(defenderPath + "Attack000.png");
        }
        attackerActor.isAnimating = true; // attacker starts fight
        defenderActor.getImage().mirrorHorizontally();
        addObject(attackerActor, getWidth() / 2, getHeight() / 2);
        addObject(defenderActor, getWidth() / 2, getHeight() / 2);

        getWeaponMultipliers();
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
            checkFightFinished();
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
    
    public void getWeaponMultipliers() {
        attackerWeaponMultiplier = GameWorld.getWeaponMultiplier(attacker.weapon, defender.weapon);
        defenderWeaponMultiplier = GameWorld.getWeaponMultiplier(defender.weapon, attacker.weapon);
    }

    public void determineWillHit() {
        // ATTACKER
        int attackerHitChance = (int)(attacker.spd * 2 * attackerWeaponMultiplier * attacker.terrainMultiplier - (defender.ev * 3 * defenderWeaponMultiplier * defender.terrainMultiplier) + (attacker.spd - defender.spd > 5 ? 70 : 90));
        attackerWillHit = Greenfoot.getRandomNumber(100) < attackerHitChance ? true :false;

        // DEFENDER
        int defenderHitChance = (int)(defender.spd * 2 * defenderWeaponMultiplier * defender.terrainMultiplier - (attacker.ev * 3 * attackerWeaponMultiplier * attacker.terrainMultiplier) + (defender.spd - attacker.spd > 5 ? 70 : 90));
        defenderWillHit = Greenfoot.getRandomNumber(100) < defenderHitChance ? true : false;
    }

    public void determineWillCrit() {
        attackerWillCrit = Greenfoot.getRandomNumber(100) <  attacker.crit ? true : false;
        defenderWillCrit = Greenfoot.getRandomNumber(100) <  defender.crit ? true : false;
    }

    public void setupHealthBars() {
        // BG's
        if (attacker_s == "ally") {
            addObject(allyHpBg, getWidth() / 4, getHeight() - allyHpBg.getImage().getHeight() / 2);
            addObject(enemyHpBg, getWidth() / 4 * 3, getHeight() - enemyHpBg.getImage().getHeight() / 2);
        }
        else {
            addObject(enemyHpBg, getWidth() / 4, getHeight() - enemyHpBg.getImage().getHeight() / 2);
            addObject(allyHpBg, getWidth() / 4 * 3, getHeight() - allyHpBg.getImage().getHeight() / 2);
        }
        // LABELS
        if (attacker_s == "ally") {
            attackerHpLabel = new Text(Integer.toString(a.health), font, Color.WHITE, null);
            defenderHpLabel = new Text(Integer.toString(e.health), font, Color.WHITE, null);
        }
        else {
            attackerHpLabel = new Text(Integer.toString(e.health), font, Color.WHITE, null);
            defenderHpLabel = new Text(Integer.toString(a.health), font, Color.WHITE, null);
        }
        addObject(attackerHpLabel, 50, getHeight() - allyHpBg.getImage().getHeight() / 2 + 8);
        addObject(defenderHpLabel, 600 + 50, getHeight() - allyHpBg.getImage().getHeight() / 2 + 8);

        // HEALTH BARS
        // max 33 ticks in a row
        // current health relative to max health is displayed
        int y1 = 700; // top row
        int y2 = y1 + tick.getImage().getHeight() + 8; // bottom row
        // ATTACKER HEALTH BAR
        if (attacker.maxHealth <= 33) {
            // only display 1 row
            for (int i = 0, x = 110, y = getHeight() - allyHpBg.getImage().getHeight() / 2; i < attacker.maxHealth; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                attackerTicks.add(t);
                addObject(t, x, y);
            }
        }
        else {            
            // top row - length 33
            for (int i = 0, x = 110; i < 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                attackerTicks.add(t);
                addObject(t, x, y1);
            }
            // bottom row - rest
            for (int i = 0, x = 110; i < attacker.maxHealth - 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                attackerTicks.add(t);
                addObject(t, x, y2);
            }
        }
        // make attacker missing health grey
        for (int i = attacker.maxHealth - 1; i >= attacker.health; i--) {
            attackerTicks.get(i).setImage("GreyHealthTick.png");
        }

        // DEFENDER HEALTH BAR
        if (defender.maxHealth <= 33) {
            for (int i = 0, x = 600 + 110, y = getHeight() - allyHpBg.getImage().getHeight() / 2; i < defender.maxHealth; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                defenderTicks.add(t);
                addObject(t, x, y);
            }
        }
        else {
            // top row - length 33
            for (int i = 0, x = 600 + 110; i < 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                defenderTicks.add(t);
                addObject(t, x, y1);
            }
            // bottom row - rest
            for (int i = 0, x = 600 + 110; i < defender.maxHealth - 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                defenderTicks.add(t);
                addObject(t, x, y2);
            }
        }
        // make defender missing health grey
        for (int i = defender.maxHealth - 1; i >= defender.health; i--) {
            defenderTicks.get(i).setImage("GreyHealthTick.png");
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
        int damageDealt;
        if (wounded == defender) {
            damageDealt = calculateDamageDealtBy(attacker, defender);
            for (int i = defender.health - 1; i >= defender.health - damageDealt; i--) {
                defenderTicks.get(i).setImage("GreyHealthTick.png");
            }
        }
        else {
            damageDealt = calculateDamageDealtBy(defender, attacker);
            for (int i = attacker.health - 1; i >= attacker.health - damageDealt; i--) {
                attackerTicks.get(i).setImage("GreyHealthTick.png");
            }
        }
        wounded.health -= damageDealt;
        updateHpLabels();
        checkDeath(wounded);
    }

    public int calculateDamageDealtBy(BattleWorldCharacter dealer, BattleWorldCharacter dealtTo) {
        int weaponDmg = 0;
        int damageDealt = (int)((dealer.atk + weaponDmg) * dealer.terrainMultiplier * GameWorld.getWeaponMultiplier(dealer.weapon, dealtTo.weapon) - dealtTo.def);

        return damageDealt;
    }

    public void updateHpLabels() {
        attackerHpLabel.setText(Integer.toString(attacker.health));
        defenderHpLabel.setText(Integer.toString(defender.health));
    }

    /**
     * Checks whether a character has died during the battle.
     */
    public void checkDeath(BattleWorldCharacter wounded) {
        if (wounded.health <= 0) {
            // check if wounded was ally or enemy
            if (wounded instanceof Ally) {
                returnWorld.removeAlly(a);
            }
            else if (wounded instanceof Enemy) {
                returnWorld.removeEnemy(e);
                a.increaseXp(69);
            }

            // check if wounded was attacker or defender
            if (wounded == attacker) {
                attackerActor.die();
            }
            else if (wounded == defender) {
                defenderActor.die();
            }
        }
    }

    public void animate() {
        // attacker has first strike, then defender retaliates
        if (actCount % 5 == 0) {
            if (attackerActor.isAnimating) {
                if (attack_i == attackerFrameOfImpact) {
                    if (attackerWillHit) {
                        cutHp(defender);
                    }
                    else {
                        TextCard miss = new TextCard("Miss!", font, Color.WHITE, null);
                        addObject(miss, getWidth() / 2, getHeight() / 2 - 200);
                    }
                }
                attackerActor.setImage(attackerFrames.get(attack_i));
                attack_i++;
                if (attack_i == attackerFrames.size()) {
                    attackerActor.isAnimating = false;
                    if (defenderActor.isDying) {
                        attackerActor.finished = true;
                    }
                    else {
                        defenderActor.isAnimating = true; // switch turn to defender
                    }
                    
                }
                attack_i %= attackerFrames.size();
            }
            else if (defenderActor.isAnimating) {
                if (defend_i == defenderFrameOfImpact) {
                    if (defenderWillHit) {
                        cutHp(attacker);
                    }
                    else {
                        TextCard miss = new TextCard("Miss!", font, Color.WHITE, null);
                        addObject(miss, getWidth() / 2, getHeight() / 2 - 200);
                    }
                }
                defenderActor.setImage(defenderFrames.get(defend_i));
                defend_i++;
                if (defend_i == defenderFrames.size()) {
                    defenderActor.isAnimating = false;
                    defenderActor.finished = true;
                    if (!attackerActor.isDying) {
                        attackerActor.finished = true;
                    }
                }
                defend_i %= defenderFrames.size();
            }
        }
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

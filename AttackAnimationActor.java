import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;

/**
 * Image of a battle world character used in AttackAnimationWorld
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AttackAnimationActor extends Actor
{
    // DATA
    protected int actCount = 0;
    protected BattleWorldCharacter me, other;
    protected String name;
    protected boolean isFlashing, isFading, flip;
    protected boolean isAnimating, isDying, finished; // whether it has finished all its attacks and animations such as dying
    protected int j = 0;
    protected boolean willHit, willCrit;
    protected int frameOfImpact;
    protected double weaponMultiplier;
    // ANIMATION
    protected ArrayList<GreenfootImage> frames = new ArrayList<GreenfootImage>();
    protected ArrayList<GreenfootImage> dmgIndicators = new ArrayList<GreenfootImage>();
    protected Image dmgIndicator;
    protected int i, damageIndicator_i; // indexes for aniamtion
    protected boolean framesInitialized = false;
    // FILE PATHS
    protected String path, critPath;
    // HEALTH BARS AND LABELS
    protected ArrayList<Image> ticks = new ArrayList<Image>();
    protected Image tick = new Image("HealthTick.png");
    protected Image allyHpBg = new Image("AllyHPbg.png");
    protected Image enemyHpBg = new Image("EnemyHPbg.png");
    protected Text hpLabel;
    // MISC
    protected Font font = new Font("Candara", true, false, 70);

    public AttackAnimationActor(BattleWorldCharacter me, BattleWorldCharacter other) {
        this.me = me;
        this.other = other;
        name = me.getClass().getSimpleName();
        weaponMultiplier = getWeaponMultiplier();
        willHit = determineWillHit();
        willCrit = determineWillCrit();
        frameOfImpact = getFrameOfImpact();
        hpLabel = new Text(Integer.toString(me.health), font, Color.WHITE, null);
    }

    public void addedToWorld(World w) {
        initFrames();
    }

    public void act() {
        actCount++;
        if (isAnimating) {
            animate();
        }
        if (isFlashing) {
            flash();
        }
        if (isFading) {
            fade();
        }
    }

    public void initFrames() {
        if (willCrit) {
            int numFrames = new File(path).list().length;
            for (int i = 0; i < numFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                frames.add(new GreenfootImage(critPath + "Crit" + zeroes + i + ".png"));
                if (this instanceof Defender) {
                    frames.get(i).mirrorHorizontally();
                }
            }    
        }
        else {
            int numFrames = new File(path).list().length;
            for (int i = 0; i < numFrames; i++) {
                String zeroes = i < 10 ? "00" : "0";
                frames.add(new GreenfootImage(path + "Attack" + zeroes + i + ".png"));
                if (this instanceof Defender) {
                    frames.get(i).mirrorHorizontally();
                }
            }
        }
        
        setImage(frames.get(0));
    }

    public double getWeaponMultiplier() {
        return GameWorld.getWeaponMultiplier(me.weapon, other.weapon);
    }

    public boolean determineWillHit() {
        int hitChance = (int)(me.spd * 2 * weaponMultiplier * me.terrainMultiplier - (other.ev * 3 * GameWorld.getWeaponMultiplier(other.weapon, me.weapon) * other.terrainMultiplier) + (me.spd - other.spd > 5 ? 70 : 90));
        return Greenfoot.getRandomNumber(100) < hitChance;
    }

    public boolean determineWillCrit() {
        return Greenfoot.getRandomNumber(100) <  me.crit;
    }
    
    public int getFrameOfImpact() {
        if (me instanceof AllyHero) {

        }
        else if (me instanceof AllyFootSoldier || me instanceof EnemyFootSoldier) {
            return willCrit ? 22 : 18;
        }
        else if (me instanceof AllyCavalry || me instanceof EnemyCavalry) {

        }
        else if (me instanceof AllyArcher || me instanceof EnemyArcher) {

        }
        else if (me instanceof AllySniper || me instanceof EnemySniper) {

        }
        else if (me instanceof AllyWizard || me instanceof EnemyWizard) {

        }
        else if (me instanceof AllyDivineSorceror || me instanceof EnemyDivineSorceror) {

        }
        else if (me instanceof AllyChariot || me instanceof EnemyChariot) {

        }
        else if (me instanceof AllyCrusader || me instanceof EnemyCrusader) {

        }
        else if (me instanceof AllyKingsGuard || me instanceof EnemyKingsGuard) {

        }
        else if (me instanceof AllyElephantRider || me instanceof EnemyElephantRider) {

        }
        else if (me instanceof AllyTheBlessedOne) {

        }
        
        return -1;
    }

    public void cutHp() {
        int damageDealt = AttackAnimationWorld.calculateDamageDealtBy(other, me);
        for (int i = me.health - 1; i >= me.health - damageDealt; i--) {
            ticks.get(i).setImage("GreyHealthTick.png");
        }
        me.health -= damageDealt;
        hpLabel.setText(Integer.toString(me.health));
        checkDeath();
    }

    public abstract void checkDeath();

    public abstract void animate();

    public void die() {
        isFlashing = true;
        isDying = true;
    }

    public void flash() {
        if (j == 7) {
            isFlashing = false;
            isFading = true;
        }
        if (actCount % 10 == 0) {
            if (flip) {
                getImage().setTransparency(100);    
            }
            else {
                getImage().setTransparency(255);    
            }
            flip = !flip;
            j++;
        }
    }

    public void fade() {
        int newTrans = getImage().getTransparency() - 5;
        if (newTrans <= 0) {
            isFading = false;
            finished = true;
        }
        if (actCount % 3 == 0 && newTrans >= 0) {
            getImage().setTransparency(newTrans);
        }
    }
}

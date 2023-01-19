import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;

/**
 * Write a description of class GameWorldCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleWorldCharacter extends Actor
{    
    // DATA
    protected int r, c;
    protected int moveLimit;
    protected int level = 0;
    protected List<String> stats = Arrays.asList("health", "atk", "def", "ev", "spd");
    protected int maxHealth, health;
    protected int atk; // base atk 
    protected int def;
    protected int ev; // evasion - each evasion reduces opponent's chance of hitting by 3%
    protected int spd; // each speed you have greater than your enemy, the hit chance increases by 2%, if speed is greater than opponent's by 4, double attack
    protected int terrainMultiplier;
    protected int crit; // crit chance
    protected ArrayList<String> weapons = new ArrayList<String>();
    protected String weapon = "";
    // MOVEMENT
    protected boolean isMoving = false;
    protected boolean moved = false; // whether has been moved already
    protected int i; // index for path
    protected SimpleTimer moveTimer = new SimpleTimer();
    protected Point prevLocation; 
    // PATH FINDING
    protected ArrayList<Point> path = new ArrayList<Point>();
    protected boolean pathPossible;
    protected int[][] map;
    // ANIMATIONS
    protected ArrayList<GreenfootImage> walkFrames = new ArrayList<GreenfootImage>();
    protected ArrayList<GreenfootImage> idleFrames = new ArrayList<GreenfootImage>();
    protected int idle_i = 0, walk_i = 0;
    protected String facing; // which direction they are facing
    // MISC
    protected int actCount = 0;

    public abstract void move();

    public BattleWorldCharacter() {
        terrainMultiplier = 1; // most characters have no terrain bonus
    }

    public void addedToWorld(World w) {
        updateCoords();
        map = ((GameWorld)getWorld()).getMap();
        initializeFrames();
        setImage(idleFrames.get(0));
    }

    public void act() {
        actCount++;
        updateCoords();
    }

    public void initializeFrames() {        
        String path = "";
        if (this instanceof Ally) {
            path = "Animations/AllyAnimations/" + this.getClass().getSimpleName() + "Animations/";
        }
        else if (this instanceof Enemy) {
            path = "Animations/EnemyAnimations/" + this.getClass().getSimpleName() + "Animations/";
        }

        // IDLE
        for (int i = 0; i < 2; i++) {
            idleFrames.add(new GreenfootImage(path + "Idle/Idle00" + i + ".png"));
        }

        // WALK
        for (int i = 0; i < 2; i++) {
            walkFrames.add(new GreenfootImage(path + "Walk/Walk00" + i + ".png"));
        }
    }

    /**
     * Constantly updates the coordinates (r and c) of game world character as it moves.
     */
    public void updateCoords() {
        r = GameWorld.getXCell(getY());
        c = GameWorld.getYCell(getX());
    }

    /**
     * Always animates character facing right (original direction of images).
     */
    public void idleAnimate() {
        if (actCount % 25 == 0) {
            GreenfootImage frame = new GreenfootImage(idleFrames.get(idle_i));
            if (moved) {
                frame.setTransparency(100);
            }
            
            setImage(frame);
            idle_i++;
            idle_i %= idleFrames.size();
        }
    }

    public void walkAnimate() {
        if (actCount % 10 == 0) {
            GreenfootImage frame = new GreenfootImage(walkFrames.get(walk_i));
            if (facing == "left") {
                frame.mirrorHorizontally();
            }
            
            setImage(frame);
            walk_i++;
            walk_i %= walkFrames.size();  
        }
    }

    /**
     * Checks current direction the character is moving.
     */
    public void checkDirection() {
        if (c - prevLocation.c > 0) {   
            facing = "right";
        }
        else if (c - prevLocation.c < 0) {
            
            facing = "left";
        }
    }
    
    public void checkHealth() {
        if (maxHealth > 66) {
            maxHealth = 66;
        }
        if (health > 66) {
            health = 66;
        }
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

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
    protected int maxHealth, health;
    protected int atk; // base atk 
    protected int def;
    protected int ev; // evasion - each evasion reduces opponent's chance of hitting by 3%
    protected int speed; // each speed you have greater than your enemy, the hit chance increases by 2%, if speed is greater than opponent's by 4, double attack
    protected int hitChance;
    protected int crit; // crit chance
    protected String weapon;
    // MOVEMENT
    protected boolean isMoving = false;
    protected boolean moved = false; // whether has been moved already
    protected int i; // index for path
    protected SimpleTimer moveTimer = new SimpleTimer();
    // PATH FINDING
    protected ArrayList<Point> path = new ArrayList<Point>();
    protected boolean pathPossible;
    protected int[][] map;
    // MISC
    protected int actCount = 0;
    
    public abstract void move();
    
    public BattleWorldCharacter() {
        // placeholder values for testing
        hitChance = 100;
    }
    
    public void addedToWorld(World w) {
        r = GameWorld.getYCell(getY());
        c = GameWorld.getXCell(getX());
        map = ((GameWorld)getWorld()).getMap();
    }
    
    public void act() {
        actCount++;
        updateCoords();
    }
    
    /**
     * Constantly updates the coordinates (r and c) of game world character as it moves.
     */
    public void updateCoords() {
        r = GameWorld.getXCell(getY());
        c = GameWorld.getYCell(getX());
    }
    
    public int getR() {
        return r;
    }
    
    public int getC() {
        return c;
    }
}

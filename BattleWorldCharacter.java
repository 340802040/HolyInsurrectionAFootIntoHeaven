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
    protected int speed; // move limit
    protected int health;
    protected int damage;
    protected int critChance;
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

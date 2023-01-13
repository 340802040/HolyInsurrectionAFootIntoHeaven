import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorldCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleWorldCharacter extends Actor
{
    protected int actCount = 0;
    protected int r, c;
    protected int[][] map;
    
    public BattleWorldCharacter() {
        
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

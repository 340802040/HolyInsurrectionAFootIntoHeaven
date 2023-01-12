import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorldCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorldCharacter extends Actor
{
    private int r, c;
    
    public GameWorldCharacter() {
        
    }
    
    public void addedToWorld(World w) {
        r = GameWorld.getYCell(getY());
        c = GameWorld.getXCell(getX());
    }
    
    public void act() {
        
    }
    
    public int getR() {
        return r;
    }
    
    public int getC() {
        return c;
    }
}

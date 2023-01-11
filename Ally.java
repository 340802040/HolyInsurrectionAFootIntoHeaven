import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ally extends Actor
{
    protected int r, c; // row and column player is currently on
    protected SimpleTimer moveTimer;
    
    public Ally() {
        
    }
    
    public void act() {
    
    }
    
    public void checkMovement() {
        if (moveTimer.millisElapsed() > 75) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            
            moveTimer.mark();
        }
    }
    
    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < Overworld.GRID_HEIGHT && c >= 0 && c < Overworld.GRID_WIDTH;
    }
}

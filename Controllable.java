import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Controllable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controllable extends Actor
{
    protected int r, c; // row and column
    protected SimpleTimer moveTimer = new SimpleTimer();
    
    
    public Controllable() {
        
    }
    
    public void act() {
    
    }
    
    public void checkWASDMovement() {
        if (moveTimer.millisElapsed() > 150) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                }
            }
            else if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                }
            }
            
            moveTimer.mark();
        }
    }

    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH;
    }
    
    public void animateWalkCharacter() {
        if(animationTimer.millisElapsed() < 90) {
            return;
        }
        animationTimer.mark();

        setImage(walk[imageIndex]);
        imageIndex = (imageIndex + 1) % walk.length;
    }
}

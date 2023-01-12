import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Selector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selector extends Actor
{
    
    public Selector() {
        setImage("selector.png");
    }
    
    public void act() {
        checkMovement();
    }
    
    public void checkMovement() {
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
}

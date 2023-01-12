import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ally extends Controllable
{
    protected boolean selected;

    public Ally() {
        selected = false;
    }

    public void act() {
        if (selected) {
            checkWASDMovement();
        }
    }
    
    public void checkWASDMovement() {
        if (moveTimer.millisElapsed() > 150) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                    setRotation(0);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                    setRotation(270);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                    setRotation(180);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(GameWorld.getXCoordinate(c), GameWorld.getYCoordinate(r));
                    setRotation(90);
                    animateWalkCharacter();
                }
            }

            moveTimer.mark();
        }
    }
}

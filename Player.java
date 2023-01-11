import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int r, c; // row and column player is currently on
    private SimpleTimer moveTimer;

    public Player() {
        r = 0;
        c = 0;
        moveTimer = new SimpleTimer();
    }

    public void act() {
        checkMovement();
    }

    public void checkMovement() {
        if (moveTimer.millisElapsed() > 75) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                }
            }
            
            moveTimer.mark();
        }
    }
    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < Overworld.getGridHeight() && c >= 0 && c < Overworld.getGridWidth();
    }
}

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
}

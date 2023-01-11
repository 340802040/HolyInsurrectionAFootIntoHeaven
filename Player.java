import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Ally
{
    public Player() {
        r = 0;
        c = 0;
        moveTimer = new SimpleTimer();
    }

    public void act() {
        checkMovement();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverWorldPlayer extends Ally
{
    public OverWorldPlayer() {
        r = 0;
        c = 0;
        moveTimer = new SimpleTimer();
        
        for(int i = 0; i < 7; i++) {
            walk[i] = new GreenfootImage("images/Animations/PlayerOverworld/PlayerO0" + i + ".png");
        }
    }

    public void act() {
        checkWASDMovement();
    }
}

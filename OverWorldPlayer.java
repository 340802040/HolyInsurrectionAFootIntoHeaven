import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverworldPlayer extends Ally
{
    private SimpleTimer animationTimer = new SimpleTimer();
    protected GreenfootImage[] walkFrames = new GreenfootImage[7];
    private int imageIndex = 0;
    
    public OverworldPlayer() {
        r = 0;
        c = 0;
        moveTimer = new SimpleTimer();        

        for(int i = 0; i < 7; i++) {
            walkFrames[i] = new GreenfootImage("images/Animations/PlayerOverworld/PlayerO0" + i + ".png");
        }
        
        setImage("images/Animations/PlayerOverWorld/PlayerO00.png");
    }

    public void act() {
        checkWASDMovement();
    }
}

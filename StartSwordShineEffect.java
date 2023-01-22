import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class StartSwordShineEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartSwordShineEffect extends Actor
{
    private SimpleTimer animationTimer = new SimpleTimer();
    private SimpleTimer timer = new SimpleTimer();
    protected ArrayList<GreenfootImage> frames = Images.imgs.get("start sword shine");
    private boolean marked;
    private int imageIndex = 0;
    
    public StartSwordShineEffect() {
        setImage("images/Animations/ShineAnimations/Shine000.png");
    }
    
    public void act() {
        if (!marked) {
            timer.mark();
            marked = true;
        }
        animateShine();
        if (timer.millisElapsed() > 2050) {
            Greenfoot.setWorld(new CutsceneWorld());
        }
    }
    
    public void animateShine() {
        if (animationTimer.millisElapsed() < 150) {
            return;
        }
        animationTimer.mark();
        setImage(frames.get(imageIndex));
        imageIndex = (imageIndex + 1) % frames.size();
    }
}

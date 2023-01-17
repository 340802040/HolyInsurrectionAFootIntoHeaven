import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Cutscenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cutscene extends World
{
    private SimpleTimer animationTimer = new SimpleTimer();
    private SimpleTimer timer = new SimpleTimer();
    protected GreenfootImage[] cutscenes = new GreenfootImage[50];
    private int imageIndex = 0;
    private boolean marked = false;
    
    public Cutscene() {
        super(1200, 800, 1);
        
        for(int i = 0; i < 50; i++) {
            if(i >= 10) {
                cutscenes[i] = new GreenfootImage("images/Cutscenes/Cutscene0" + i + ".png");
            }
            else {
                cutscenes[i] = new GreenfootImage("images/Cutscenes/Cutscene00" + i + ".png");
            }
        }
        
        setBackground("images/Cutscenes/Cutscene000.png");
    }
    
    public void act()
    {
        if(!marked) {
            timer.mark();
            marked = true;
        }
        animateCutscene();
        if(timer.millisElapsed() > 7500) {
            setBackground("images/Cutscenes/Cutscene049.png");
        }
    }
    
    public void animateCutscene() {
        if(animationTimer.millisElapsed() < 150) {
            return;
        }
        animationTimer.mark();

        setBackground(cutscenes[imageIndex]);
        imageIndex = (imageIndex + 1) % cutscenes.length;
    }
}

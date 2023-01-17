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
    private int actCount = 0;
    private int mod; // affects speed of animation
    protected GreenfootImage[] cutscenes = new GreenfootImage[50];
    private int imageIndex = 0;
    private boolean animationFinished;
    private Dialogue d;
    
    public Cutscene() {
        super(1200, 800, 1);
        
        // init cutscene frames and dialogue text
        for (int i = 0; i < 50; i++) {
            if (i >= 10) {
                cutscenes[i] = new GreenfootImage("images/Cutscenes/Cutscene0" + i + ".png");
            }
            else {
                cutscenes[i] = new GreenfootImage("images/Cutscenes/Cutscene00" + i + ".png");
            }
        }
        d = new Dialogue("images/Text/Intro/");
        
        setBackground("images/Cutscenes/Cutscene000.png");
    }
    
    public void act() {
        actCount++;
        if (!animationFinished) {
            animateCutscene();    
        }
        checkDialogueFinished();
    }
    
    public void animateCutscene() {
        if (imageIndex <= 25) {
            mod = 27;
        }
        else {
            mod = 10;
        }
        if (actCount % mod == 0 && imageIndex < cutscenes.length) {
            setBackground(cutscenes[imageIndex]);
            imageIndex++;
            if (imageIndex == cutscenes.length) {
                animationFinished = true;
                addObject(d, 0, 0);
            }
            imageIndex %= cutscenes.length;
        }
    }
    
    public void checkDialogueFinished() {
        if (d.i == d.dialogues.size()) {
            Greenfoot.setWorld(new Tutorial());
        }
    }
}

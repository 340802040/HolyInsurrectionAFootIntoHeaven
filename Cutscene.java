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
    private ArrayList<Image> dialogues = new ArrayList<Image>();
    private Image curDialogue;
    private int dialogue_i = 0;
    
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
        for (int i = 0; i < 6; i++) {
            dialogues.add(new Image("Text/Intro/Text" + i + ".png"));
        }
        
        setBackground("images/Cutscenes/Cutscene000.png");
    }
    
    public void act() {
        actCount++;
        if (!animationFinished) {
            animateCutscene();    
        }
        else {
            displayDialogues();
        }
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
            }
            imageIndex %= cutscenes.length;
        }
    }
    
    public void displayDialogues() {
        if (dialogue_i == dialogues.size()) {
            Greenfoot.setWorld(new Tutorial());
            return;
        }
        curDialogue = dialogues.get(dialogue_i);
        if (curDialogue.getWorld() == null) {
            addObject(curDialogue, getWidth() / 2, getHeight() - 150);    
        }
        if (Greenfoot.mouseClicked(curDialogue)) {
            dialogue_i++;
            removeObject(curDialogue);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;

/**
 * Write a description of class Dialogue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dialogue extends Actor
{
    protected ArrayList<Image> dialogues = new ArrayList<Image>();
    private Image curDialogue;
    protected int i = 0;
    
    public Dialogue(String path) {
        int size = new File(path).list().length;
        for (int i = 0; i < size; i++) {
            dialogues.add(new Image(path + "Text" + i + ".png"));
        }
    }
    
    public void addedToWorld(World w) {
        GameWorld gw = (GameWorld)w;
        if (gw instanceof BattleWorld) {
            gw.state = "dialogue";            
        }
    }
    
    public void act() {
        displayDialogues();
    }
    
    public void displayDialogues() {
        if (i == dialogues.size()) {
            GameWorld gw = ((GameWorld)getWorld());
            if (gw instanceof BattleWorld) {
                gw.state = "gameplay";    
            }
            gw.removeObject(this);
            return;
        }
        curDialogue = dialogues.get(i);
        if (curDialogue.getWorld() == null) {
            getWorld().addObject(curDialogue, getWorld().getWidth() / 2, getWorld().getHeight() - 150);    
        }
        if (Greenfoot.mouseClicked(curDialogue)) {
            i++;
            getWorld().removeObject(curDialogue);
        }
    }
}

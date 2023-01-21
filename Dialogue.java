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
    protected SimpleTimer timer = new SimpleTimer();

    public Dialogue(String path) {
        int size = new File(path).list().length;
        for (int i = 0; i < size; i++) {
            dialogues.add(new Image(path + "Text" + i + ".png"));
        }
    }

    public void addedToWorld(World w) {
        if (w instanceof GameWorld) {
            GameWorld gw = (GameWorld)w;
            if (gw instanceof BattleWorld) {
                gw.state = "dialogue";            
            }    
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
        if (Greenfoot.mouseClicked(curDialogue) || (Greenfoot.isKeyDown("k") && timer.millisElapsed() > 300)) {
            timer.mark();
            i++;
            getWorld().removeObject(curDialogue);
        }
    }
}

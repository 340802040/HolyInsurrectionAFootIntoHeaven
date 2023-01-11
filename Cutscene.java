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
    private GifImage cutscene = new GifImage("./gifs/cutscene1.gif");
    private int actCount = 0;
    private List<GreenfootImage> frames = cutscene.getImages();
    private int anim_size = frames.size(), anim_index = 0;
    
    public Cutscene() {    
        super(1200, 800, 1);
    }
    
    public void act() {
        actCount++;
        if (actCount % 1 == 0) {
            setBackground(frames.get(anim_index));
            anim_index++;
            anim_index %= anim_size;
        }
        //setBackground((cutscene.getCurrentImage()));
    }
}

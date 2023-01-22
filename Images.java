import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;

/**
 * Write a description of class Images here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Images extends Actor
{
    protected static final Hashtable<String, ArrayList<GreenfootImage>> imgs = new Hashtable<String, ArrayList<GreenfootImage>>();
    protected static final Hashtable<String, ArrayList<GreenfootImage>> dmgImgs = new Hashtable<String, ArrayList<GreenfootImage>>();
    
    static {
        initDmgImgs();
    }

    public static ArrayList<GreenfootImage> getFrames(String path, boolean willCrit, boolean isAlly) {
        if (imgs.containsKey(path)) {
            return imgs.get(path);
        }
        ArrayList<GreenfootImage> frames = new ArrayList<GreenfootImage>();
        int numFrames = new File(path).list().length;
        for (int i = 0; i < numFrames; i++) {
            String zeroes = i < 10 ? "00" : "0";
            frames.add(new GreenfootImage(path + (willCrit ? "Crit" : "Attack") + zeroes + i + ".png"));
            if (!isAlly) {
                frames.get(i).mirrorHorizontally();
            }
        }
        imgs.put(path, frames);
        return frames;
    }

    public static ArrayList<GreenfootImage> getDmgFrames(String path, boolean willCrit, boolean isAlly) {
        if (dmgImgs.containsKey(path)) {
            return dmgImgs.get(path);
        }
        ArrayList<GreenfootImage> frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i < 8; i++) {
            frames.add(new GreenfootImage(path + (willCrit ? "Crit" : "Attack") + "0" + i + ".png"));
        }   

        dmgImgs.put(path, frames);
        return frames;
    }
    
    public static void initDmgImgs() {
        // ALLY DMG INDICATORS
        ArrayList<GreenfootImage> frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i < 8; i++) {
            frames.add(new GreenfootImage("Animations/DamageAnimations/Attack/Attack0" + i + ".png"));
        }
        dmgImgs.put("ally attack dmg indicators", frames);
        
        frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i < 8; i++) {
            frames.add(new GreenfootImage("Animations/DamageAnimations/Crit/Crit0" + i + ".png"));
        }
        dmgImgs.put("ally crit dmg indicators", frames);
        
        // ENEMY DMG INDICATORS
        frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i < 8; i++) {
            frames.add(new GreenfootImage("Animations/DamageAnimations/Attack/Attack0" + i + ".png"));
            frames.get(i).mirrorHorizontally();
        }
        dmgImgs.put("enemy attack dmg indicators", frames);
        
        frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i < 8; i++) {
            frames.add(new GreenfootImage("Animations/DamageAnimations/Crit/Crit0" + i + ".png"));
            frames.get(i).mirrorHorizontally();
        }
        dmgImgs.put("enemy crit dmg indicators", frames);
    }
}

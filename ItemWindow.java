import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemWindow extends Window
{    
    public ItemWindow(String path, Ally ally) {
        // prolly take in hashmap of items and their quantity
        super(path);
        this.ally = ally;
    }
    
    public void act() {
        
    }
}

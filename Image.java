import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class simply to show an image.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Image extends Actor
{
    public Image() {
        
    }
    
    public Image(String path) {
        setImage(path);
    }
    
    public Image(TextImage ti) {
        setImage(ti);
    }
    
    public void removeSelf() {
        getWorld().removeObject(this);
    }
}

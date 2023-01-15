import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class simply to show an image.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Image extends Actor
{
    public Image(String path) {
        setImage(path);
    }
    
    public void close() {
        getWorld().removeObject(this);
    }
}

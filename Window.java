import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Window extends Image
{
    protected Ally ally; // ally that the window applies to
    
    public Window(String path) {
        super(path);
    }
    
    public void close() {
        getWorld().removeObject(this);
    }
}

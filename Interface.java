import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Interface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Interface extends Image
{
    protected Ally a;
    
    public Interface(String path, Ally a) {
        super(path);
        this.a = a;
    }
    
    public void act() {
        
    }
}

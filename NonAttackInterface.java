import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonAttackInterface extends Interface
{
    protected Ally a; // ally that the window applies to
    
    public NonAttackInterface(String path, Ally a) {
        super(path, a);
        this.a = a;
    }
}

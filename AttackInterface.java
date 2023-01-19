import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AttackInterface extends Interface
{
    protected Enemy e;
    
    public AttackInterface(Ally a, Enemy e) {
        super(a);
        this.e = e;
    }
}

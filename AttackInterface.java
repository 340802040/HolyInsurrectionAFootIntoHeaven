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
    String attacker;
    
    public AttackInterface(String path, Ally a, Enemy e, String attacker) {
        super(path, a);
        this.a = a;
        this.e = e;
        this.attacker = attacker;
    }
}

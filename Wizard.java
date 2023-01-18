import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends Ally
{
    public Wizard() {
        moveLimit = 4;
        weapons.add("Fire");
        weapons.add("Water");
        weapons.add("Ice");
    }
    
    public void act() {
        super.act();
    }
}

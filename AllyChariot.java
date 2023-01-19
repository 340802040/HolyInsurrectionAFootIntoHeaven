import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chariot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyChariot extends Ally
{
    public AllyChariot(String name) {
        super(name);
        moveLimit = 5;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

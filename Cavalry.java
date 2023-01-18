import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cavalry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cavalry extends Ally
{
    public Cavalry() {
        moveLimit = 7;
        weapons.add("Sword");
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cavalry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyCavalry extends Ally
{
    public AllyCavalry(String name) {
        super(name);
        moveLimit = 8;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

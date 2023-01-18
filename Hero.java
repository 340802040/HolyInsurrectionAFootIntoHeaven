import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Ally
{
    public Hero() {
        moveLimit = 4;
        weapons.add("Sword");
        weapons.add("Spear");
        weapons.add("Bow");
        weapons.add("Fire");
        weapons.add("Water");
        weapons.add("Ice");
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyHero extends Ally
{
    public AllyHero(String name) {
        super(name);
        maxHealth = 66;
        health = maxHealth;
        atk = 5;
        moveLimit = 100;
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

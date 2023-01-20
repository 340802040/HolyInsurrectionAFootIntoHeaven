import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyFootSoldier extends Ally
{
    public AllyFootSoldier(String name) {
        super(name);
        maxHealth = health = 30;
        crit = 20;
        moveLimit = 15;
        weapons.add("Spear");
    }   
    
    public void act() {
        super.act();
    }
}

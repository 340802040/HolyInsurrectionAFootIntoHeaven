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
        moveLimit = 15;
        maxHealth = 30;
        health = maxHealth;
        atk = 3;
        crit = 20;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyFootSoldier extends Ally
{
    public AllyFootSoldier() {
        moveLimit = 15;
        maxHealth = 10;
        health = maxHealth;
        atk = 3;
        crit = 20;
        hitChance = 0;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FootSoldier extends Ally
{
    public FootSoldier() {
        moveLimit = 4;
        maxHealth = 5;
        health = maxHealth;
        atk = 3;
        crit = 20;
    }
    
    public void act() {
        super.act();
    }
}

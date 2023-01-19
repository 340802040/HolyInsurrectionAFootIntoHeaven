import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyFootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyFootSoldier extends Enemy
{
    public EnemyFootSoldier(int level) {
        super(level);
        moveLimit = 4;
        maxHealth = 35;
        health = maxHealth;
        atk = 5;
        crit = 20;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

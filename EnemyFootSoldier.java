import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyFootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyFootSoldier extends Enemy
{
    public EnemyFootSoldier() {
        moveLimit = 4;
        maxHealth = 50;
        health = maxHealth;
        atk = 5;
        crit = 20;
    }
    
    public void act() {
        super.act();
    }
}

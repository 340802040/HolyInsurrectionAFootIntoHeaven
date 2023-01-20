import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyCavalry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyCavalry extends Enemy
{
    public EnemyCavalry() {
        super();
        moveLimit = 7;
        weapons.add("Sword");
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyArcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyArcher extends Enemy
{
    public EnemyArcher(boolean isBoss) {
        super(isBoss);
        moveLimit = 4;    
        weapons.add("Bow");
    }
    
    public void act() {
        super.act();
    }
}

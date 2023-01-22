import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyFootSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyFootSoldier extends Enemy
{
    public EnemyFootSoldier(boolean isBoss) {
        super(isBoss);
        moveLimit = 4;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

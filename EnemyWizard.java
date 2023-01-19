import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyWizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyWizard extends Enemy
{
    public EnemyWizard(int level) {
        super(level);
        moveLimit = 4;
        weapons.add("Fire");
        weapons.add("Water");
        weapons.add("Ice");
    }
    
    public void act() {
        super.act();
    }
}

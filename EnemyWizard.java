import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyWizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyWizard extends Enemy
{
    public EnemyWizard(boolean isBoss) {
        super(isBoss);
        moveLimit = isBoss ? 0 : 6;
        weapons.add("Fire");
        weapons.add("Water");
        weapons.add("Ice");
    }
    
    public void act() {
        super.act();
    }
}

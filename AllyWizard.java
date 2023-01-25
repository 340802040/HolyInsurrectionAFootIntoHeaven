import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ally wizard class
 * 
 * @author Patrick Hu
 * @version Jan 2023
 */
public class AllyWizard extends Ally
{
    public AllyWizard(String name) {
        super(name);
        moveLimit = 4;
        weapons.add("Fire");
        weapons.add("Water");
        weapons.add("Ice");
    }
    
    public void act() {
        super.act();
    }
}

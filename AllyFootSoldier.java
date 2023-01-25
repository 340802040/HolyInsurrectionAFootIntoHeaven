import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ally foot soldier class
 * 
 * @author Patrick Hu
 * @version Jan 2023
 */
public class AllyFootSoldier extends Ally
{
    public AllyFootSoldier(String name) {
        super(name);
        moveLimit = 6;
        weapons.add("Spear");
    }   
    
    public void act() {
        super.act();
    }
}

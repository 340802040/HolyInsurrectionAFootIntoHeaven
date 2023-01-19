import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllyArcher extends Ally
{
    public AllyArcher(String name) {
        super(name);
        moveLimit = 4;
        weapons.add("Bow");
    }
    
    public void act() {
        super.act();
    }
}

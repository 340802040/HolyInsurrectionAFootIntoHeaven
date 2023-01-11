import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleWorld extends GameWorld
{
    public BattleWorld(int width, int height, int pixelSize) {    
        super(width, height, pixelSize);
        GameInfo.worldType = "battle world";
    }
}

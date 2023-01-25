import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyCrusader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyCrusader extends EnemyCavalry
{
    public EnemyCrusader(boolean isBoss) {
        super(isBoss);
        moveLimit = isBoss ? 0 : 8;
    }
    
    public void act() {
        super.act();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy Foot Soldier class
 * 
 * @author Patrick Hu 
 * @version Jan 2023
 */
public class EnemyFootSoldier extends Enemy
{
    public EnemyFootSoldier(boolean isBoss) {
        super(isBoss);
        moveLimit = isBoss ? 0 : 4;
        weapons.add("Spear");
    }
    
    public void act() {
        super.act();
    }
}

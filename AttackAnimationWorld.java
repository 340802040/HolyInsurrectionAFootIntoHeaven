import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimationWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimationWorld extends GameWorld
{
    BattleWorld returnWorld;
    
    public AttackAnimationWorld(BattleWorld returnWorld) {
        // prolly also take in the ally and enemy that are fighting to determine which animations to use
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        returnWorld.state = "attack animation";
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(null)) {
            returnWorld.state = "gameplay";
            Greenfoot.setWorld(returnWorld);
        }
    }
}

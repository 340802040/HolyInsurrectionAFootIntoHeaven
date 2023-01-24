import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Visual here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Visual extends GameWorld
{
    GameWorld returnWorld;
    
    public Visual(String path, GameWorld returnWorld) {
        super(1200, 800, 1);
        setBackground(path);
        this.returnWorld = returnWorld;
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("j")) {
            if (returnWorld instanceof BattleWorld) {
                BattleWorld bw = (BattleWorld)returnWorld;
                if (bw.menuWindow != null && bw.menuWindow.getWorld() != null) {
                    bw.menuWindow.timer.mark();
                }
            }
            Greenfoot.setWorld(returnWorld);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndTurnWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndTurnWindow extends NonAttackInterface
{
    public EndTurnWindow(String path, Ally a) {
        super(path, a);
        timer.mark();
    }
    
    public void act() {
        checkUserInput();
        checkGoBack();
    }
        
    public void checkUserInput() {
        if (timer.millisElapsed() > 500 && Greenfoot.isKeyDown("k")) {
            BattleWorld bw = (BattleWorld)getWorld();
            for (Ally ally : bw.allies) {
                ally.moved = true;
                ally.getImage().setTransparency(150);
            }
            
            bw.state = "gameplay";
            removeSelf();
        }
    }
    
    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j")) {
            BattleWorld bw = (BattleWorld)getWorld();
            bw.state = "gameplay";
            removeSelf();
        }
    }
}
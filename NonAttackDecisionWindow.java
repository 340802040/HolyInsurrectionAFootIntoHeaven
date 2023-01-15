import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NonAttackDecisionWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonAttackDecisionWindow extends NonAttackInterface
{
    public NonAttackDecisionWindow(String path, Ally a) {
        super(path, a);
    }
    
    public void act() {
        checkUserInput();
    }
    
    public void checkUserInput() {
        BattleWorld bw = (BattleWorld)getWorld();
        if (Greenfoot.isKeyDown("i")) {
            //close();
        }
        else if (Greenfoot.isKeyDown("j")) {
            bw.state = "gameplay";
            removeSelf();
        }
    }
}

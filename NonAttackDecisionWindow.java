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
        timer.mark();
    }
    
    public void act() {
        checkUserInput();
        checkGoBack();
    }
    
    public void checkUserInput() {
        BattleWorld bw = (BattleWorld)getWorld();
        if (Greenfoot.isKeyDown("x")) { // item
            //close();
        }
        else if (Greenfoot.isKeyDown("c")) { // wait
            bw.state = "gameplay";
            removeSelf();
        }
    }
    
    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j") && timer.millisElapsed() > 500) {
            BattleWorld bw = ((BattleWorld)getWorld());
            int[][] map = bw.getMap();
            a.moved = false;
            a.selectedEnemy = null;
            a.getImage().setTransparency(255);
            map[a.r][a.c] = 0; // clear spot
            map[a.backLocation.r][a.backLocation.c] = 1;
            a.setLocation(GameWorld.getX(a.backLocation.c), GameWorld.getY(a.backLocation.r));
            bw.state = "gameplay";
            removeSelf();
        }
    }
}

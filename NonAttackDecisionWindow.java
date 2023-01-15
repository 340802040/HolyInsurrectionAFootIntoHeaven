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
        checkGoBack();
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
    
    public void checkGoBack() {
        if (Greenfoot.isKeyDown("b")) {
            BattleWorld bw = ((BattleWorld)getWorld());
            int[][] map = bw.getMap();
            a.moved = false;
            map[a.r][a.c] = 0; // clear spot
            bw.alliesMoved--;
            a.getImage().setTransparency(255);
            a.selectedEnemy = null;
            a.setLocation(GameWorld.getX(a.prevLocation.c), GameWorld.getY(a.prevLocation.r));
            bw.state = "gameplay";
            removeSelf();
        }
    }
}

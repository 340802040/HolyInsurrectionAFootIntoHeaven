import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackDecisionWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackDecisionWindow extends AttackInterface
{
    public AttackDecisionWindow(String path, Ally a, Enemy e, String attacker) {
        super(path, a, e, attacker);
        timer.mark();
    }

    public void act() {
        checkUserInput();
        checkGoBack();
    }

    public void checkUserInput() {
        BattleWorld bw = (BattleWorld)getWorld();
        if (Greenfoot.isKeyDown("k")) {
            getWorld().addObject(new WeaponSelectWindow("placeholder/weapon-select-bg.png", a, e, attacker), getWorld().getWidth() - 200, getWorld().getHeight() / 2);
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("i")) {

        }
        else if (Greenfoot.isKeyDown("j")) {
            bw.state = "gameplay";
            removeSelf();
        }
    }

    public void checkGoBack() {
        if (Greenfoot.isKeyDown("b") && timer.millisElapsed() > 500) {
            BattleWorld bw = ((BattleWorld)getWorld());
            bw.alliesMoved--;
            int[][] map = bw.getMap();
            a.moved = false;
            a.selectedEnemy = null;
            a.getImage().setTransparency(255);
            map[a.r][a.c] = 0; // clear spot
            map[a.prevLocation.r][a.prevLocation.c] = 1;
            a.setLocation(GameWorld.getX(a.prevLocation.c), GameWorld.getY(a.prevLocation.r));
            bw.state = "gameplay";
            removeSelf();
        }
    }
}

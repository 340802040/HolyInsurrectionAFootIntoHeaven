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

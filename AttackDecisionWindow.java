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
    }

    public void checkUserInput() {
        BattleWorld bw = (BattleWorld)getWorld();
        if (Greenfoot.isKeyDown("k")) {
            getWorld().addObject(new WeaponSelectWindow("placeholder/weapon-select-bg.png", a, e, attacker), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            close();
        }
        else if (Greenfoot.isKeyDown("i")) {
            //close();
        }
        else if (Greenfoot.isKeyDown("j")) {
            bw.state = "gameplay";
            close();
        }
    }
}

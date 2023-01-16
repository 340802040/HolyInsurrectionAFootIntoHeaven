import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponIcon extends AttackInterface
{
    private String name;
    private WeaponSelectWindow window;
    private boolean borderAdded;
    private Image border = new Image("WeaponIcons/WeaponSelector2.png");

    public WeaponIcon(String path, String name, Ally a, Enemy e, WeaponSelectWindow window, String attacker) {
        super(path, a, e, attacker);
        this.name = name;
        this.window = window;   
    }

    public void act() {
        checkSelect();
        checkHover();
    }

    public void checkSelect() {
        if (Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(border)) {
            BattleWorld bw =     (BattleWorld)getWorld();
            a.weapon = name;
            Greenfoot.setWorld(new AttackAnimationWorld(bw, a, e, attacker));
            window.removeSelf();
        }
    }

    public void checkHover() {
        if(Greenfoot.mouseMoved(this) && !borderAdded) {
            getWorld().addObject(border, getX(), getY());
            borderAdded = true;
        }
        if(Greenfoot.mouseMoved(null) && borderAdded && !Greenfoot.mouseMoved(border) && !Greenfoot.mouseMoved(this)) {
            getWorld().removeObject(border);
            borderAdded = false;
        }
    }

    public void removeSelf() {
        if (borderAdded) {
            getWorld().removeObject(border);
        }
        super.removeSelf();
    }
}

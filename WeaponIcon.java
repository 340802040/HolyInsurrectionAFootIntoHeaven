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
    private Ally ally;
    private Enemy e;
    private WeaponSelectWindow window;

    public WeaponIcon(String path, String name, Ally a, Enemy e, WeaponSelectWindow window, String attacker) {
        super(path, a, e, attacker);
        this.name = name;
        this.window = window;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            BattleWorld bw = (BattleWorld)getWorld();
            a.weapon = name;
            Greenfoot.setWorld(new AttackAnimationWorld(bw, a, e, attacker));
            window.close();
        }
    }
}

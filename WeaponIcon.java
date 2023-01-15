import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponIcon extends Image
{
    private String name;
    private Ally ally;
    private WeaponSelectWindow window;

    public WeaponIcon(String path, String name, Ally ally, WeaponSelectWindow window) {
        super(path);
        this.name = name;
        this.ally = ally;
        this.window = window;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            BattleWorld bw = (BattleWorld)getWorld();
            ally.weapon = name;
            Greenfoot.setWorld(new AttackAnimationWorld(bw));
            window.close();
        }
    }
}

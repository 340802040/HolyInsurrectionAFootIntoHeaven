import greenfoot.*;  // (World, Actor, GreenfootWeaponIcon, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class WeaponSelectWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponSelectWindow extends Window
{
    private WeaponIcon sword;
    private WeaponIcon lance;
    private WeaponIcon bow;
    private WeaponIcon fire;
    private WeaponIcon water;
    private WeaponIcon ice;
    private ArrayList<WeaponIcon> icons = new ArrayList<WeaponIcon>();
    
    public WeaponSelectWindow(String path, Ally ally) {
        // take in the class of ally and then displays list of possible weapons/magic      
        // hovering over each weapon highlights the name of it
        // clicking a weapon gives it to the ally, self destructs all WeaponIcons associated with this class, and sets world to attack animation world
        super(path);
        this.ally = ally;
        sword = new WeaponIcon("placeholder/weapon-icons/sword.jpg", "sword", ally, this);
        lance = new WeaponIcon("placeholder/weapon-icons/lance.png", "lance", ally, this);
        bow = new WeaponIcon("placeholder/weapon-icons/bow.jpg", "bow", ally, this);
        fire = new WeaponIcon("placeholder/weapon-icons/fire.jpg", "fire", ally, this);
        water = new WeaponIcon("placeholder/weapon-icons/water.jpg", "water", ally, this);
        ice = new WeaponIcon("placeholder/weapon-icons/ice.png", "ice", ally, this);
        
        // determine ally's weapons
        if (ally instanceof Hero) {
            
        }
        else if (ally instanceof FootSoldier) {
            icons.add(lance);
        }
        else if (ally instanceof Cavalry) {
            icons.add(sword);
            icons.add(lance);
        }
    }
    
    public void addedToWorld(World w) {
        addWeaponIcons();
    }
    
    public void addWeaponIcons() {
        for (int i = 0, x = 300; i < icons.size(); i++, x += 300) {
            WeaponIcon icon = icons.get(i);
            getWorld().addObject(icon, x, getWorld().getHeight() / 2);
        }
    }
    
    /**
     * Removes itself and all WeaponIcon's from the world.
     */
    public void close() {
        for (WeaponIcon icon : icons) {
            getWorld().removeObject(icon);
        }
        super.close();
    }
}

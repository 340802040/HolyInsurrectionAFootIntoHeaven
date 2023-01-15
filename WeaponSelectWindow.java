import greenfoot.*;  // (World, Actor, GreenfootWeaponIcon, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class WeaponSelectWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponSelectWindow extends AttackInterface
{
    private WeaponIcon sword;
    private WeaponIcon lance;
    private WeaponIcon bow;
    private WeaponIcon fire;
    private WeaponIcon water;
    private WeaponIcon ice;
    private ArrayList<WeaponIcon> icons = new ArrayList<WeaponIcon>();
    
    public WeaponSelectWindow(String path, Ally a, Enemy e, String attacker) {
        // take in the class of ally and then displays list of possible weapons/magic      
        // hovering over each weapon highlights the name of it
        super(path, a, e, attacker);
        sword = new WeaponIcon("placeholder/weapon-icons/sword.jpg", "sword", a, e, this, attacker);
        lance = new WeaponIcon("placeholder/weapon-icons/lance.png", "lance", a, e, this, attacker);
        bow = new WeaponIcon("placeholder/weapon-icons/bow.jpg", "bow", a, e, this, attacker);
        fire = new WeaponIcon("placeholder/weapon-icons/fire.jpg", "fire", a, e, this, attacker);
        water = new WeaponIcon("placeholder/weapon-icons/water.jpg", "water", a, e, this, attacker);
        ice = new WeaponIcon("placeholder/weapon-icons/ice.png", "ice", a, e, this, attacker);
        
        // determine ally's weapons
        if (a instanceof Hero) {
            
        }
        else if (a instanceof FootSoldier) {
            icons.add(lance);
        }
        else if (a instanceof Cavalry) {
            icons.add(sword);
            icons.add(lance);
        }
    }
    
    public void addedToWorld(World w) {
        addWeaponIcons();
    }
    
    public void act() {
        checkGoBack();
    }
    
    public void addWeaponIcons() {
        for (int i = 0, x = 300; i < icons.size(); i++, x += 300) {
            WeaponIcon icon = icons.get(i);
            getWorld().addObject(icon, x, getWorld().getHeight() / 2);
        }
    }
    
    public void checkGoBack() {
        if (Greenfoot.isKeyDown("b")) {
            BattleWorld bw = ((BattleWorld)getWorld());
            bw.addObject(new AttackDecisionWindow("attack-decision-window.png", a, e, "ally"), bw.getWidth() - 250, bw.getHeight() / 2);
            removeSelf();
        }
    }
    
    /**
     * Removes itself and all WeaponIcon's from the world.
     */
    public void removeSelf() {
        for (WeaponIcon icon : icons) {
            getWorld().removeObject(icon);
        }
        super.removeSelf();
    }
}

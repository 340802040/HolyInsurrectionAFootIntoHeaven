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
    private WeaponIcon spear;
    private WeaponIcon bow;
    private WeaponIcon fire;
    private WeaponIcon water;
    private WeaponIcon ice;
    private ArrayList<WeaponIcon> icons = new ArrayList<WeaponIcon>();
    
    public WeaponSelectWindow(String path, Ally a, Enemy e, String attacker) {
        super(path, a, e, attacker);
        setup();
    }
    
    public void addedToWorld(World w) {
        addWeaponIcons();
    }
    
    public void act() {
        checkGoBack();
    }
    
    public void setup() {
        sword = new WeaponIcon("WeaponIcons/Sword.png", "sword", a, e, this, attacker);
        spear = new WeaponIcon("WeaponIcons/Spear.png", "spear", a, e, this, attacker);
        bow = new WeaponIcon("WeaponIcons/Bow.png", "bow", a, e, this, attacker);
        fire = new WeaponIcon("WeaponIcons/Fire.png", "fire", a, e, this, attacker);
        water = new WeaponIcon("WeaponIcons/Water.png", "water", a, e, this, attacker);
        ice = new WeaponIcon("WeaponIcons/Ice.png", "ice", a, e, this, attacker); 
        
        // determine ally's weapons and bg size
        setImage("WeaponIcons/SmallWeaponIconBG.png");
        if (a instanceof Hero) {
            icons.add(sword);
            icons.add(spear);
            icons.add(bow);
            icons.add(fire);
            icons.add(water);
            icons.add(ice);
            setImage("WeaponIcons/BigWeaponIconBG.png");
        }
        else if (a instanceof FootSoldier) {
            icons.add(spear);
        }
        else if (a instanceof Cavalry) {
            icons.add(sword);
            icons.add(spear);
        }
        else if (a instanceof Archer) {
            icons.add(bow);
        }
        else if (a instanceof Sniper) {
            icons.add(bow);
            icons.add(sword);
        }
        else if (a instanceof Wizard || a instanceof DivineSorceror) {
            icons.add(fire);
            icons.add(water);
            icons.add(ice);
        }
        else if (a instanceof Chariot) {
            icons.add(spear);
        }
        else if (a instanceof Crusader) {
            icons.add(sword);
            icons.add(spear);
            icons.add(bow);
        }
        else if (a instanceof KingsGuard) {
            icons.add(sword);
            icons.add(spear);
        }
        else if (a instanceof ElephantRider) {
            icons.add(spear);   
        }
        else if (a instanceof TheBlessedOne) {
            icons.add(sword);
            icons.add(spear);
            icons.add(bow);
            icons.add(fire);
            icons.add(water);
            icons.add(ice);
            setImage("WeaponIcons/BigWeaponIconBG.png");
        }
    }
    
    public void addWeaponIcons() {
        int y = getY() - getImage().getHeight() / 2 + 80 / 2;
        for (int i = 0; i < icons.size(); i++, y += 60) {
            WeaponIcon icon = icons.get(i);
            getWorld().addObject(icon, getX(), y);
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
            icon.removeSelf();
        }
        super.removeSelf();
    }
}

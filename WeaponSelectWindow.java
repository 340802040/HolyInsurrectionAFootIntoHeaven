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
    private Image sword;
    private Image spear;
    private Image bow;
    private Image fire;
    private Image water;
    private Image ice;
    private ArrayList<Image> icons = new ArrayList<Image>();

    public WeaponSelectWindow(String path, Ally a, Enemy e, String attacker) {
        super(path, a, e, attacker);
        setup();
    }

    public void addedToWorld(World w) {
        addWeaponIcons();
    }

    public void act() {
        checkGoBack();
        checkUserInput();
    }

    public void setup() {
        sword = new Image("WeaponIcons/Sword.png");
        spear = new Image("WeaponIcons/Spear.png");
        bow = new Image("WeaponIcons/Bow.png");
        fire = new Image("WeaponIcons/Fire.png");
        water = new Image("WeaponIcons/Water.png");
        ice = new Image("WeaponIcons/Ice.png"); 

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
        int y = getY() - getImage().getHeight() / 2 + 43;
        for (int i = 0; i < icons.size(); i++, y += 60) {
            Image icon = icons.get(i);
            getWorld().addObject(icon, getX(), y);
        }
    }

    public void checkUserInput() {
        if (Greenfoot.isKeyDown("1") && icons.contains(sword)) {
            a.weapon = "Sword";
        }
        else if (Greenfoot.isKeyDown("2") && icons.contains(spear)) {
            a.weapon = "Spear";
        }
        else if (Greenfoot.isKeyDown("3") && icons.contains(bow)) {
            a.weapon = "Bow";
        }
        else if (Greenfoot.isKeyDown("4") && icons.contains(fire)) {
            a.weapon = "Fire";
        }
        else if (Greenfoot.isKeyDown("5") && icons.contains(water)) {
            a.weapon = "Water";
        }
        else if (Greenfoot.isKeyDown("6") && icons.contains(ice)) {
            a.weapon = "Ice";
        }

        if (Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6")) {
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
    }

    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j")) {
            BattleWorld bw = ((BattleWorld)getWorld());
            bw.addObject(new AttackDecisionWindow("attack-decision-window.png", a, e, "ally"), bw.getWidth() - 250, bw.getHeight() / 2);
            removeSelf();
        }
    }

    /**
     * Removes itself and all WeaponIcon's from the world.
     */
    public void removeSelf() {
        for (Image icon : icons) {
            icon.removeSelf();
        }
        super.removeSelf();
    }
}

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
        sword = new Image("Panels/WeaponIcons/Sword.png");
        spear = new Image("Panels/WeaponIcons/Spear.png");
        bow = new Image("Panels/WeaponIcons/Bow.png");
        fire = new Image("Panels/WeaponIcons/Fire.png");
        water = new Image("Panels/WeaponIcons/Water.png");
        ice = new Image("Panels/WeaponIcons/Ice.png"); 

        // determine ally's weapons and bg size
        setImage("Panels/WeaponIcons/SmallWeaponIconBG.png");
        if (a instanceof Hero || a instanceof TheBlessedOne) {
            setImage("Panels/WeaponIcons/BigWeaponIconBG.png");
        }
        for (String weapon : a.weapons) {
            switch (weapon) {
                case "Sword":
                    icons.add(sword);
                    break;
                case "Spear":
                    icons.add(spear);
                    break;
                case "Bow":
                    icons.add(bow);
                    break;
                case "Fire":
                    icons.add(fire);
                    break;
                case "Water":
                    icons.add(water);
                    break;
                case "Ice":
                    icons.add(ice);
                    break;
            }
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
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("2") && icons.contains(spear)) {
            a.weapon = "Spear";
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("3") && icons.contains(bow)) {
            a.weapon = "Bow";
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("4") && icons.contains(fire)) {
            a.weapon = "Fire";
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("5") && icons.contains(water)) {
            a.weapon = "Water";
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
        else if (Greenfoot.isKeyDown("6") && icons.contains(ice)) {
            a.weapon = "Ice";
            Greenfoot.setWorld(new AttackAnimationWorld((BattleWorld)getWorld(), a, e, "ally"));
            removeSelf();
        }
    }

    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j")) {
            BattleWorld bw = ((BattleWorld)getWorld());
            bw.addObject(new AttackDecisionWindow("Panels/AttackDecisionWindow.png", a, e, "ally"), bw.getWidth() - 250, bw.getHeight() / 2);
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

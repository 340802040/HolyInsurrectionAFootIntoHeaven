import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HoverWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HoverWindow extends Image
{
    public HoverWindow(BattleWorldCharacter bwc) {
        GreenfootImage img;
        
        if (bwc instanceof Ally) {
            img = new GreenfootImage("AllyStatBG.png");
        }
        else {
            img = new GreenfootImage("EnemyStatBG.png");
        }
        
        String s = bwc.name + "\n";
        s += "HP - " + bwc.health + "/" + bwc.maxHealth + "\n";
        s += "Weapon - " + (bwc.weapon == "" ? "N/A" : bwc.weapon) + "\n";
        
        Font font = new Font("Candara", true, false, 35);
        TextImage ti = new TextImage(s, font, Color.WHITE, null);
        img.drawImage(ti, img.getWidth() / 2 - ti.getWidth() / 2, img.getHeight() / 2 - ti.getHeight() / 2);
        img.setTransparency(230);
        
        setImage(img);
    }
}

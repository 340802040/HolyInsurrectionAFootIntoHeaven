import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StatWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatWindow extends Image
{    
    
    public StatWindow(BattleWorldCharacter bwc) {
        String stats = "\n" + bwc.name + "\n\n";
        String whitespace = "     ";
        stats += whitespace + "LEVEL - " + bwc.level + whitespace + "\n";
        if (bwc instanceof Ally) {
            Ally a = (Ally)bwc;
            stats += whitespace + "XP - " + a.xp + "/" + a.xpNeeded + whitespace + "\n";
        }
        stats += whitespace + "HP - " + bwc.health + "/" + bwc.maxHealth + whitespace + "\n";
        stats += whitespace + "ATK - " + bwc.atk + whitespace + "\n";
        stats += whitespace + "DEF - " + bwc.def + whitespace + "\n";
        stats += whitespace + "EV - " + bwc.ev + whitespace + "\n";
        stats += whitespace + "SPD - " + bwc.spd + whitespace + "\n";
        stats += whitespace + "Crit Chance - " + bwc.crit + "%" + whitespace + "\n";
        stats += whitespace + "Current Weapon - " + (bwc.weapon == "" ? "Not yet chosen" : bwc.weapon) + whitespace + "\n \n";
        
        Font font = new Font("Candara", true, false, 45);
        Color fontColor = (bwc instanceof Ally) ? Color.WHITE : Color.WHITE;
        Color bgColor = (bwc instanceof Ally) ? Color.BLUE : Color.RED;
        TextImage ti = new TextImage(stats, font, fontColor, bgColor);
        ti.setTransparency(230);
        setImage(ti);
    }
    
    public StatWindow(String s, Font font, Color textColor, Color bgColor) {
        TextImage ti = new TextImage(s, font, textColor, bgColor);
        ti.setTransparency(190);
        setImage(ti);
    }

    public void act() {
        checkGoBack();
    }

    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j")) {
            BattleWorld bw = ((BattleWorld)getWorld());
            bw.state = "gameplay";
            removeSelf();
        }
    }
}

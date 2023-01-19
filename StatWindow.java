import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StatWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatWindow extends NonAttackInterface
{
    private Font font = new Font("Candara", true, false, 45);
    
    public StatWindow(BattleWorldCharacter bwc) {
        super(bwc);
        String stats = "\n" + bwc.name + " Stats \n\n";
        String whitespace = "     ";
        stats += whitespace + "LEVEL - " + bwc.level + whitespace + "\n";
        stats += whitespace + "HP - " + bwc.health + "/" + bwc.maxHealth + whitespace + "\n";
        stats += whitespace + "ATK - " + bwc.atk + whitespace + "\n";
        stats += whitespace + "DEF - " + bwc.def + whitespace + "\n";
        stats += whitespace + "EV - " + bwc.ev + whitespace + "\n";
        stats += whitespace + "SPD - " + bwc.spd + whitespace + "\n";
        stats += whitespace + "Crit Chance - " + bwc.crit + "%" + whitespace + "\n";
        stats += whitespace + "Current Weapon - " + (bwc.weapon == "" ? "Not yet chosen" : bwc.weapon) + whitespace + "\n \n";
        TextImage ti = new TextImage(stats, font, Color.YELLOW, Color.BLACK);
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

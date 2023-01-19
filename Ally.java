import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Ally extends BattleWorldCharacter
{
    protected Enemy selectedEnemy; // whether character has selected an enemy to move to
    protected int xp = 0, xpNeeded = 50;
    protected Point backLocation; // location to go back to
    protected String name;

    public Ally(String name) {
        this.name = name;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        map[r][c] = 1;
    }

    public void act() {
        super.act();
        if (isMoving) {
            move();
            walkAnimate();
        }
        else {
            idleAnimate();
        }
    }

    public void startMoving(ArrayList<Point> path) {
        i = path.size() - 1;
        isMoving = true;
        this.path = path;
        map[r][c] = 0; // clear spot
        backLocation = new Point(r, c); // save location to go back to
    }

    public void move() {
        if (i == -1) { // reached destination
            BattleWorld bw = ((BattleWorld)getWorld());
            isMoving = false;
            moved = true;
            map[r][c] = 1;
            getImage().setTransparency(150);
            if (selectedEnemy != null) {
                bw.state = "decision";
                bw.addObject(new AttackDecisionWindow("Panels/AttackDecisionWindow.png", this, selectedEnemy, "ally"), bw.getWidth() - 250, bw.getHeight() / 2);
            }
            else {
                bw.state = "decision";
                bw.addObject(new NonAttackDecisionWindow("Panels/NonAttackDecisionWindow.png", this), bw.getWidth() - 250, bw.getHeight() / 2);
            }

            return;
        }

        Point p = path.get(i);
        if (moveTimer.millisElapsed() > 200) {
            prevLocation = new Point(r, c);
            setLocation(GameWorld.getX(p.c), GameWorld.getY(p.r));
            updateCoords();
            checkDirection();
            i--;
            moveTimer.mark();
        }
    }

    public TextCard increaseXp(int amount) {
        xp += amount;
        Font font = new Font("Candara", true, false, 50);
        String msg = "XP +" + amount + getLevelUpMsg();
        return new TextCard(msg, font, Color.GREEN, null, 18);
    }

    public String getLevelUpMsg() {
        if (xp >= xpNeeded) {
            int numLevelUps = 0;
            int xpCopy = xp;
            while (true) {
                xpCopy -= xpNeeded;
                if (xpCopy < 0) {
                    break;
                }
                numLevelUps++;
                xpNeeded += 5;
            }
            level += numLevelUps;
            int increase = 2 * numLevelUps;

            int numStatIncreases = GameWorld.getRandomNumberInRange(1, 5);
            List<String> upgradedStats = GameWorld.pickNRandom(stats, numStatIncreases);
            String msg = " --> LEVEL +" + numLevelUps + "\n";
            for (String s : upgradedStats) {
                switch (s) {
                    case "health":
                        msg += "MAX HP +" + increase + "\n";
                        maxHealth += increase + 10000;
                        health += increase + 10000;
                        checkHealth();
                        break;
                    case "atk":
                        msg += "ATK +" + increase + "\n";
                        atk += increase;
                        break;
                    case "def":
                        msg += "DEF +" + increase + "\n";
                        def += increase;
                        break;
                    case "ev":
                        msg += "EV +" + increase + "\n";
                        ev += increase;
                        break;
                    case "spd":
                        msg += "SPD +" + increase + "\n";
                        spd += increase;
                        break;
                }
            }

            return msg;
        }
        else return "";
    }
}

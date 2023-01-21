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
    protected int xp = 0, xpNeeded = 50, xpGained;
    protected Point backLocation; // location to go back to

    public Ally(String name) {
        this.name = name;
        crit = 3;
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
            bw.selector.selectedAlly = null;
            if (selectedEnemy != null) {
                bw.state = "decision";
                bw.addObject(new AttackDecisionWindow(this, selectedEnemy), bw.getWidth() - 250, bw.getHeight() / 2);
            }
            else {
                bw.state = "decision";
                bw.addObject(new NonAttackDecisionWindow(this), bw.getWidth() - 250, bw.getHeight() / 2);
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

    public void increaseXp(int amount) {
        xp += amount;
        xpGained += amount;
    }

    /**
     * Calculates how many levels gained from total xp gained after a fight.
     * If leveled up, increases stats. 
     * 
     * @return String message with all information (xp gained, amount of level ups and stat increases).
     */
    public String getLevelUpMsg() {
        String msg;
        String whitespace = "     ";
        
        if (xp >= xpNeeded) {
            int numLevelUps = 0;
            while (true) {
                if (xp - xpNeeded < 0) {
                    break;
                }
                xp -= xpNeeded;
                numLevelUps++;
                xpNeeded += 20;
            }
            level += numLevelUps;
            int increase = 2 * numLevelUps;

            int numStatIncreases = GameWorld.getRandomNumberInRange(1, potentialStats.size() + 1);
            List<String> upgradedStats = GameWorld.pickNRandom(potentialStats, numStatIncreases);
            msg = "\n" + whitespace + "XP +" + xpGained + " --> LEVEL +" + numLevelUps + whitespace + "\n";
            for (String s : upgradedStats) {
                switch (s) {
                    case "health":
                        msg += whitespace + "MAX HP +" + increase + whitespace + "\n";
                        maxHealth += increase;
                        checkHealth();
                        break;
                    case "atk":
                        msg += whitespace + "ATK +" + increase + whitespace + "\n";
                        atk += increase;
                        break;
                    case "def":
                        msg += whitespace + "DEF +" + increase + whitespace + "\n";
                        def += increase;
                        break;
                    case "ev":
                        msg += whitespace + "EV +" + increase + whitespace + "\n";
                        ev += increase;
                        break;
                    case "spd":
                        msg += whitespace + "SPD +" + increase + whitespace + "\n";
                        spd += increase;
                        break;
                }
            }
            msg += " \n";
            xpGained = 0;
        }
        else {
            msg = "\n" + whitespace + "XP +" + xpGained + whitespace + "\n \n";   
            xpGained = 0;
        }
        
        return msg;
    }
}

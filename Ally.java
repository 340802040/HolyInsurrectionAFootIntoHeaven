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

    public String getLevelUpMsg() {
        String msg;
        
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
            msg = "XP +" + xpGained + " --> LEVEL +" + numLevelUps + "\n";
            for (String s : upgradedStats) {
                switch (s) {
                    case "health":
                        msg += "MAX HP +" + increase + "\n";
                        maxHealth += increase;
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
            xpGained = 0;
        }
        else {
            msg = "XP +" + xpGained;   
            xpGained = 0;
        }
        
        return msg;
    }
}

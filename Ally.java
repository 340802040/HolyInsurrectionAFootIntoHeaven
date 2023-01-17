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
    protected int xp = 0, level = 0, xpNeeded;
    protected Point prevLocation; // for when user wants to go back
    
    public Ally() {
        setImage("placeholder/ally.png");
        xpNeeded = 100; // palceholder
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        map[r][c] = 1;
    }

    public void act() {
        super.act();
        if (isMoving) {
            move();
        }
        checkLevelUp();
    }

    public void startMoving(ArrayList<Point> path) {
        i = path.size() - 1;
        isMoving = true;
        this.path = path;
        map[r][c] = 0; // clear spot
        prevLocation = new Point(r, c); // save previous location
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
                bw.addObject(new AttackDecisionWindow("attack-decision-window.png", this, selectedEnemy, "ally"), bw.getWidth() - 250, bw.getHeight() / 2);
            }
            else {
                bw.state = "decision";
                bw.addObject(new NonAttackDecisionWindow("non-attack-decision-window.png", this), bw.getWidth() - 250, bw.getHeight() / 2);
            }

            return;
        }

        Point p = path.get(i);
        if (moveTimer.millisElapsed() > 80) {
            setLocation(GameWorld.getX(p.c), GameWorld.getY(p.r));
            i--;
            moveTimer.mark();
        }
    }
    
    public void increaseXp(int amount) {
        xp += amount;
    }
    
    public void checkLevelUp() {
        if (xp >= xpNeeded) {
            level++;
            xp = 0;
            xpNeeded += 10; // placeholder
        }
    }
}

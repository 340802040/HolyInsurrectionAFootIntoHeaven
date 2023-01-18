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
    protected Point backLocation; // location to go back to
    
    public Ally() {
        xpNeeded = 100; // placeholder
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
        checkLevelUp();
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

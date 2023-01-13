import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ally extends BattleWorldCharacter
{
    private boolean isMoving = false;
    private ArrayList<Point> path = new ArrayList<Point>();
    private SimpleTimer moveTimer = new SimpleTimer();
    private int i; // index for path
    private int speed; // move limit
    protected boolean moved = false; // whether has been moved already

    public Ally(int speed) {
        this.speed = speed;
        setImage("placeholder/ally.png");
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
    }

    public void startMoving(ArrayList<Point> path) {
        i = path.size() - 1;
        isMoving = true;
        this.path = path;
        map[r][c] = 0; // clear spot
    }

    public void move() {
        if (i == -1) {
            isMoving = false;
            moved = true;
            map[r][c] = 1;
            ((BattleWorld)getWorld()).increaseAlliesMoved();
            getImage().setTransparency(150);
            return;
        }
        Point p = path.get(i);
        if (moveTimer.millisElapsed() > 80) {
            setLocation(GameWorld.getX(p.c), GameWorld.getY(p.r));
            i--;
            moveTimer.mark();
        }
    }
    
    public int getSpeed() {
        return speed;
    }
}

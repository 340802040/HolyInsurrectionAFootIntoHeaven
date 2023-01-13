import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ally extends GameWorldCharacter
{
    private boolean isMoving = false;
    private ArrayList<Point> path = new ArrayList<Point>();
    private SimpleTimer moveTimer = new SimpleTimer();
    private int i; // index for path
    private int speed; // move limit

    public Ally(int speed) {
        this.speed = speed;
        setImage("placeholder/ally.png");
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
        
        // update map (current space opens up while new space is occupied)
        int newR = path.get(0).r;
        int newC = path.get(0).c;
        map[r][c] = 0;
        map[newR][newC] = 1;
    }

    public void move() {
        if (i == -1) {
            isMoving = false;
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BattleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleWorld extends GameWorld
{
    protected String phase = "player";
    protected int numAllies;
    protected int numEnemies;
    protected int alliesMoved; // # of allies moved in a player phase
    protected int enemiesMoved;
    protected ArrayList<Ally> allies = new ArrayList<Ally>();
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    protected Selector selector = new Selector();
    private int i; // index used for going through each enemy during enemy phase
    protected Enemy curMovingEnemy;

    public BattleWorld(int width, int height, int pixelSize) {    
        super(width, height, pixelSize);
        setPaintOrder(Selector.class);

        // add selector
        addObject(selector, GameWorld.getX(0), GameWorld.getY(0));
    }

    public void act() {
        checkPhaseSwitch();
        if (phase == "enemy") {
            moveEnemies();
        }
    }

    public void increaseAlliesMoved() {
        alliesMoved++;
    }

    public void increaseEnemiesMoved() {
        enemiesMoved++;
    }

    public void checkPhaseSwitch() {
        if (phase == "player" && alliesMoved == numAllies) {
            phase = "enemy";
            removeObject(selector);
            resetAllyVariables();
            //System.out.println("enemy phase");
        }
        else if (phase == "enemy" && enemiesMoved == numEnemies) {
            phase = "player";
            addObject(selector, GameWorld.getX(selector.c), GameWorld.getY(selector.r));
            resetEnemyVariables();
            //System.out.println("player phase");
        }
    }

    public void moveEnemies() {
        if (i == 0 && curMovingEnemy == null) {
            curMovingEnemy = enemies.get(i);
            curMovingEnemy.startMoving();
        }
        if (curMovingEnemy.moved) {
            i++;
            if (i >= enemies.size()) {
                return;
            }
            curMovingEnemy = enemies.get(i);
            curMovingEnemy.startMoving();
        }
    }

    /**
     * Resets all necessary Ally variables such as Ally.moved and alliesMoved after player phase is over.
     */
    public void resetAllyVariables() {
        alliesMoved = 0;
        for (Ally a : allies) {
            a.moved = false;
            a.getImage().setTransparency(255);
        }
    }

    public void resetEnemyVariables() {
        enemiesMoved = 0;
        i = 0;
        curMovingEnemy = null;
        for (Enemy e : enemies) {
            e.moved = false;
            e.getImage().setTransparency(255);
        }
    }

    /**
     * BattleWorld Map Legend:
     * 
     * Example Map:
     * {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
     * {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
     * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
     * 
     * 0 - Available space
     * 1 - Ally
     * 2 - Enemy
     */
}

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
    // DATA
    protected String phase = "player";
    protected String state = "gameplay"; // could be gameplay, decision, or attack animation
    protected int numAllies;
    protected int numEnemies;
    protected int alliesMoved; // # of allies moved in a player phase
    protected int enemiesMoved;
    protected ArrayList<Ally> allies = new ArrayList<Ally>();
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    protected Selector selector = new Selector();
    protected boolean attackAnimating; // whether an attack is currently animating
    // ENEMY PHASE
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
        if (phase == "enemy" && state == "gameplay") {
            moveEnemies();
        }
    }

    public void checkPhaseSwitch() {
        if (phase == "player" && state == "gameplay" && alliesMoved == numAllies && !cardAnimating) {
            addObject(new BattlePhaseCard("placeholder/enemy-phase.jpg"), getWidth() / 2, getHeight() / 2);
            cardAnimating = true;
            removeSelector();
            //System.out.println("enemy phase");
        }
        else if (phase == "enemy" && state == "gameplay" && enemiesMoved == numEnemies && !cardAnimating) {
            addObject(new BattlePhaseCard("placeholder/player-phase.jpg"), getWidth() / 2, getHeight() / 2);
            cardAnimating = true;
            //System.out.println("player phase");
        }
    }
    
    public void startEnemyPhase() {
        resetEnemyVariables();
        phase = "enemy";
    }
    
    public void startPlayerPhase() {
        resetAllyVariables();
        phase = "player";
        addSelector();
    }

    public void moveEnemies() {
        if (i == 0 && curMovingEnemy == null) {
            curMovingEnemy = enemies.get(i);
            curMovingEnemy.startMoving();
        }
        if (curMovingEnemy.moved && i < enemies.size()) {
            Greenfoot.delay(40);
            i++;
            if (i == enemies.size()) {
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
            a.selectedEnemy = null;
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
    
    public ArrayList<Ally> getAllies() {
        return allies;
    }
    
    /**
     * For other classes that need to remove the selector from the world.
     */
    public void removeSelector() {
        removeObject(selector);
    }
    
    /**
     * Adds selector back into world at the position it left off at.
     */
    public void addSelector() {
        addObject(selector, GameWorld.getX(selector.c), GameWorld.getY(selector.r));
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

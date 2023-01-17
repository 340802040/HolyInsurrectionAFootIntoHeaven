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
    protected String state = "gameplay";
    protected ArrayList<Ally> allies = new ArrayList<Ally>();
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    protected Selector selector = new Selector();
    protected boolean selectorAdded; // whether selector is in the world
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
        checkState();
    }
    
    public void initializeGrid() {
        for (int r = 0; r < GRID_HEIGHT; r++) {
            for (int c = 0; c < GRID_WIDTH; c++) {
                if (map[r][c] == 0) {
                    addObject(new Cell("EnvironmentTiles/cloud.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 3) {
                    addObject(new Cell("EnvironmentTiles/mountain.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 4) {
                    addObject(new Cell("EnvironmentTiles/forest.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 5) {
                    addObject(new Cell("EnvironmentTiles/house.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 6) {
                    addObject(new Cell("EnvironmentTiles/market.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
            }
        } 
    }

    public void checkPhaseSwitch() {
        if (phase == "player" && state == "gameplay" && getNumAlliesMoved() == allies.size()) {
            addObject(new BattlePhaseCard("placeholder/enemy-phase.jpg"), getWidth() / 2, getHeight() / 2);
            state = "card animation";
        }
        else if (phase == "enemy" && state == "gameplay" && getNumEnemiesMoved() == enemies.size()) {
            addObject(new BattlePhaseCard("placeholder/player-phase.jpg"), getWidth() / 2, getHeight() / 2);
            state = "card animation";
        }
    }
    
    public void checkState() {
        if (state == "gameplay" && phase == "player" && !selectorAdded) {
            addSelector();
            selectorAdded = true;
        }
        else if (state != "gameplay" && selectorAdded) {
            removeSelector();
            selectorAdded = false;
        }
        
        if (phase == "enemy" && state == "gameplay") {
            moveEnemies();
        }
    }
    
    public void startEnemyPhase() {
        resetEnemyVariables();
        phase = "enemy";
    }
    
    public void startPlayerPhase() {
        resetAllyVariables();
        phase = "player";
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
        for (Ally a : allies) {
            a.moved = false;
            a.getImage().setTransparency(255);
            a.selectedEnemy = null;
        }
    }

    public void resetEnemyVariables() {
        i = 0;
        curMovingEnemy = null;
        for (Enemy e : enemies) {
            e.moved = false;
            e.getImage().setTransparency(255);
        }
    }
    
    public int getNumAlliesMoved() {
        int ret = 0;
        for (Ally a : allies) {
            if (a.moved) {
                ret++;
            }
        }
        
        return ret;
    }
    
    public int getNumEnemiesMoved() {
        int ret = 0;
        for (Enemy e : enemies) {
            if (e.moved) {
                ret++;
            }
        }
        
        return ret;
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
     * 3 - Mountain
     * 4 - Forest
     * 5 - House
     * 6 - Market
     */
}

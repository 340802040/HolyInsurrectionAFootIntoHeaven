import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BattleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleWorld extends GameWorld
{
    // DATA
    protected String phase = "player";
    protected ArrayList<Ally> allies = new ArrayList<Ally>();
    protected static ArrayList<Ally> ALLIES = new ArrayList<Ally>();
    protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    protected Selector selector = new Selector();
    protected boolean selectorAdded; // whether selector is in the world
    protected int turns = 0;
    // ENEMY PHASE
    private int i; // index used for going through each enemy during enemy phase
    protected Enemy curMovingEnemy;
    // MISC
    protected Image bossIcon = new Image("BossIcon.png");
    protected MenuWindow menuWindow;

    public BattleWorld(int width, int height, int pixelSize) {    
        super(width, height, pixelSize);
        state = "gameplay";
        addObject(selector, GameWorld.getX(0), GameWorld.getY(0)); 
        setPaintOrder(HoverWindow.class, Selector.class);
    }

    public void act() {
        checkStateAndPhase();
        checkMenu();
    }

    public void checkStateAndPhase() {        
        // PHASE SWITCH
        if (phase == "player" && state == "gameplay" && getNumAlliesMoved() == allies.size()) {
            addObject(new BattlePhaseCard("EnemyPhase.png"), getWidth() / 2, getHeight() / 2);
            state = "card animation";
        }
        else if (phase == "enemy" && state == "gameplay" && getNumEnemiesMoved() == enemies.size()) {
            addObject(new BattlePhaseCard("PlayerPhase.png"), getWidth() / 2, getHeight() / 2);
            state = "card animation";
        }
        // PHASES
        if (phase == "player") {
            Soundtrack.farInForeignLands.playLoop();
        }
        if (phase == "enemy") {
            Soundtrack.aSweepingFog.playLoop();
        }
        // ENEMY MOVEMENT
        if (phase == "enemy" && state == "gameplay") {
            moveEnemies();
        }
        // SELECTOR
        if (state == "gameplay" && phase == "player" && !selectorAdded) {
            addSelector();
            selectorAdded = true;
        }
        else if (state != "gameplay") {
            selector.removeSelf();
            selectorAdded = false;
        }
    }
    
    public void checkMenu() {
        if (Greenfoot.isKeyDown("escape") && state == "gameplay") {
            menuWindow = new MenuWindow(state);
            addObject(menuWindow, getWidth() / 2, getHeight() / 2);
            state = "menu";
            Soundtrack.lullabyOfFairies.playLoop();
            Soundtrack.stopAllExceptLullabyOfFairies();
        }
    }

    public void startEnemyPhase() {
        resetAllyVariables();
        phase = "enemy";
    }

    public void startPlayerPhase() {
        turns++;
        resetEnemyVariables();
        phase = "player";
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

    public void moveEnemies() {
        if (i == 0 && curMovingEnemy == null) {
            curMovingEnemy = enemies.get(i);
            if (curMovingEnemy.isBoss) {
                curMovingEnemy.moved = true;
            }
            else {
                curMovingEnemy.startMoving();   
            }
            i++;
        }
        if (curMovingEnemy.moved && i < enemies.size()) {
            Greenfoot.delay(30);
            curMovingEnemy = enemies.get(i);
            if (curMovingEnemy.isBoss) {
                curMovingEnemy.moved = true;
            }
            else {
                curMovingEnemy.startMoving();    
            }
            i++;
        }
    }

    public void removeAlly(Ally a) {
        allies.remove(a);
        map[a.r][a.c] = 0;  
        removeObject(a);
    }

    public void removeEnemy(Enemy e) {
        enemies.remove(e);
        map[e.r][e.c] = 0;
        i -= (i == 0) ? 0 : 1;
        if (e.isBoss) {
            removeObject(bossIcon);
        }
        removeObject(e);
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
     * Adds selector back into world at the position it left off at.
     */
    public void addSelector() {
        addObject(selector, GameWorld.getX(selector.c), GameWorld.getY(selector.r));
    }
    
    /**
     * Replenishes all allies' hp at start of a chapter.
     */
    public void replenish() {
        for (Ally a : allies) {
            a.health = a.maxHealth; // replenish
        }
    }
    
    public Ally findAlly(String name) {
        for (Ally a : allies) {
            if (a.name.equals(name)) {
                return a;
            }
        }
        return new Ally("");
    }

    /**
     * Saves the highest chapter achieved with the army present at that time.
     * Only called once a chapter has been complete.
     */
    public void saveHighestChapter() {
        int newScore = 1;
        if (this instanceof Chapter1) {
            newScore = 2;
        }
        if (this instanceof Chapter2) {
            newScore = 3;
        }
        
        ALLIES = Ally.getClones(allies); //  save clones to master array of allies

        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            if (newScore > myInfo.getScore()) {
                myInfo.setScore(newScore);
                myInfo.store();  // write back to server
            }
        }
    }
    
    public boolean tileAvailable(int r, int c) {
        int[][] map = getMap();
        return map[r][c] == 0 || map[r][c] == 7 || map[r][c] == 8 || map[r][c] == 15;
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
                else if (map[r][c] == 7) {
                    addObject(new Cell("EnvironmentTiles/hill.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 8) {
                    addObject(new Cell("EnvironmentTiles/grass.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 9) {
                    addObject(new Cell("EnvironmentTiles/forestforest.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 10) {
                    addObject(new Cell("EnvironmentTiles/foresthill.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 11) {
                    addObject(new Cell("EnvironmentTiles/forestmountain.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 12) {
                    addObject(new Cell("EnvironmentTiles/foresttree.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 13) {
                    addObject(new Cell("EnvironmentTiles/foresttree1.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 14) {
                    addObject(new Cell("EnvironmentTiles/river.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 15) {
                    addObject(new Cell("EnvironmentTiles/road.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 16) {
                    addObject(new Cell("EnvironmentTiles/blacksmith.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 17) {
                    addObject(new Cell("EnvironmentTiles/wizardhut.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 18) {
                    addObject(new Cell("EnvironmentTiles/cloudtree.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 19) {
                    addObject(new Cell("EnvironmentTiles/colosseum.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 20) {
                    addObject(new Cell("EnvironmentTiles/deserthouse.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 21) {
                    addObject(new Cell("EnvironmentTiles/house1.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 22) {
                    addObject(new Cell("EnvironmentTiles/placeofslumbertile.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 23) {
                    addObject(new Cell("EnvironmentTiles/slumberwallhorizontal.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 24) {
                    addObject(new Cell("EnvironmentTiles/slumberwallvertical.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 25) {
                    addObject(new Cell("EnvironmentTiles/castletile.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
                else if (map[r][c] == 26) {
                    addObject(new Cell("EnvironmentTiles/castlewallvertical.png"), GameWorld.getX(c), GameWorld.getY(r));
                }
            }
        } 
    }
    
    public void stopped() {
        
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
     * 0 - cloud
     * 1 - Ally
     * 2 - Enemy
     * 3 - mountain
     * 4 - forest
     * 5 - house
     * 6 - market
     * 7 - hill
     * 8 - grass
     * 9 - forestforest
     * 10 - foresthill
     * 11 - forestmountain
     * 12 - foresttree
     * 13 - foresttree1
     * 14 - river
     * 15 - road
     * 16 - blacksmith
     * 17 - wizardhut
     * 18 - cloudtree
     * 19 - colosseum
     * 20 - deserthouse
     * 21 - house1
     * 22 - placeofslumbertile
     * 23 - slumberwallhorizontal
     * 24 - slumberwallvertical
     * 25 - castletile
     * 26 - castlewallvertical
     */
}

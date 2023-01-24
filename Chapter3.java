import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chapter3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chapter3 extends BattleWorld
{
    private AllyFootSoldier telu = new AllyFootSoldier("Telu");
    private EnemyFootSoldier e1 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e2 = new EnemyFootSoldier(false);
    private EnemyWizard e3 = new EnemyWizard(false);
    private EnemyWizard e4 = new EnemyWizard(false);
    private EnemyWizard e5 = new EnemyWizard(false);
    private EnemyWizard e6 = new EnemyWizard(false);
    private EnemyWizard e7 = new EnemyWizard(false);
    private EnemyWizard boss = new EnemyWizard(true);
    
    public Chapter3() {
        super(1200, 800, 1);
        allies = Ally.getClones(ALLIES); // clone the saved copy
        allies.add(telu);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
        enemies.add(boss);
        
        map = new int[][] {
            {11, 11, 11, 12, 11, 11, 11, 11, 11, 12, 11, 11, 11, 13, 12, 11, 11, 12, 11, 11, 11, 13, 13, 13},
            {11, 12, 11, 11, 11, 12, 12, 13, 11, 11, 11, 8, 8, 8, 8, 8, 8, 8, 13, 11, 11, 11, 13, 13},
            {11, 13, 11, 13, 12, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 13},
            {13, 12, 13, 13, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12},
            {12, 12, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 8, 8, 8, 8, 8, 8, 8, 8},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 13, 12, 12, 13, 8, 8, 8, 8, 8, 8, 8},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 13, 12, 12, 13, 13, 12, 13, 13, 12, 8, 8, 8},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 13, 12, 12, 13, 12, 13, 12, 13, 13, 12, 14, 14, 14},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 13, 12, 12, 12, 12, 13, 12, 14, 14, 14},
            {12, 12, 12, 12, 13, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 13, 12, 12, 13, 13, 13, 8, 8},
            {11, 13, 13, 13, 13, 13, 13, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {13, 13, 13, 13, 12, 12, 12, 13, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {11, 13, 11, 12, 11, 12, 13, 13, 12, 12, 12, 13, 13, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {12, 11, 11, 11, 11, 13, 13, 11, 13, 13, 13, 12, 12, 12, 11, 13, 13, 8, 8, 8, 8, 8, 8, 8},
            {11, 12, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 12, 11, 11, 12, 13, 13, 8, 8, 8, 8, 8},
            {11, 11, 11, 12, 11, 11, 11, 12, 11, 11, 11, 11, 13, 11, 11, 11, 12, 11, 11, 11, 11, 8, 8, 8}
        };
        
        initializeGrid();
        spawn();
        setupStats();
    }
    
    public void act() {
        super.act();
    }
    
    public void spawn() {
        // ALLIES
        for (Ally a : allies) {
            while (true) {
                int r = getRandomNumberInRange(4, 11);
                int c = getRandomNumberInRange(1, 4);
                if (tileAvailable(r, c)) {                      
                    addObject(a, GameWorld.getX(c), GameWorld.getY(r));         
                    map[r][c] = 1; // just to be safe in case the loop runs faster than Ally's addedToWorld()
                    break;
                }
            }
        }
        // ENEMIES
        for (Enemy e : enemies) {
            if (e.isBoss) continue;
            while (true) {
                int r = getRandomNumberInRange(0, GameWorld.GRID_HEIGHT);
                int c = getRandomNumberInRange(GameWorld.GRID_WIDTH - 7, GameWorld.GRID_WIDTH - 3);
                if (tileAvailable(r, c)) {                      
                    addObject(e, GameWorld.getX(c), GameWorld.getY(r));         
                    map[r][c] = 2; // just to be safe in case the loop runs faster than Ally's addedToWorld()
                    break;
                }
            }
        }
        // boss
        int r = GameWorld.GRID_HEIGHT - 4;
        int c = GameWorld.GRID_WIDTH - 3;
        addObject(boss, GameWorld.getX(c), GameWorld.getY(r));
        addObject(bossIcon, GameWorld.getX(c), GameWorld.getY(r));
    }
    
    public void setupStats() {
        // ALLIES
        telu.maxHealth = telu.health = 33;
        telu.atk = 6;
        telu.def = 7;
        telu.ev = 4;
        telu.spd = 3;
        // ENEMIES
        boss.name = "Wizard Boss";
        for (Enemy e : enemies) {
            if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 20;
                e.atk = 7;
                e.def = 3;
                e.ev = 2;
            }
            if (e instanceof EnemyWizard) {
                e.maxHealth = e.health = 18;
                e.atk = 7;
                e.ev = 3;
                e.spd = 3;
            }
            if (e.isBoss) {
                e.maxHealth = e.health = 35;
                e.atk = 9;
                e.def = 5;
                e.ev = 4;
                e.spd = 3;
            }
        }
    }
}

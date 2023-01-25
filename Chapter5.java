import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Chapter 5 world. The Place of Slumber
 * 
 * @author Jonathan Zhao, Patrick Hu 
 * @version Jan 2023
 */
public class Chapter5 extends BattleWorld
{
    private EnemyArcher e1 = new EnemyArcher(false);
    private EnemyArcher e2 = new EnemyArcher(false);
    private EnemyArcher e3 = new EnemyArcher(false);
    private EnemyArcher e4 = new EnemyArcher(false);
    private EnemyArcher e5 = new EnemyArcher(false);
    private EnemyArcher e6 = new EnemyArcher(false);
    private EnemyCavalry e7 = new EnemyCavalry(false);
    private EnemyCavalry e8 = new EnemyCavalry(false);
    private EnemyFootSoldier e9 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e10 = new EnemyFootSoldier(false);
    private EnemyWizard e11 = new EnemyWizard(false);
    private EnemyWizard e12 = new EnemyWizard(false);
    private EnemyArcher boss1 = new EnemyArcher(true);
    private EnemyWizard boss2 = new EnemyWizard(true);
    
    public Chapter5() {
        super(1200, 800, 1);
        allies = Ally.getClones(ALLIES);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
        enemies.add(e8);
        enemies.add(e9);
        enemies.add(e10);
        enemies.add(e11);
        enemies.add(e12);
        enemies.add(boss1);
        enemies.add(boss2);
        
        map = new int[][] {
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22},
            {22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 22, 22, 22, 22, 22, 22, 22, 22}
        };
        
        initializeGrid();
        spawn();
        setupStats();
        replenish();
    }

    public void act() {
        super.act();
    }
    
    public void spawn() {
        // ALLIES
        for (Ally a : allies) {
            while (true) {
                int r = getRandomNumberInRange(3, 5);
                int c = getRandomNumberInRange(1, 7);
                if (tileAvailable(r, c)) {
                    addObject(a, GameWorld.getX(c), GameWorld.getY(r));
                    map[r][c] = 1;
                    break;
                }
            }
        }
        // ENEMIES
        for (Enemy e : enemies) {
            if (e.isBoss) continue;
            while (true) {
                int r = getRandomNumberInRange(0, GameWorld.GRID_HEIGHT);
                int c = getRandomNumberInRange(6, GameWorld.GRID_WIDTH - 4);
                if (tileAvailable(r, c)) {
                    addObject(e, GameWorld.getX(c), GameWorld.getY(r));
                    map[r][c] = 2;
                    break;
                }
            }
        }
        // 2 bosses
        int r = 13;
        int c = GameWorld.GRID_WIDTH - 3;
        addObject(boss1, GameWorld.getX(c), GameWorld.getY(r));
        r = 2;
        addObject(boss2, GameWorld.getX(c), GameWorld.getY(r));
    }
    
    public void setupStats() {
        for (Enemy e : enemies) {
            if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 22;
                e.atk = 9;
                e.def = 5;
                e.ev = 2;
            }
            if (e instanceof EnemyArcher) {
                e.maxHealth = e.health = 17;
                e.atk = 9;
                e.ev = 4;
                e.spd = 4;
            }
            if (e instanceof EnemyWizard) {
                e.maxHealth = e.health = 15;
                e.atk = 9;
                e.ev = 4;
                e.spd = 3;
            }
            if (e instanceof EnemyCavalry) {
                e.maxHealth = e.health = 22;
                e.atk = 8;
                e.def = 3;
                e.ev = 3;
                e.spd = 5;
            }
            if (e.isBoss) {
                e.maxHealth = e.health = 40;
                e.atk = 11;
                e.def = 7;
                e.ev = 4;
                e.spd = 5;
            }
        }
    }
}

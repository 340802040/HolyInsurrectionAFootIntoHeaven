import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chapter2 extends BattleWorld
{   
    private AllyCavalry ipos = new AllyCavalry("Ipos");
    private AllyArcher effitos = new AllyArcher("Effitos");
    private EnemyFootSoldier e1 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e2 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e3 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e4 = new EnemyFootSoldier(false);
    private EnemyArcher e5 = new EnemyArcher(false);
    private EnemyArcher e6 = new EnemyArcher(false);
    private EnemyArcher boss = new EnemyArcher(true);

    public Chapter2() {
        // level1 should take in array of allies but for now hardcode them
        super(1200, 800, 1);
        allies = Ally.getClones(ALLIES); // clone the saved copy
        allies.add(ipos);
        allies.add(effitos);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(boss);

        map = new int[][] {
            {0, 3, 3, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 0, 3, 0, 0, 0, 3, 3, 3, 0, 3, 0},
            {3, 0, 3, 0, 0, 3, 3, 3, 0, 0, 3, 3, 3, 3, 0, 3, 0, 0, 3, 0, 3, 0, 3, 3},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 5, 0, 0, 5, 5, 0, 0, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 0, 5, 5, 5, 0, 0, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 3, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 3, 0, 3, 3, 0, 3, 0, 3, 0, 0},
            {3, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 3, 3, 3, 3, 3}
        };
        
        initializeGrid();
        spawn();
        setupStats();
    }
    
    public void spawn() {
        // ALLIES
        for (Ally a : allies) {
            while (true) {
                int r = getRandomNumberInRange(4, 11);
                int c = getRandomNumberInRange(1, 4);
                if (map[r][c] == 0) {                      
                    addObject(a, GameWorld.getX(c), GameWorld.getY(r));         
                    map[r][c] = 1; // just to be safe in case the loop runs faster than Ally's addedToWorld()
                    break;
                }
            }
        }
        // ENEMIES
        addObject(e1, GameWorld.getX(16), GameWorld.getY(6));
        addObject(e2, GameWorld.getX(16), GameWorld.getY(7));
        addObject(e3, GameWorld.getX(16), GameWorld.getY(9));
        addObject(e4, GameWorld.getX(16), GameWorld.getY(10));
        addObject(e5, GameWorld.getX(19), GameWorld.getY(7));
        addObject(e6, GameWorld.getX(19), GameWorld.getY(9));
        addObject(boss, GameWorld.getX(21), GameWorld.getY(8));
        addObject(bossIcon, GameWorld.getX(21), GameWorld.getY(8));
    }
    
    public void setupStats() {
        // ALLIES
        ipos.maxHealth = 28;
        ipos.atk = 6;
        ipos.def = 5;
        effitos.maxHealth = 20;
        effitos.atk = 8;
        effitos.def = 3;
        replenish();
        // ENEMIES
        boss.name = "Archer Boss";
        for (Enemy e : enemies) {
            if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 15;
                e.atk = 4;
                e.ev = 1;
            }
            if (e instanceof EnemyArcher) {
                e.maxHealth = e.health = 10;
                e.atk = 6;
                e.ev = 2;
                e.spd = 1;
            }
            if (e.isBoss) {
                e.maxHealth = e.health = 25;
                e.atk = 5;
                e.def = 2;
                e.ev = 2;
            }
        }
    }
}

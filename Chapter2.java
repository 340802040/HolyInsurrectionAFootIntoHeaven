import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chapter2 extends BattleWorld
{   
    private EnemyFootSoldier e1 = new EnemyFootSoldier();
    private EnemyFootSoldier e2 = new EnemyFootSoldier();

    public Chapter2() {
        // level1 should take in array of allies but for now hardcode them
        super(1200, 800, 1);
        allies.add(new AllyFootSoldier("placeholder"));
        allies.add(new AllyFootSoldier("placeholder"));
        enemies.add(e1);
        enemies.add(e2);
        
        setupEnemyStats();

        map = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        initializeGrid();

        for (Ally a : allies) {
            while (true) {
                int r = getRandomNumberInRange(0, 10);
                int c = getRandomNumberInRange(0, 10);
                if (map[r][c] == 0) {                      
                    addObject(a, GameWorld.getX(c), GameWorld.getY(r));         
                    map[r][c] = 1; // just to be safe in case the loop runs faster than Ally's addedToWorld()
                    break;
                }
            }
        }
        
        for (Enemy e : enemies) {
            while (true) {
                int r = getRandomNumberInRange(0, 10);
                int c = getRandomNumberInRange(0, 10);
                if (map[r][c] == 0) {                       
                    addObject(e, GameWorld.getX(c), GameWorld.getY(r));         
                    map[r][c] = 2;
                    break;
                }
            }
        }        
    }
    
    public void setupEnemyStats() {
        for (Enemy e : enemies) {
            if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 9;
                e.atk = 1;
            }
            else if (e.isBoss) {
                e.maxHealth = e.health = 15;
                e.atk = 3;
                e.def = 1;
            }
        }
    }
}

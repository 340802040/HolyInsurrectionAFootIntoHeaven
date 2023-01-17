import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends BattleWorld
{
    // DATA
    private int turn = 0;
    // CHARACTERS
    private Hero hero = new Hero();
    private Crusader prodeus = new Crusader();
    private EnemyFootSoldier e1 = new EnemyFootSoldier();
    private EnemyFootSoldier e2 = new EnemyFootSoldier();
    private EnemyFootSoldier e3 = new EnemyFootSoldier();
    private EnemyFootSoldier e4 = new EnemyFootSoldier();
    private EnemyFootSoldier boss = new EnemyFootSoldier();
    // DIALOGUES
    Dialogue oldManD = new Dialogue("images/Text/OldManInstructions/");
    Dialogue prodeusD = new Dialogue("images/Text/ProdeusInstructions/");
    
    public Tutorial() {
        super(1200, 800, 1);
        allies.add(hero);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(boss);
        
        map = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        initializeGrid();
        
        // spawn hero
        addObject(hero, GameWorld.getX(4), GameWorld.getY(GameWorld.GRID_HEIGHT / 2));
        
        // spawn enemies randomly
        for (Enemy e : enemies) {
            while (true) {
                int randr = getRandomNumberInRange(0, GameWorld.GRID_HEIGHT);
                int randc = getRandomNumberInRange(GameWorld.GRID_WIDTH - 6, GameWorld.GRID_WIDTH - 1);
                if (map[randr][randc] == 0) {
                    addObject(e, GameWorld.getX(randc), GameWorld.getY(randr));         
                    map[randr][randc] = 2; // just to be safe in case the loop runs faster than character's addedToWorld()
                    break;
                }
            }
        }
        
        addObject(oldManD, 0, 0);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chapter7 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chapter7 extends BattleWorld
{
    private EnemyFootSoldier e1 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e2 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e3 = new EnemyFootSoldier(false);
    private EnemyArcher e4 = new EnemyArcher(false);
    private EnemyArcher e5 = new EnemyArcher(false);
    private EnemyArcher e6 = new EnemyArcher(false);
    private EnemyCavalry e7 = new EnemyCavalry(false);
    private EnemyCavalry e8 = new EnemyCavalry(false);
    private EnemyCavalry e9 = new EnemyCavalry(false);
    private EnemyCavalry e10 = new EnemyCavalry(false);
    private EnemyWizard e11 = new EnemyWizard(false);
    private EnemyWizard e12 = new EnemyWizard(false);
    private EnemyWizard e13 = new EnemyWizard(false);
    private EnemyWizard e14 = new EnemyWizard(false);
    private EnemyCrusader boss1 = new EnemyCrusader(true);
    private EnemyCrusader boss2 = new EnemyCrusader(true);
    // DIALOGUE
    Dialogue prodeusDying = new Dialogue("images/Text/ProdeusDying/", "gameplay");
    
    public Chapter7() {
        super(1200, 800, 1);
        buff();
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
        enemies.add(e13);
        enemies.add(e14);
        enemies.add(boss1);
        enemies.add(boss2);
        
        map = new int[][] {
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25},
            {25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25, 25, 25, 25}
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
                int r = getRandomNumberInRange(0, 5);
                int c = getRandomNumberInRange(0, 16);
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
                int c = getRandomNumberInRange(8, GameWorld.GRID_WIDTH - 3);
                if (tileAvailable(r, c)) {
                    addObject(e, GameWorld.getX(c), GameWorld.getY(r));
                    map[r][c] = 2;
                    break;
                }
            }
        }
        
        // boss
        int r = GameWorld.GRID_HEIGHT / 2;
        int c = GameWorld.GRID_WIDTH - 2;
        addObject(boss1, GameWorld.getX(c), GameWorld.getY(r));
        boss1.name = "The Being";
        
        // boss 2
        r = 8;
        c = 11;
        addObject(boss2, GameWorld.getX(c), GameWorld.getY(r));
        boss2.name = "Prodeus";
    }
    
    public void setupStats() {
        // add blade of eithalon
        Ally hero = findAlly("Hero");
        hero.weapons.add("BladeOfEithalon");
        // transfer prodeus over to enemy side
        Ally prodeus = findAlly("Prodeus");
        boss2.maxHealth = prodeus.maxHealth;
        boss2.health = prodeus.health;
        boss2.atk = prodeus.atk;
        boss2.def = prodeus.def;
        boss2.ev = prodeus.ev;
        boss2.spd = prodeus.spd;
        boss2.moveLimit = prodeus.moveLimit;
        boss2.weapons = prodeus.weapons;
        boss2.weapon = prodeus.weapon;  
        
        for (Enemy e : enemies) {
            if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 25;
                e.atk = 9;
                e.def = 5;
                e.ev = 2;
            }
            if (e instanceof EnemyArcher) {
                e.maxHealth = e.health = 20;
                e.atk = 9;
                e.ev = 4;
                e.spd = 4;
            }
            if (e instanceof EnemyWizard) {
                e.maxHealth = e.health = 17;
                e.atk = 9;
                e.ev = 4;
                e.spd = 3;
            }
            if (e instanceof EnemyCavalry && !e.name.equals("Prodeus") && !e.name.equals("The Being")) {
                e.maxHealth = e.health = 30;
                e.atk = 8;
                e.ev = 3;
                e.spd = 5;
            }
            if (e.name == "The Being") {
                e.maxHealth = e.health = 66;
                e.atk = 14;
                e.def = 8;
                e.ev = 5;
                e.spd = 5;
            }
        }
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chapter1 extends BattleWorld
{
    // CHARACTERS
    private AllyHero hero = new AllyHero("Hero");
    private AllyCrusader prodeus = new AllyCrusader("Prodeus");
    private EnemyFootSoldier e1 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e2 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e3 = new EnemyFootSoldier(false);
    private EnemyFootSoldier e4 = new EnemyFootSoldier(false);
    private EnemyFootSoldier boss = new EnemyFootSoldier(true);
    // DIALOGUES
    Dialogue oldManD = new Dialogue("images/Text/OldManInstructions/", "gameplay"); // images/ needed for Java.io.File detection
    Dialogue prodeusD = new Dialogue("images/Text/ProdeusInstructions/", "gameplay");
    Dialogue oldManD2 = new Dialogue("images/Text/PostChap1/", "clear");
    // MISC
    private boolean mark;

    public Chapter1() {
        super(1200, 800, 1);
        allies.add(hero);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(boss);

        map = new int[][] {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 0, 0},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 3, 3, 3, 3, 4, 4, 3, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 18, 18, 0, 0},
            {3, 3, 4, 4, 4, 4, 4, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0},
            {3, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 18, 5, 5, 5, 18, 0, 5},
            {4, 4, 4, 4, 4, 0, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 0},
            {0, 0, 0, 4, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0},
            {4, 4, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 4, 4, 4, 4, 4, 0, 4, 4, 0, 0, 0, 0, 0, 0, 18, 5, 5, 18, 0, 5, 5, 5, 5},
            {3, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 18, 18, 0},
            {3, 3, 3, 4, 3, 3, 4, 4, 4, 3, 3, 4, 4, 4, 4, 4, 0, 0, 0, 18, 0, 0, 0, 0},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0}
        };
        
        initializeGrid();
        spawn();
        setupStats();
        addObject(oldManD, 0, 0);
    }

    public void act() {
        super.act();
        if (turns == 1 && !mark) {
            addObject(prodeusD, 0, 0);
            hero.weapons.add("Sword");
            hero.weapons.add("Spear");
            hero.weapons.add("Bow");
            addObject(prodeus, GameWorld.getX(2), GameWorld.getY(8));
            allies.add(prodeus);
            mark = true;
        }
        checkClear();
    }
    
    public void spawn() {
        // ALLIES
        addObject(hero, GameWorld.getX(4), GameWorld.getY(8));
        // ENEMIES
        addObject(e1, GameWorld.getX(19), GameWorld.getY(6));
        addObject(e2, GameWorld.getX(19), GameWorld.getY(7));
        addObject(e3, GameWorld.getX(19), GameWorld.getY(9));
        addObject(e4, GameWorld.getX(19), GameWorld.getY(10));
        addObject(boss, GameWorld.getX(21), GameWorld.getY(8));
        addObject(bossIcon, GameWorld.getX(21), GameWorld.getY(8));
    }
    
    public void setupStats() {
        // ALLIES
        hero.maxHealth = 100; // testing, should be 16
        hero.atk = 100; // testing, should be 6
        hero.weapon = "Fists";
        hero.moveLimit = 100; // testing
        prodeus.maxHealth = prodeus.health = 45;
        prodeus.atk = 100; // testing, should be 12
        prodeus.def = 10;
        prodeus.moveLimit = 100; // testing
        replenish();
        // ENEMIES
        boss.name = "Foot Soldier Boss";
        for (Enemy e : enemies) {
            if (e.isBoss) { // first check if boss since boss is also a foot soldier
                e.maxHealth = e.health = 18;
                e.atk = 4;
                e.def = 2;
            }
            else if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 9;
                e.atk = 3;
            }
        }
    }
    
    public void checkClear() {  
        if (state == "clear" && oldManD2.getWorld() == null) {
            addObject(oldManD2, 0, 0);
        }
        if (oldManD2.i == oldManD2.dialogues.size()) {
            Greenfoot.setWorld(new Intermission("Intermissions/Intermission1.png", "images/Text/OldManInstructions/", 1));
        }
    }
}

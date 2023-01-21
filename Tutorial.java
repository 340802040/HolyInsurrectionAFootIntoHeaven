import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends BattleWorld
{
    // CHARACTERS
    private AllyHero hero = new AllyHero("Hero");
    private AllyCrusader prodeus = new AllyCrusader("Prodeus");
    private EnemyFootSoldier e1 = new EnemyFootSoldier();
    private EnemyFootSoldier e2 = new EnemyFootSoldier();
    private EnemyFootSoldier e3 = new EnemyFootSoldier();
    private EnemyFootSoldier e4 = new EnemyFootSoldier();
    private EnemyFootSoldier boss = new EnemyFootSoldier();
    // DIALOGUES
    Dialogue oldManD = new Dialogue("images/Text/OldManInstructions/", "gameplay"); // images/ needed for Java.io.File detection
    Dialogue prodeusD = new Dialogue("images/Text/ProdeusInstructions/", "gameplay");
    Dialogue oldManD2 = new Dialogue("images/Text/PostChap1/", "clear");
    // MISC
    private boolean mark;

    public Tutorial() {
        super(1200, 800, 1);
        allies.add(hero);
        enemies.add(boss);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
    
        setupStats();

        map = new int[][] {
            {3, 3, 3, 0, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 3, 3, 3, 0, 3, 3},
            {3, 3, 3, 0, 0, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 0, 0, 0, 3, 3},
            {0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 3, 3, 3, 3, 0, 3, 3, 0, 0, 0, 3, 3, 3, 3, 0, 3, 3, 3, 0, 0, 3, 0, 0},
            {3, 3, 3, 0, 3, 3, 0, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3}
        };
        initializeGrid();

        addObject(hero, GameWorld.getX(4), GameWorld.getY(8));
        //map[8][4] = 1; // BattleWorldCharacter addedToWorld() may be too slow to set coordinates
        addObject(boss, GameWorld.getX(21), GameWorld.getY(8));
        addObject(bossIcon, GameWorld.getX(21), GameWorld.getY(8));
        
        addObject(e1, GameWorld.getX(19), GameWorld.getY(6));
        addObject(e2, GameWorld.getX(19), GameWorld.getY(7));
        addObject(e3, GameWorld.getX(19), GameWorld.getY(9));
        addObject(e4, GameWorld.getX(19), GameWorld.getY(10));

        addObject(oldManD, 0, 0);
    }

    public void act() {
        super.act();
        if (turns == 1 && !mark) {
            addObject(prodeusD, 0, 0);
            hero.weapons.add("Sword");
            hero.weapons.add("Spear");
            hero.weapons.add("Bow");
            hero.weapon = "Sword";
            addObject(prodeus, GameWorld.getX(1), GameWorld.getY(7));
            allies.add(prodeus);
            mark = true;
        }
        checkClear();
    }
    
    public void setupStats() {
        // ALLIES
        hero.weapons.clear();
        hero.weapons.add("Fists");
        hero.maxHealth = hero.health = 20;
        hero.atk = 6;
        prodeus.maxHealth = prodeus.health = 45;
        prodeus.atk = 12;
        prodeus.def = 10;
        
        // ENEMIES
        boss.isBoss = true;
        boss.name = "Foot Soldier Boss";
        for (Enemy e : enemies) {
            if (e.isBoss) { // first check if boss since boss is also a foot soldier
                e.maxHealth = e.health = 15;
                e.atk = 6;
                e.def = 2;
            }
            else if (e instanceof EnemyFootSoldier) {
                e.maxHealth = e.health = 9;
                e.atk = 2;
            }
        }
    }
    
    public void checkClear() {
        if (enemies.size() == 0 && state != "clear") {
            state = "clear";
            addObject(oldManD2, 0, 0);
        }
        if (oldManD2.i == oldManD2.dialogues.size()) {
            Greenfoot.setWorld(new Level1());
        }
    }
}

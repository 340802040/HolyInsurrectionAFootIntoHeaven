import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends BattleWorldCharacter
{
    // DATA
    private Ally target; // AI will determine which target enemy chooses to attack
    private boolean willAttack; // if enemy can reach its target within its move limit, willAttack will be true
    protected int hitXp, killXp; // xp rewarded for hitting 
    protected boolean isBoss;
    // MOVEMENT
    private int endIndex; // for movement
    // DEATH ANIMATION
    private boolean isFlashing, flip; // if enemy about to attack, flash as an indicator
    private int j = 0;

    public Enemy(int level) {
        this.level = level;
        name = getName();
        levelUp(level);
        hitXp = 10000;
        killXp = 200;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        map[r][c] = 2;
    }

    public void act() {
        super.act();
        if (isMoving) {
            move();
            walkAnimate();
        }
        else {
            idleAnimate();
        }
        if (isFlashing) {
            flash();
        }
    }

    public String getName() {
        String s = this.getClass().getSimpleName();
        String[] r = s.split("(?=\\p{Upper})");
        String ret = "";
        for (String ss : r) {
            ret += ss + " ";
        }
        
        return ret;
    }

    public void startMoving() {
        getTargetAlly();
        checkPath();
        if (pathPossible) {
            isMoving = true;    
        }
        i = path.size() - 1;
        if (path.size() <= moveLimit) {
            endIndex = -1;
            willAttack = true;
        }
        else {
            endIndex = path.size() - moveLimit - 1;
            willAttack = false;
        }
        prevLocation = new Point(r, c);
        map[r][c] = 0; // clear spot
    }

    public void getTargetAlly() { // for now just gets a random ally
        ArrayList<Ally> allies = ((BattleWorld)getWorld()).allies;
        target = null;
        String counter = "";
        for (Ally a : allies) {
            switch (a.weapon) {
                case "Sword":
                    counter = "Bow";
                    break;
                case "Spear":
                    counter = "Sword";
                    break;
                case "Bow":
                    counter = "Spear";
                    break;
                case "Fire":
                    counter = "Water";
                    break;
                case "Water":
                    counter = "Ice";
                    break;
                case "Ice":
                    counter = "Fire";
                    break;
            }

            if (weapons.contains(counter)) {
                target = a;
                weapon = counter;
                break;
            }
        }

        if (target == null) { // if no counter
            target = allies.get(Greenfoot.getRandomNumber(allies.size()));
            weapon = weapons.get(0);
        }
    }

    public void checkPath() {
        // find shortest path from Ally to selector
        map = ((GameWorld)getWorld()).getMap();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean[][] vis = new boolean[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH];
        Point start = new Point(r, c);
        Queue<Point> Q = new LinkedList<Point>();
        Point[][] prev = new Point[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH]; // keeps track of nodes in shortest path

        Q.add(start);
        vis[start.r][start.c] = true;
        pathPossible = false;

        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            if (cur.r == target.getR() && cur.c == target.getC()) { // if node is target ally
                getPath(start, prev[cur.r][cur.c], prev);
                pathPossible = true;
                break;
            }
            else if (map[cur.r][cur.c] == 1) { // if node is any other ally, ignore
                continue;
            }

            for (int j = 0; j < 4; j++) { // checks 4 cardinal offsets
                int nr = cur.r + dy[j], nc = cur.c + dx[j];
                if (nc >= 0 && nc < GameWorld.GRID_WIDTH && nr >= 0 && nr < GameWorld.GRID_HEIGHT && !vis[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == 1)) {
                    Q.add(new Point(nr, nc));
                    vis[nr][nc] = true;
                    prev[nr][nc] = cur;
                }
            }
        }
    }

    /**
     * Stores the path from selectedAlly to current selector position.
     */
    public void getPath(Point start, Point end, Point[][] prev) {
        path.clear(); 
        while (end.r != start.r || end.c != start.c) {
            path.add(end);
            end = prev[end.r][end.c];
        }
    }

    public void move() {
        if (i == endIndex) {
            BattleWorld bw = ((BattleWorld)getWorld());
            isMoving = false;
            moved = true;
            map[r][c] = 2;
            getImage().setTransparency(150);

            if (willAttack) {
                ((BattleWorld)getWorld()).state = "other";
                isFlashing = true;
            }

            return;
        }

        Point p = path.get(i);
        if (moveTimer.millisElapsed() > 200) {
            prevLocation = new Point(r, c);
            setLocation(GameWorld.getX(p.c), GameWorld.getY(p.r));
            updateCoords();
            checkDirection();
            i--;
            moveTimer.mark();
        }
    }

    public void levelUp(int amount) {
        for (int i = 0; i < amount; i++) {
            int numStatIncreases = GameWorld.getRandomNumberInRange(1, potentialStats.size() + 1);
            List<String> upgradedStats = GameWorld.pickNRandom(potentialStats, numStatIncreases);
            int increase = 2;
            for (String s : upgradedStats) {
                switch (s) {
                    case "health":
                        maxHealth += increase;
                        health += increase;
                        break;
                    case "atk":
                        atk += increase;
                        break;
                    case "def":
                        def += increase;
                        break;
                    case "ev":
                        ev += increase;
                        break;
                    case "spd":
                        spd += increase;
                        break;
                }
            }
        }
    }

    public void flash() {
        if (j == 11) {
            Greenfoot.delay(40);
            BattleWorld bw = (BattleWorld)getWorld();
            Greenfoot.setWorld(new AttackAnimationWorld(bw, target, this, "enemy"));
            isFlashing = false;
            j = 0;
        }
        if (actCount % 10 == 0) {
            if (flip) {
                getImage().setTransparency(100);    
            }
            else {
                getImage().setTransparency(255);    
            }
            flip = !flip;
            j++;
        }
    }

    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH && map[r][c] == 0;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends BattleWorldCharacter
{
    private int endIndex; // for movement
    private int dir; // 0 - 3 representing each cardinal direction
    private Ally target; // AI will determine which target enemy chooses to attack

    public Enemy(int speed) {
        this.speed = speed;
        setImage("placeholder/enemy.png");
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        map[r][c] = 2;
    }

    public void act() {
        super.act();
        if (isMoving) {
            move();
        }
    }

    public void startMoving() {
        getTargetAlly();
        checkPath();
        if (pathPossible) {
            isMoving = true;    
        }
        i = path.size() - 1;
        endIndex = path.size() <= speed ? -1 : (path.size() - speed);
        map[r][c] = 0; // clear spot
    }

    public void getTargetAlly() { // for now just gets a random ally
        ArrayList<Ally> allies = ((BattleWorld)getWorld()).getAllies();
        target = allies.get(Greenfoot.getRandomNumber(allies.size()));
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
            bw.enemiesMoved++;
            getImage().setTransparency(150);
            
            return;
        }
        
        Point p = path.get(i);
        if (moveTimer.millisElapsed() > 80) {
            setLocation(GameWorld.getX(p.c), GameWorld.getY(p.r));
            i--;
            moveTimer.mark();
        }
    }

    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH && map[r][c] == 0;
    }
}

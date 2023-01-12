import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Selector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selector extends Actor
{
    private int r, c;
    private SimpleTimer moveTimer = new SimpleTimer();
    private boolean active; // whether the selector has selected an ally
    private Image selectionIndicator;
    private Ally selectedAlly;

    public Selector() {
        r = 0;
        c = 0;
        active = false;
        setImage("selector.png");
        selectionIndicator = new Image("selector.png");
    }

    public void act() {
        checkMovement();
        checkSelect();
        checkDeselect();
    }

    public void checkMovement() {
        if (moveTimer.millisElapsed() > 120) {
            if (Greenfoot.isKeyDown("w") && canMoveTo(r - 1, c)) {
                r--;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    highlightPath();    
                }
            }
            else if (Greenfoot.isKeyDown("a") && canMoveTo(r, c - 1)) {
                c--;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    highlightPath();    
                }
            }
            else if (Greenfoot.isKeyDown("s") && canMoveTo(r + 1, c)) {
                r++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    highlightPath();    
                }
            }
            else if (Greenfoot.isKeyDown("d") && canMoveTo(r, c + 1)) {
                c++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    highlightPath();    
                }
            }

            moveTimer.mark();
        }
    }

    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH;
    }

    public void checkSelect() {
        if (!active && Greenfoot.isKeyDown("space") && getOneIntersectingObject(Ally.class) != null) {
            active = true;
            getWorld().addObject(selectionIndicator, GameWorld.getX(c), GameWorld.getY(r));
            selectedAlly = (Ally)getOneIntersectingObject(Ally.class);
        }
    }

    /**
     * Checks if user deselected their current ally.
     */
    public void checkDeselect() {
        if (active && Greenfoot.isKeyDown("b")) {
            active = false;
            getWorld().removeObject(selectionIndicator);
            selectedAlly = null;
            removeHighlight();
        }
    }

    /**
     * If selector is active, highlights the path from selected Ally to current selector location. 
     * This shows the user how many tiles it will take them to get to their desired location.
     */
    public void highlightPath() {   
        // find shortest path from Ally to selector
        int[][] map = ((GameWorld)getWorld()).getMap();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int[][] dis = new int[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH];
        boolean[][] vis = new boolean[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH];
        Point start = new Point(selectedAlly.getR(), selectedAlly.getC());
        Queue<Point> Q = new LinkedList<Point>();
        Point[][] prev = new Point[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH]; // keeps track of nodes in shortest path

        Q.add(start);
        vis[start.c][start.r] = true;
        dis[start.c][start.r] = 0;
        prev[start.c][start.r] = new Point(-1, -1);

        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            if (cur.r == r && cur.c == c) {
                break;
            }
            for (int j = 0; j < 4; j++) { // checks 4 cardinal offsets
                int nc = cur.c + dy[j], nr = cur.r + dx[j];
                if (nc >= 0 && nc < GameWorld.GRID_WIDTH && nr >= 0 && nr < GameWorld.GRID_HEIGHT && !vis[nr][nc] && map[nr][nc] == 0) {
                    Q.add(new Point(nr, nc));
                    vis[nr][nc] = true; 
                    prev[nr][nc] = cur;
                }
            }
        }

        // highlight path
        Point p = new Point(r, c);
        while (prev[p.r][p.c].r != -1) {
            getWorld().addObject(new BlueHighlight(), GameWorld.getX(p.c), GameWorld.getX(p.r));
            p = prev[p.r][p.c];
        }
    }

    public void removeHighlight() {
        // remove all BlueHighlight's from the world
        List<BlueHighlight> l = getWorld().getObjects(BlueHighlight.class);
        for (BlueHighlight b : l) {
            getWorld().removeObject(b);
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }   
}

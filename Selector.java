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
    private SimpleTimer animationTimer = new SimpleTimer();
    private GreenfootImage[] selectionFrames = new GreenfootImage[2];
    private int imageIndex = 0;
    private boolean active; // whether the selector has selected an ally
    private Ally selectedAlly;
    private ArrayList<Point> path = new ArrayList<Point>();
    private SimpleTimer timer = new SimpleTimer();

    public Selector() {
        r = 0;
        c = 0;
        active = false;
        
        for(int i = 0; i < 2; i++) {
            selectionFrames[i] = new GreenfootImage("images/Animations/Selector/Selector0" + i + ".png");
        }

        setImage("images/Animations/Selector/Selector00.png");
    }

    public void act() {
        checkMovement();
        checkSelect();
        checkDeselect();
        checkConfirmMove();
        checkHovering();
    }

    public void checkMovement() {
        if (moveTimer.millisElapsed() > 90) {
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

    public void checkHovering() {   // Animate selector if hovering
        if(getOneIntersectingObject(Ally.class) != null) {
            animateSelector();
        }
        else {
            setImage("images/Animations/Selector/Selector00.png");
        }
    }
    
    public void animateSelector() {
        if(animationTimer.millisElapsed() < 200) {
            return;
        }
        animationTimer.mark();

        setImage(selectionFrames[imageIndex]);
        imageIndex = (imageIndex + 1) % selectionFrames.length;
    }
    
    public void checkSelect() {
        if (!active && Greenfoot.isKeyDown("space") && getOneIntersectingObject(Ally.class) != null) {
            active = true;
            
            selectedAlly = (Ally)getOneIntersectingObject(Ally.class);
            timer.mark();
        }
    }

    /**
     * Checks if user deselected their current ally.
     */
    public void checkDeselect() {
        if (active && Greenfoot.isKeyDown("b")) {
            deselect();
        }
    }
    
    public void deselect() {
        active = false;
        selectedAlly = null;
        removeHighlight();
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
        //System.out.println(selectedAlly.getR() + " " + selectedAlly.getC());
        Queue<Point> Q = new LinkedList<Point>();
        Point[][] prev = new Point[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH]; // keeps track of nodes in shortest path

        Q.add(start);
        vis[start.c][start.r] = true;
        dis[start.c][start.r] = 0;

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
        
        // store and highlight path
        Point p = new Point(r, c);
        path.clear();
        while (p.r != start.r || p.c != start.c) {
            path.add(p);
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
    
    /**
     * Checks if user has confirmed his location to move an Ally to.
     */
    public void checkConfirmMove() {
        if (timer.millisElapsed() > 1000 && active && Greenfoot.isKeyDown("space")) {
            selectedAlly.startMoving(path);
            deselect();
            Greenfoot.delay(30);
        }
    }
}

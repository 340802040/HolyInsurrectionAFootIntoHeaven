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
    protected int r, c;
    private SimpleTimer moveTimer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    private GreenfootImage[] selectionFrames = new GreenfootImage[2];
    private int imageIndex = 0;
    private boolean active = false; // whether the selector has selected an ally
    private Ally selectedAlly;
    private ArrayList<Point> path = new ArrayList<Point>();
    private boolean pathPossible;
    private SimpleTimer timer = new SimpleTimer(), timer2 = new SimpleTimer();
    private Image selectionIndicator;
    private int[][] map;

    public Selector() {        
        for(int i = 0; i < 2; i++) {
            selectionFrames[i] = new GreenfootImage("images/Animations/Selector/Selector0" + i + ".png");
        }
        setImage("images/Animations/Selector/Selector00.png");        
        selectionIndicator = new Image("pink-selector.png");
        timer2.mark();
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
                    checkPath();    
                }
            }
            else if (Greenfoot.isKeyDown("a") && canMoveTo(r, c - 1)) {
                c--;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    checkPath();    
                }
            }
            else if (Greenfoot.isKeyDown("s") && canMoveTo(r + 1, c)) {
                r++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    checkPath();       
                }
            }
            else if (Greenfoot.isKeyDown("d") && canMoveTo(r, c + 1)) {
                c++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                if (active) {
                    removeHighlight();
                    checkPath();    
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
        Ally a = (Ally)getOneIntersectingObject(Ally.class);
        if (timer2.millisElapsed() > 500 && !active && Greenfoot.isKeyDown("space") && a != null && !a.moved) {
            active = true;
            getWorld().addObject(selectionIndicator, GameWorld.getX(c), GameWorld.getY(r));
            selectedAlly = (Ally)getOneIntersectingObject(Ally.class);
            timer.mark();
            checkPath();
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
        getWorld().removeObject(selectionIndicator);
        selectedAlly = null;
        removeHighlight();
    }

    /**
     * If selector is active, highlights the path from selected Ally to current selector location. 
     * This shows the user how many tiles it will take them to get to their desired location.
     */
    public void checkPath() {   
        // find shortest path from Ally to selector
        map = ((GameWorld)getWorld()).getMap();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean[][] vis = new boolean[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH];
        Point start = new Point(selectedAlly.getR(), selectedAlly.getC());
        Queue<Point> Q = new LinkedList<Point>();
        Point[][] prev = new Point[GameWorld.GRID_HEIGHT][GameWorld.GRID_WIDTH]; // keeps track of nodes in shortest path

        Q.add(start);
        vis[start.r][start.c] = true;
        pathPossible = false;

        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            if (cur.r == r && cur.c == c && map[cur.r][cur.c] == 2) { // if destination is enemy
                getPath(start, prev[cur.r][cur.c], prev);
                if (path.size() <= selectedAlly.getSpeed()) {
                    highlightPath();
                    getWorld().addObject(new Highlight("red-highlight.png"), GameWorld.getX(c), GameWorld.getY(r));   
                    pathPossible = true;
                }
                break;
            }
            else if (map[cur.r][cur.c] == 2) { // ignore enemy cell if selector not on one
                continue;
            }
            else if (cur.r == r && cur.c == c && !(cur.r == start.r && cur.c == start.c)) { // if destination is empty tile and not ally itself
                getPath(start, cur, prev);
                if (path.size() <= selectedAlly.getSpeed()) {
                    highlightPath();
                    pathPossible = true;
                }
                break;
            }

            for (int j = 0; j < 4; j++) { // checks 4 cardinal offsets
                int nr = cur.r + dy[j], nc = cur.c + dx[j];
                if (nc >= 0 && nc < GameWorld.GRID_WIDTH && nr >= 0 && nr < GameWorld.GRID_HEIGHT && !vis[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == 2)) {
                    // add enemy cells into the queue but ignore them when polled unless selector is currently on an enemy
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

    public void highlightPath() {
        for (Point coord : path) {
            getWorld().addObject(new Highlight("blue-highlight.png"), GameWorld.getX(coord.c), GameWorld.getY(coord.r));   
        }
    }

    public void removeHighlight() {
        // remove all BlueHighlight's from the world
        List<Highlight> l = getWorld().getObjects(Highlight.class);
        for (Highlight h : l) {
            getWorld().removeObject(h);
        }
    }

    /**
     * Checks if user has confirmed his location to move an Ally to.
     */
    public void checkConfirmMove() {
        if (timer.millisElapsed() > 500 && active && Greenfoot.isKeyDown("space") && pathPossible) {
            selectedAlly.startMoving(path);
            deselect();
            timer2.mark();
        }
    }
}

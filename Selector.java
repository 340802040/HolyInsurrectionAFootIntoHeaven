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
    // DATA
    protected int r, c;
    private boolean active; // whether the selector has selected an ally
    private Ally selectedAlly;
    // PATH FINDING
    private ArrayList<Point> path = new ArrayList<Point>();
    private boolean pathPossible;   
    private int[][] map;
    // ANIMATION
    private SimpleTimer animationTimer = new SimpleTimer();
    private GreenfootImage[] selectionFrames = new GreenfootImage[2];
    private Image selectionIndicator = new Image("PinkSelector.png");;
    private int imageIndex = 0;    
    // MISC
    private SimpleTimer moveTimer = new SimpleTimer();
    private SimpleTimer timer = new SimpleTimer(), timer2 = new SimpleTimer();

    public Selector() {        
        for(int i = 0; i < 2; i++) {
            selectionFrames[i] = new GreenfootImage("images/Animations/Selector/Selector0" + i + ".png");
        }
        setImage("images/Animations/Selector/Selector00.png");        
        timer2.mark();
    }
    
    public void addedToWorld(World w) {
        r = GameWorld.getYCell(getY());
        c = GameWorld.getXCell(getX());
    }

    public void act() {
        checkMovement();
        checkSelect();
        checkDeselect();
        checkConfirmMove();
        checkHovering();
    }

    public void checkMovement() {
        if (moveTimer.millisElapsed() > 100) {
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

    public void checkHovering() {   // Animate selector if hovering
        if (getOneIntersectingObject(Ally.class) != null) {
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
        BattleWorld bw = (BattleWorld)getWorld();
        Ally a = (Ally)getOneIntersectingObject(Ally.class);
        BattleWorldCharacter bwc = (BattleWorldCharacter)getOneIntersectingObject(BattleWorldCharacter.class);
        
        if (timer2.millisElapsed() > 500 && !active && Greenfoot.isKeyDown("k") && a != null && !a.moved) { // ally selected
            active = true;
            bw.addObject(selectionIndicator, GameWorld.getX(c), GameWorld.getY(r));
            selectedAlly = (Ally)getOneIntersectingObject(Ally.class);
            timer.mark();
            checkPath();
        }
        else if (timer2.millisElapsed() > 500 && !active && Greenfoot.isKeyDown("k") && a == null) { // ground selected to end turn
            bw.state = "decision";
            bw.addObject(new EndTurnWindow(selectedAlly), bw.getWidth() - 220, bw.getHeight() / 2);
        }
        else if (timer2.millisElapsed() > 500 && !active && Greenfoot.isKeyDown("u") && bwc != null) { // show stats
            bw.state = "decision";
            bw.addObject(new StatWindow(bwc), bw.getWidth() / 2, bw.getHeight() / 2);
        }
    }

    /**
     * Checks if user deselected their current ally.
     */
    public void checkDeselect() {
        if (active && Greenfoot.isKeyDown("j")) {
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
     * Checks if there is a possible path from the selected ally to current selector(cursor) location.
     * If yes, highlights the shortest path.
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
            if (cur.r == r && cur.c == c && map[r][c] == 2) { // if node is enemy and selector is on one (user wishes to attack)
                // get path from ally to just 1 tile before the enemy
                getPath(start, prev[cur.r][cur.c], prev);
                if (path.size() <= selectedAlly.moveLimit) {
                    highlightPath();
                    getWorld().addObject(new Highlight("RedHighlight.png"), GameWorld.getX(c), GameWorld.getY(r));   
                    pathPossible = true;
                }
                break;
            }
            else if (map[cur.r][cur.c] == 2) { // if node is enemy but selector is not on one, ignore (path should not include enemies)
                continue;
            }
            else if (cur.r == r && cur.c == c /*!(cur.r == start.r && cur.c == start.c)*/) { // if destination is empty tile
                getPath(start, cur, prev);
                if (path.size() <= selectedAlly.moveLimit) {
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
            getWorld().addObject(new Highlight("BlueHighlight.png"), GameWorld.getX(coord.c), GameWorld.getY(coord.r));   
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
        if (timer.millisElapsed() > 500 && active && Greenfoot.isKeyDown("k") && pathPossible) {
            if (map[r][c] == 2) {
                selectedAlly.selectedEnemy = (Enemy)getOneIntersectingObject(Enemy.class);
            }
            selectedAlly.startMoving(path);
            deselect();
            timer2.mark();
        }
    }
    
    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends BattleWorldCharacter
{
    private int speed;
    private int i; // index for movement
    protected boolean isMoving = false;
    protected boolean moved = false;
    private SimpleTimer moveTimer = new SimpleTimer();
    private int dir; // 0 - 3 representing each cardinal direction

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
        isMoving = true;
        map[r][c] = 0; // clear spot
        i = Greenfoot.getRandomNumber(speed) + 4;
    }

    public void move() {
        if (i == -1) {
            isMoving = false;
            moved = true;
            map[r][c] = 2;
            ((BattleWorld)getWorld()).increaseEnemiesMoved();
            getImage().setTransparency(150);
            return;
        }

        if (moveTimer.millisElapsed() > 250) {
            boolean flag = true;
            while (flag) {
                // chance to switch direction
                
                if (Greenfoot.getRandomNumber(3) == 0) {
                    dir = Greenfoot.getRandomNumber(4);    
                }
                switch (dir) {
                    case 0: // up
                        if (canMoveTo(r - 1, c)) {
                            r--;
                            setLocation(GameWorld.getX(c), GameWorld.getY(r));
                            flag = false;
                        }
                        break;
                    case 1: // right
                        if (canMoveTo(r, c + 1)) {
                            c++;
                            setLocation(GameWorld.getX(c), GameWorld.getY(r));
                            flag = false;
                        }
                        break;
                    case 2: // down
                        if (canMoveTo(r + 1, c)) {
                            r++;
                            setLocation(GameWorld.getX(c), GameWorld.getY(r));
                            flag = false;
                        }
                        break;
                    case 3: // left
                        if (canMoveTo(r, c - 1)) {
                            c--;
                            setLocation(GameWorld.getX(c), GameWorld.getY(r));
                            flag = false;
                        }
                        break;
                }
            }
            
            i--;
            moveTimer.mark();
        }
    }

    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH && map[r][c] == 0;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OverworldPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverworldPlayer extends Actor
{
    private int r, c;
    private SimpleTimer moveTimer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    protected GreenfootImage[] walkFrames = new GreenfootImage[7];
    private int imageIndex = 0;
    
    public OverworldPlayer() {
        r = 0;
        c = 0;
        moveTimer = new SimpleTimer();        

        for(int i = 0; i < 7; i++) {
            walkFrames[i] = new GreenfootImage("images/Animations/PlayerOverworld/PlayerO0" + i + ".png");
        }
        
        setImage("images/Animations/PlayerOverWorld/PlayerO00.png");
    }

    public void act() {
        checkWASDMovement();
    }
    
    public void checkWASDMovement() {
        if (moveTimer.millisElapsed() > 150) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(GameWorld.getX(c), GameWorld.getY(r));
                    setRotation(0);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(GameWorld.getX(c), GameWorld.getY(r));
                    setRotation(270);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(GameWorld.getX(c), GameWorld.getY(r));
                    setRotation(180);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(GameWorld.getX(c), GameWorld.getY(r));
                    setRotation(90);
                    animateWalkCharacter();
                }
            }

            moveTimer.mark();
        }
    }
    
    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < GameWorld.GRID_HEIGHT && c >= 0 && c < GameWorld.GRID_WIDTH;
    }
    
    public void animateWalkCharacter() {
        if(animationTimer.millisElapsed() < 90) {
            return;
        }
        animationTimer.mark();

        setImage(walkFrames[imageIndex]);
        imageIndex = (imageIndex + 1) % walkFrames.length;
    }
}

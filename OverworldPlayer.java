import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OverworldPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverworldPlayer extends Actor
{
    private int r = 0, c = 0;
    private SimpleTimer moveTimer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    protected GreenfootImage[] walkFrames = new GreenfootImage[7];
    private int imageIndex = 0;

    public OverworldPlayer() {    
        for(int i = 0; i < 7; i++) {
            walkFrames[i] = new GreenfootImage("images/Animations/PlayerOverworld/PlayerO0" + i + ".png");
        }

        setImage("images/Animations/PlayerOverWorld/PlayerO00.png");
    }

    public void act() {
        checkWASDMovement();
    }

    public void checkWASDMovement() {
        if (Greenfoot.isKeyDown("w") && canMoveTo(r - 1, c)) {
            if (moveTimer.millisElapsed() > 150) {
                r--;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                moveTimer.mark();
            }
            setRotation(0);
            animateWalkCharacter();
        }
        else if (Greenfoot.isKeyDown("a") && canMoveTo(r, c - 1)) {
            if (moveTimer.millisElapsed() > 150) {
                c--;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                moveTimer.mark();
            }
            setRotation(270);
            animateWalkCharacter();
        }
        else if (Greenfoot.isKeyDown("s") && canMoveTo(r + 1, c)) {
            if (moveTimer.millisElapsed() > 150) {
                r++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                moveTimer.mark();
            }
            setRotation(180);
            animateWalkCharacter();
        }
        else if (Greenfoot.isKeyDown("d") && canMoveTo(r, c + 1)) {
            if (moveTimer.millisElapsed() > 150) {
                c++;
                setLocation(GameWorld.getX(c), GameWorld.getY(r));
                moveTimer.mark();
            }
            setRotation(90);
            animateWalkCharacter();
        }
        else {
            setImage("images/Animations/PlayerOverWorld/PlayerO00.png");
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
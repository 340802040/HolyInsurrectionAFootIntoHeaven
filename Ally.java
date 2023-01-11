import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ally here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ally extends Actor
{
    protected int r, c; // row and column player is currently on
    protected SimpleTimer moveTimer;
    

    public Ally() {
        
    }
    
    public void act() {
    
    }
    
    public void checkMovement() {
        if (moveTimer.millisElapsed() > 150) {
            if (Greenfoot.isKeyDown("w")) {
                if (canMoveTo(r - 1, c)) {
                    r--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                    setRotation(0);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("a")) {
                if (canMoveTo(r, c - 1)) {
                    c--;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                    setRotation(270);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("s")) {
                if (canMoveTo(r + 1, c)) {
                    r++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                    setRotation(180);
                    animateWalkCharacter();
                }
            }
            else if (Greenfoot.isKeyDown("d")) {
                if (canMoveTo(r, c + 1)) {
                    c++;
                    setLocation(Overworld.getXCoordinate(c), Overworld.getYCoordinate(r));
                    setRotation(90);
                    animateWalkCharacter();
                }
            }
            else {
                setImage("images/Animations/PlayerOverworld/PlayerO00.png");
            }
            
            moveTimer.mark();
        }
    }
    
    public boolean canMoveTo(int r, int c) {
        return r >= 0 && r < Overworld.GRID_HEIGHT && c >= 0 && c < Overworld.GRID_WIDTH;
    }
    
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage[] walk = new GreenfootImage[7];
    int imageIndex = 0;
    public void animateWalkCharacter() {
        if(animationTimer.millisElapsed() < 90)
        {
            return;
        }
        animationTimer.mark();
        
        setImage(walk[imageIndex]);
        imageIndex = (imageIndex + 1) % walk.length;
    }
}

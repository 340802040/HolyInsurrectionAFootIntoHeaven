import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartSwordShineEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartSwordShineEffect extends Actor
{
    private SimpleTimer animationTimer = new SimpleTimer();
    private SimpleTimer timer = new SimpleTimer();
    protected GreenfootImage[] shineFrames = new GreenfootImage[13];
    private boolean marked = false;
    private int imageIndex = 0;
    World cutscene = new Cutscene();
    
    public StartSwordShineEffect() {
        for(int i = 0; i < 13; i++) {
            if(i >= 10) {
                shineFrames[i] = new GreenfootImage("images/Animations/ShineAnimations/Shine0" + i + ".png");
            }
            else {
                shineFrames[i] = new GreenfootImage("images/Animations/ShineAnimations/Shine00" + i + ".png");
            }
        }
        
        setImage("images/Animations/ShineAnimations/Shine000.png");
    }
    
    public void act()
    {
        if(!marked) {
            timer.mark();
            marked = true;
        }
        animateShine();
        if(timer.millisElapsed() > 2050) {
            Greenfoot.setWorld(cutscene);
        }
    }
    
    public void animateShine() {
        if(animationTimer.millisElapsed() < 150) {
            return;
        }
        animationTimer.mark();

        setImage(shineFrames[imageIndex]);
        imageIndex = (imageIndex + 1) % shineFrames.length;
    }
}

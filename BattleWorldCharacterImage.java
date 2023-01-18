import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image of a battle world character used in AttackAnimationWorld
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleWorldCharacterImage extends Image
{
    private int actCount = 0;
    protected boolean isFlashing, isFading, flip;
    protected boolean isAnimating, isDying, finished; // whether it has finished all its attacks and animations such as dying
    private int j = 0;
    private String path;

    public BattleWorldCharacterImage(String path) {
        super(path);
        this.path = path;
    }

    public void act() {
        actCount++;
        if (isFlashing) {
            flash();
        }
        if (isFading) {
            fade();
        }
    }

    public void die() {
        isFlashing = true;
        isDying = true;
    }

    public void flash() {
        if (j == 7) {
            isFlashing = false;
            isFading = true;
        }
        if (actCount % 10 == 0) {
            if (flip) {
                getImage().setTransparency(100);    
            }
            else {
                getImage().setTransparency(255);    
            }
            flip = !flip;
            j++;
        }
    }

    public void fade() {
        int newTrans = getImage().getTransparency() - 5;
        if (newTrans <= 0) {
            isFading = false;
            finished = true;
        }
        if (actCount % 3 == 0 && newTrans >= 0) {
            getImage().setTransparency(newTrans);
        }
    }
}

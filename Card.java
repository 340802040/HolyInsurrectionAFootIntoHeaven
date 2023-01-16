import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Card is an introduction or visual that covers most of the screen and
 * notifies the player of a change in the game state or setting.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Card extends Actor
{
    protected int actCount = 0;
    protected boolean isFadingIn = true;
    
    /**
     * Creates a card of an image.
     */
    public Card(String path) {
        setImage(path);
        getImage().setTransparency(0);
    }
    
    /**
     * Creates a card of just text.
     */
    public Card(TextImage ti) {
        setImage(ti);
    }
    
    public void act() {
        actCount++;
        if (isFadingIn) {
            fadeIn();
        }
        else {
            fadeOut();
        }
    }    
    
    public void fadeIn() {
        int newTrans = getImage().getTransparency() + 15;
        
        if (newTrans >= 255) {
            isFadingIn = false;
        }
        if (actCount % 4 == 0 && newTrans <= 255) {
            getImage().setTransparency(newTrans);
        }
    }
    
    public void fadeOut() {
        int newTrans = getImage().getTransparency() - 15;
        if (newTrans == 0) {
            getWorld().removeObject(this);
        }
        if (actCount % 4 == 0 && newTrans >= 0) {
            getImage().setTransparency(newTrans);
        }
    }
}

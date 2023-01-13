import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Card is an introduction or visual that covers most of the screen and
 * notifies the player of a change in the game state or setting.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    private int actCount = 0;
    private boolean isFadingIn = true;
    
    public Card(String path) {
        setImage(path);
        getImage().setTransparency(0);
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
        int newTrans = getImage().getTransparency() + 5;
        if (newTrans == 255) {
            isFadingIn = false;
        }
        if (actCount % 6 == 0 && newTrans <= 255) {
            getImage().setTransparency(newTrans);
        }
    }
    
    public void fadeOut() {
        int newTrans = getImage().getTransparency() - 5;
        
        if (actCount % 6 == 0 && newTrans >= 0) {
            getImage().setTransparency(newTrans);
        }
    }
}

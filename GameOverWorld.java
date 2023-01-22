import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends GameWorld
{
    protected Image mainMenuButton = new Image("Buttons/MainMenuButton.png");
    protected boolean isHovering;
    protected GreenfootImage bg = new GreenfootImage("DeathScreen.png");
    
    public GameOverWorld() {
        super(1200, 800, 1);
        setBackground(bg);       
        mainMenuButton.getImage().setTransparency(190);
        addObject(mainMenuButton, 400, 300);
    }
    
    public void act() {
        actCount++;
        fadeIn();
        checkHovering();
        checkClick();
    }
    
    public void fadeIn() {
        int newTrans = bg.getTransparency() + 15;
        if (newTrans >= 255) {
            return;
        }
        if (actCount % 5 == 0 && newTrans <= 255) {
            bg.setTransparency(newTrans);
        }
    }
    
    public void checkHovering() {
        if (Greenfoot.mouseMoved(mainMenuButton) && !isHovering) {
            mainMenuButton.getImage().setTransparency(255);
            isHovering = true;
        }
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(mainMenuButton)) {
            mainMenuButton.getImage().setTransparency(190);
            isHovering = false;
        }
    }
    
    public void checkClick() {
        if (Greenfoot.mouseClicked(mainMenuButton)) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

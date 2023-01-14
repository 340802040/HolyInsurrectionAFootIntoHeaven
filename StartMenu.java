import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends World
{
    // Initialize variables
    Image playButton = new Image("images/Buttons/PlayButton.png");
    Image selector = new Image("images/TitleSelector.png");
    private boolean added = false;
    
    /**
     * Constructor for objects of class StartMenu.
     * 
     */
    public StartMenu() {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        
        setBackground("images/TitleScreen.png");

        // Add Buttons
        addObject(playButton, 100, 300);
    }
    
    public void act() {
        checkHovering();
    }
    
    public void checkHovering() {
        if(Greenfoot.mouseMoved(playButton) && !added) {
            addObject(selector, 100, 300);
            added = true;
        }
        if(Greenfoot.mouseMoved(null) && added && !Greenfoot.mouseMoved(selector) && !Greenfoot.mouseMoved(playButton)) {
            removeObject(selector);
            added = false;
        }
    }
}

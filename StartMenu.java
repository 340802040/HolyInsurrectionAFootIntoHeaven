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
    Image albumButton = new Image("images/Buttons/AlbumButton.png");
    Image selector = new Image("images/TitleSelector.png");
    StartSwordShineEffect shine = new StartSwordShineEffect();
    private boolean added = false;
    private boolean onPlay = false;
    private boolean onAlbum = false;
    private boolean clicked = false;
    private SimpleTimer animationTimer = new SimpleTimer();
    protected GreenfootImage[] titleFrames = new GreenfootImage[5];
    private int imageIndex = 0;
    World musicWorld = new MusicMenu();
    

    /**
     * Constructor for objects of class StartMenu.
     * 
     */
    public StartMenu() {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 

        for(int i = 0; i < 5; i++) {
            titleFrames[i] = new GreenfootImage("images/Animations/TitleScreenAnimations/TitleScreen0" + i + ".png");
        }

        setBackground("images/TitleScreen.png");

        // Add Buttons
        addObject(playButton, 100, 300);
        addObject(albumButton, 100, 400);
        
        // Set soundtrack volume
        Soundtrack.setVolume();
    }

    public void act() {
        checkHovering();
        checkClick();
        animateTitleScreen();
    }

    public void checkHovering() {
        if(Greenfoot.mouseMoved(playButton) && !added) {
            addObject(selector, 100, 300);
            added = true;
            onPlay = true;
        }
        else if(Greenfoot.mouseMoved(albumButton) && !added) {
            addObject(selector, 100, 400);
            added = true;
            onAlbum = true;
        }
        if(Greenfoot.mouseMoved(null) && added && !Greenfoot.mouseMoved(selector) && !Greenfoot.mouseMoved(playButton) && !Greenfoot.mouseMoved(albumButton)) {
            removeObject(selector);
            added = false;
            onPlay = false;
            onAlbum = false;
        }
    }

    public void checkClick() {
        if(Greenfoot.mouseClicked(selector) && !clicked && onPlay) {
            clicked = true;
            addObject(shine, 600, 400);
        }
        if(Greenfoot.mouseClicked(selector) && !clicked && onAlbum) {
            Greenfoot.setWorld(musicWorld);
        }
    }

    public void animateTitleScreen() {
        if(animationTimer.millisElapsed() < 120) {
            return;
        }
        animationTimer.mark();

        setBackground(titleFrames[imageIndex]);
        imageIndex = (imageIndex + 1) % titleFrames.length;
    }
    
    public void stopped() {
        Soundtrack.stopAll();
    }
}

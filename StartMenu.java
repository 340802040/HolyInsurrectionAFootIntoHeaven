import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends GameWorld
{
    Image playButton = new Image("Buttons/PlayButton.png");
    Image albumButton = new Image("Buttons/AlbumButton.png");
    Image controlsButton = new Image("Placeholder/controls.png");
    Image selector = new Image("images/TitleSelector.png");
    StartSwordShineEffect shine = new StartSwordShineEffect();
    private boolean added = false;
    private boolean onPlay, onAlbum, onControls;
    private boolean clicked = false;
    private SimpleTimer animationTimer = new SimpleTimer();
    protected GreenfootImage[] titleFrames = new GreenfootImage[5];
    private int imageIndex = 0;
    protected static String controlsText;

    public StartMenu() {    
        super(1200, 800, 1); 

        for(int i = 0; i < 5; i++) {
            titleFrames[i] = new GreenfootImage("images/Animations/TitleScreenAnimations/TitleScreen0" + i + ".png");
        }

        state = "normal";
        setBackground("images/TitleScreen.png");

        // Add Buttons
        addObject(playButton, 150, 400);
        addObject(albumButton, 150, 480);
        addObject(controlsButton, 150, 560);

        // Set soundtrack volume
        Soundtrack.setVolume();
    }

    public void act() {
        if (state == "normal") {
            checkHovering();
            checkClick();    
        }
        animateTitleScreen();
    }

    public void checkHovering() {
        if(Greenfoot.mouseMoved(playButton) && !added) {
            addObject(selector, playButton.getX(), playButton.getY());
            added = true;
            onPlay = true;
        }
        else if(Greenfoot.mouseMoved(albumButton) && !added) {
            addObject(selector, albumButton.getX(), albumButton.getY());
            added = true;
            onAlbum = true;
        }
        else if(Greenfoot.mouseMoved(albumButton) && !added) {
            addObject(selector, albumButton.getX(), albumButton.getY());
            added = true;
            onControls = true;
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
            Greenfoot.setWorld(new MusicMenu());
        }
        if(Greenfoot.mouseClicked(selector) && !clicked && onControls) {
            Font font = new Font("Candara", true, false, 45);
            StatWindow sw = new StatWindow(controlsText, font, Color.YELLOW, Color.BLACK, 255, state);
            state = "interface";
            addObject(sw, getWidth() / 2, getHeight() / 2);
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

    public static void initControlsText() {
        controlsText = ""; // reset static variable
        String whitespace = "     ";
        controlsText = "\n" + whitespace + "Controls" + whitespace + "\n";
        controlsText += whitespace + "K - Select" + whitespace + "\n";
        controlsText += whitespace + "J - Back" + whitespace + "\n";
        controlsText += whitespace + "U - Stats" + whitespace + "\n";
        controlsText += whitespace + "WASD - Movement" + whitespace + "\n";
        controlsText += whitespace + "Z - Attack" + whitespace + "\n";
        controlsText += whitespace + "C - Wait" + whitespace + "\n";
        controlsText += whitespace + "1-6 - Select weapon" + whitespace + "\n \n";
    }

    public void stopped() {
        Soundtrack.stopAll();
    }
}

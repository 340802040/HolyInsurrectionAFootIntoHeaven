import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class StartMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends GameWorld
{
    // ANIMATION
    protected StartSwordShineEffect shine = new StartSwordShineEffect();
    private SimpleTimer animationTimer = new SimpleTimer();
    protected ArrayList<GreenfootImage> frames = Images.imgs.get("hero cape");
    private int imageIndex = 0;
    // BUTTONS
    protected Image playButton = new Image("Buttons/PlayButton.png");
    protected Image albumButton = new Image("Buttons/AlbumButton.png");
    protected Image controlsButton = new Image("Buttons/ControlsButton.png");
    protected Image loadButton = new Image("Buttons/LoadButton.png");
    protected Image selector = new Image("TitleSelector.png");
    // MISC
    private boolean added = false;
    private boolean onPlay, onAlbum, onControls, onLoad;
    protected static String controlsText;

    public MainMenu() {    
        super(1200, 800, 1);
        state = "normal";
        initControlsText();
        setBackground(frames.get(0));
        setPaintOrder(StatWindow.class);

        // Add Buttons
        addObject(playButton, 150, 400);
        addObject(albumButton, 150, 480);
        addObject(controlsButton, 150, 560);
        addObject(loadButton, 150, 640);

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
        if (Greenfoot.mouseMoved(playButton) && !added) {
            addObject(selector, playButton.getX(), playButton.getY());
            added = true;
            onPlay = true;
            onAlbum = onControls = onLoad = false;
        }
        else if (Greenfoot.mouseMoved(albumButton) && !added) {
            addObject(selector, albumButton.getX(), albumButton.getY());
            added = true;
            onAlbum = true;
            onPlay = onControls = onLoad = false;
        }
        else if (Greenfoot.mouseMoved(controlsButton) && !added) {
            addObject(selector, controlsButton.getX(), controlsButton.getY());
            added = true;
            onControls = true;
            onPlay = onAlbum = onLoad = false;
        }
        else if (Greenfoot.mouseMoved(loadButton) && !added) {
            addObject(selector, loadButton.getX(), loadButton.getY());
            added = true;
            onLoad = true;
            onPlay = onAlbum = onControls = false;
        }
        if (Greenfoot.mouseMoved(null) && added && !Greenfoot.mouseMoved(selector) && !Greenfoot.mouseMoved(playButton) && !Greenfoot.mouseMoved(albumButton) && !Greenfoot.mouseMoved(controlsButton) && !Greenfoot.mouseMoved(loadButton)) {
            removeObject(selector);
            added = false;
            onPlay = onAlbum = onControls = onLoad = false;
        }
    }

    public void checkClick() {
        if (Greenfoot.mouseClicked(selector) && onPlay) {
            addObject(shine, 600, 400);
        }
        if (Greenfoot.mouseClicked(selector) && onAlbum) {
            Greenfoot.setWorld(new MusicMenu());
        }
        if (Greenfoot.mouseClicked(selector) && onControls) {
            Font font = new Font("Candara", true, false, 45);
            StatWindow sw = new StatWindow(controlsText, font, Color.YELLOW, Color.BLACK, 255, state);
            state = "interface";
            addObject(sw, getWidth() / 2, getHeight() / 2);
        }
        if (Greenfoot.mouseClicked(selector) && onLoad) {
            if (UserInfo.isStorageAvailable()) {
                UserInfo myInfo = UserInfo.getMyInfo();
                switch (myInfo.getScore()) {
                    case 1:
                        Greenfoot.setWorld(new Chapter1());
                        break;
                    case 2:
                        Greenfoot.setWorld(new Chapter2());
                        break;    
                }
            }
        }
    }

    public void animateTitleScreen() {
        if (animationTimer.millisElapsed() < 120) {
            return;
        }
        animationTimer.mark();
        setBackground(frames.get(imageIndex));
        imageIndex = (imageIndex + 1) % frames.size();
    }

    public static void initControlsText() {
        controlsText = ""; // reset static variable
        String whitespace = "     ";
        controlsText = "\n" + whitespace + "Controls" + whitespace + "\n\n";
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Menu window is only available in BattleWorld.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuWindow extends Image
{
    protected Image instructionsButton = new Image("Placeholder/ally.png");
    protected Image controlsButton = new Image("Buttons/ControlsButton.png");
    protected Image loadButton = new Image("Buttons/LoadButton.png");
    protected Image quitButton = new Image("Buttons/QuitButton.png");
    protected Image selector = new Image("TitleSelector.png");
    protected boolean added = false;
    protected boolean onInstructions, onQuit, onControls, onLoad;
    protected String returnState;
    protected SimpleTimer timer = new SimpleTimer();

    public MenuWindow(String returnState) {
        super("MenuBG.png");
        this.returnState = returnState;
        timer.mark();
    }

    public void addedToWorld(World w) {
        int startY = 320;
        int spacing = 70;
        getWorld().addObject(instructionsButton, getWorld().getWidth() / 2, startY);
        getWorld().addObject(controlsButton, getWorld().getWidth() / 2, startY + spacing);
        getWorld().addObject(loadButton, getWorld().getWidth() / 2, startY + spacing * 2);
        getWorld().addObject(quitButton, getWorld().getWidth() / 2, startY + spacing * 3);
    }

    public void act() {
        BattleWorld bw = (BattleWorld)getWorld();
        if (bw.state == "menu") {
            checkHovering();
            checkClick();
            checkGoBack();   
        }
    }

    public void checkHovering() {
        if (Greenfoot.mouseMoved(instructionsButton) && !added) {
            getWorld().addObject(selector, instructionsButton.getX(), instructionsButton.getY());
            added = true;
            onInstructions = true;
            onQuit = onControls = onLoad = false;
        }
        else if (Greenfoot.mouseMoved(controlsButton) && !added) {
            getWorld().addObject(selector, controlsButton.getX(), controlsButton.getY());
            added = true;
            onControls = true;
            onInstructions = onQuit = onLoad = false;
        }
        else if (Greenfoot.mouseMoved(loadButton) && !added) {
            getWorld().addObject(selector, loadButton.getX(), loadButton.getY());
            added = true;
            onLoad = true;
            onInstructions = onQuit = onControls = false;
        }
        else if (Greenfoot.mouseMoved(quitButton) && !added) {
            getWorld().addObject(selector, quitButton.getX(), quitButton.getY());
            added = true;
            onQuit = true;
            onInstructions = onControls = onLoad = false;
        }
        if (Greenfoot.mouseMoved(null) && added && !Greenfoot.mouseMoved(selector) && !Greenfoot.mouseMoved(instructionsButton) && !Greenfoot.mouseMoved(quitButton) && !Greenfoot.mouseMoved(controlsButton) && !Greenfoot.mouseMoved(loadButton)) {
            getWorld().removeObject(selector);
            added = false;
            onInstructions = onQuit = onControls = onLoad = false;
        }
    }

    public void checkClick() {
        if (Greenfoot.mouseClicked(selector) && onInstructions) {
            // add instructions image
        }
        if (Greenfoot.mouseClicked(selector) && onControls) {
            Font font = new Font("Candara", true, false, 45);
            BattleWorld bw = (BattleWorld)getWorld();
            StatWindow sw = new StatWindow(MainMenu.controlsText, font, Color.YELLOW, Color.BLACK, 255, bw.state);
            bw.state = "interface";
            bw.addObject(sw, bw.getWidth() / 2, bw.getHeight() / 2);
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
        if (Greenfoot.mouseClicked(selector) && onQuit) {
            Greenfoot.setWorld(new MainMenu());
        }
    }

    public void checkGoBack() {
        if (Greenfoot.isKeyDown("j") && timer.millisElapsed() > 500) {
            BattleWorld bw = (BattleWorld)getWorld();
            bw.state = returnState;
            removeSelf();    
        }
    }

    public void removeSelf() {
        getWorld().removeObject(controlsButton);
        getWorld().removeObject(loadButton);
        getWorld().removeObject(instructionsButton);
        getWorld().removeObject(quitButton);
        getWorld().removeObject(selector);
        super.removeSelf();
    }
}

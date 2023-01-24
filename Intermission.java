import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Intermission here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Intermission extends GameWorld
{
    int chapterNumber;
    Dialogue d;
    
    public Intermission(String path, String dialoguePath, int chapterNumber) {
        super(1200, 800, 1);
        setBackground(path);
        this.chapterNumber = chapterNumber;
        d = new Dialogue(dialoguePath, "");
        addObject(d, 0, 0);
    }
    
    public void act() {
        actCount++;
        if (actCount == 1) {
            Soundtrack.theStrolling.playLoop();
            Soundtrack.pauseAllExceptTheStrolling();
        }
        if (d.i == d.dialogues.size()) {
            switch (chapterNumber) {
                case 1:
                    Greenfoot.setWorld(new Chapter2());
                    Soundtrack.theStrolling.stop();
                    break;
                case 2:
                    Greenfoot.setWorld(new Chapter3());
                    Soundtrack.theStrolling.stop();
                    break;
                case 3:
                    Greenfoot.setWorld(new Chapter4());
                    Soundtrack.theStrolling.stop();
                    break;
                case 4:
                    Greenfoot.setWorld(new Chapter5());
                    Soundtrack.theStrolling.stop();
                    break;
                case 5:
                    Greenfoot.setWorld(new Chapter6());
                    Soundtrack.theStrolling.stop();
                    break;
                case 6:
                    Greenfoot.setWorld(new Chapter7());
                    Soundtrack.theStrolling.stop();
                    break;
            }
        }
    }
}

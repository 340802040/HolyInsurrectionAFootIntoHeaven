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
        if (d.i == d.dialogues.size()) {
            switch (chapterNumber) {
                case 1:
                    Greenfoot.setWorld(new Chapter2());
                    break;
                case 2:
                    Greenfoot.setWorld(new Chapter3());
                    break;
                case 3:
                    Greenfoot.setWorld(new Chapter4());
                    break;
                case 4:
                    Greenfoot.setWorld(new Chapter5());
                    break;
                case 5:
                    Greenfoot.setWorld(new Chapter6());
                    break;
                case 6:
                    Greenfoot.setWorld(new Chapter7());
                    break;
            }
        }
    }
}

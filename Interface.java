import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Interface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Interface extends Image
{
    protected Ally a;
    protected BattleWorldCharacter bwc;
    protected SimpleTimer timer = new SimpleTimer(); // add a buffer before user can choose to go back and prevents going back twice in a row
    
    public Interface(Ally a) {
        super();
        this.a = a;
    }
    
    public Interface(BattleWorldCharacter bwc) {
        super();
        this.bwc = bwc;
    }
}

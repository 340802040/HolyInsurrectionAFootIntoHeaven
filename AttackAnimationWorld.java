import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimationWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimationWorld extends GameWorld
{
    private BattleWorld returnWorld;
    private Ally a;
    private Enemy e;
    private String attacker_s;
    private GreenfootImage[] attackFrames, critFrames;
    private Image attacker, defender;
    
    public AttackAnimationWorld(BattleWorld returnWorld, Ally a, Enemy e, String attacker_s) {
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        this.a = a;
        this.e = e;
        this.attacker_s = attacker_s;
        returnWorld.state = "attack animation";
        setBackground("BattleBackground.png");
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(null)) {
            returnWorld.state = "gameplay";
            Greenfoot.setWorld(returnWorld);
        }
    }
    
    public void initFrames() {
        if (e instanceof EnemyFootSoldier) {
            attackFrames = new GreenfootImage[35];
        }
    }
}

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
    private Image allyHpBg = new Image("AllyHPbg.png");
    private Image enemyHpBg = new Image("EnemyHPbg.png");
    private Image attacker, defender;
    
    public AttackAnimationWorld(BattleWorld returnWorld, Ally a, Enemy e, String attacker_s) {
        super(1200, 800, 1);
        this.returnWorld = returnWorld;
        this.a = a;
        this.e = e;
        this.attacker_s = attacker_s;
        returnWorld.state = "attack animation";
        setBackground("BattleBackground.png");
        if (attacker_s == "ally") {
            addObject(allyHpBg, getWidth() / 4, getHeight() - allyHpBg.getImage().getHeight() / 2);
            addObject(enemyHpBg, getWidth() / 4 * 3, getHeight() - enemyHpBg.getImage().getHeight() / 2);
        }
        else {
            addObject(enemyHpBg, getWidth() / 4, getHeight() - enemyHpBg.getImage().getHeight() / 2);
            addObject(allyHpBg, getWidth() / 4 * 3, getHeight() - allyHpBg.getImage().getHeight() / 2);
        }
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

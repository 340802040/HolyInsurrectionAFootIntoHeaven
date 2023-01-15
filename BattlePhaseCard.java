import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattlePhaseCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattlePhaseCard extends Card
{
    public BattlePhaseCard(String path) {
        super(path);
    }
    
    public void act() {
        super.act();
    }
    
    public void fadeOut() {
        int newTrans = getImage().getTransparency() - 15;
        if (newTrans == 0) {
            BattleWorld bw = (BattleWorld)getWorld();
            bw.state = "gameplay";
            if (bw.phase == "player") {
                bw.startEnemyPhase();
            }
            else {
                bw.startPlayerPhase();
            }
            bw.cardAnimating = false;
            bw.removeObject(this);
        }
        if (actCount % 4 == 0 && newTrans >= 0) {
            getImage().setTransparency(newTrans);
        }
    }
}

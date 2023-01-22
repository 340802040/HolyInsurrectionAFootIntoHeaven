import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attacker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Attacker extends AttackAnimationActor
{    
    public Attacker(BattleWorldCharacter me, BattleWorldCharacter other) {
        super(me, other);
        isAnimating = true;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }

    public void act() {
        super.act();
    }

    public void animate() {
        AttackAnimationWorld w = (AttackAnimationWorld)getWorld();
        AttackAnimationActor otherActor = w.getOtherActor(this);

        if (actCount % 7 == 0) {
            // FRAME OF IMPACT
            if (i == frameOfImpact) {
                if (willHit) {
                    otherActor.cutHp();
                    dmgIndicatorIsAnimating = true;
                    getWorld().addObject(dmgIndicator, getWorld().getWidth() / 2, getWorld().getHeight() / 2);

                    if (willCrit) {
                        TextCard t = new TextCard("Crit!", font, Color.WHITE, null, 5);
                        getWorld().addObject(t, getWorld().getWidth() / 2, 100);
                    }

                    if (me instanceof Ally) {
                        ((Ally)me).increaseXp(((Enemy)other).hitXp);
                    }
                }
                else {
                    TextCard t = new TextCard("Miss!", font, Color.WHITE, null, 5);
                    getWorld().addObject(t, getWorld().getWidth() / 2, 100);
                }
            }

            // ATTACK ANIMATION
            if (isAnimating) {
                setImage(frames.get(i));
                i++;
                if (i == frames.size()) {
                    isAnimating = false;
                    if (!otherActor.isDying) {
                        otherActor.isAnimating = true; // switch turn to defender
                    }
                }
                else i %= frames.size();
            }

            // DMG INDICATOR
            if (dmgIndicatorIsAnimating) {
                dmgIndicator.setImage(dmgIndicators.get(damage_i));
                damage_i++;
                if (damage_i == dmgIndicators.size()) {
                    dmgIndicatorIsAnimating = false;
                    getWorld().removeObject(dmgIndicator);
                }
                else damage_i %= dmgIndicators.size();
            }
            
            // SET FINISHED
            // only if we have killed the defender can we safely set out finished state
            if (otherActor.isDying && !isAnimating && !dmgIndicatorIsAnimating) {
                finished = true;
            }
        }
    }
}

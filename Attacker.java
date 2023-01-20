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

    public void drawHealthBarAndLabel() {
        // LABEL
        hpLabel.setText(Integer.toString(me.health));
        getWorld().addObject(hpLabel, 50, getWorld().getHeight() - allyHpBg.getImage().getHeight() / 2 + 8);

        // BAR
        int y1 = 700; // top row
        int y2 = y1 + tick.getImage().getHeight() + 8; // bottom row
        if (me.maxHealth <= 33) {
            // only display 1 row
            for (int i = 0, x = 110, y = getWorld().getHeight() - allyHpBg.getImage().getHeight() / 2; i < me.maxHealth; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                ticks.add(t);
                getWorld().addObject(t, x, y);
            }
        }
        else {            
            // top row - length 33
            for (int i = 0, x = 110; i < 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                ticks.add(t);
                getWorld().addObject(t, x, y1);
            }
            // bottom row - rest
            for (int i = 0, x = 110; i < me.maxHealth - 33; i++, x += tick.getImage().getWidth() - 6) {
                Image t = new Image("HealthTick.png");
                ticks.add(t);
                getWorld().addObject(t, x, y2);
            }
        }
        // make attacker missing health grey
        for (int i = me.maxHealth - 1; i >= me.health; i--) {
            ticks.get(i).setImage("GreyHealthTick.png");
        }
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
                else {
                    i %= frames.size();    
                }
            }

            // DMG INDICATOR
            if (dmgIndicatorIsAnimating) {
                dmgIndicator.setImage(dmgIndicators.get(damage_i));
                damage_i++;
                if (damage_i == dmgIndicators.size()) {
                    dmgIndicatorIsAnimating = false;
                    getWorld().removeObject(dmgIndicator);
                }
                damage_i %= dmgIndicators.size();
            }

            if (!isAnimating && !dmgIndicatorIsAnimating) {
                finished = true;
            }
        }
    }
}

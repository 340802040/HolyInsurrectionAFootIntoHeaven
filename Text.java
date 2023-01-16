import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image of just text.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Card
{
    private Font font;
    private Color color;
    private Color bgColor;
    private boolean cardFade;
    
    public Text(String s, Font font, Color color, Color bgColor, boolean cardFade) {
        super(new TextImage(s, font, color, bgColor));
        this.font = font;
        this.color = color;
        this.bgColor = bgColor;
        this.cardFade = cardFade;
    }
    
    public void act() {
        if (cardFade) {
            super.act();
        }
    }
    
    public void setText(String s) {
        setImage(new TextImage(s, font, color, bgColor));
    }
}

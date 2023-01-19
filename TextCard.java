import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image of just text.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextCard extends Card
{
    private Font font;
    private Color color;
    private Color bgColor;

    public TextCard(String s, Font font, Color color, Color bgColor, int mod) {
        super(new TextImage(s, font, color, bgColor), mod);
        this.font = font;
        this.color = color;
        this.bgColor = bgColor;
    }

    public void act() {
        super.act();
    }

    public void setText(String s) {
        setImage(new TextImage(s, font, color, bgColor));
    }
}

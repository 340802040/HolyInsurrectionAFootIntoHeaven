import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    protected int width, height;
    protected static int BLOCK_SIZE;
    protected static int GRID_WIDTH, GRID_HEIGHT;
    protected static int X_OFFSET, Y_OFFSET;
    protected int[][] map;
    protected String state;
    protected int actCount = 0;

    public GameWorld(int width, int height, int pixelSize) {    
        super(width, height, pixelSize);
        width = 1200;
        height = 800;
        BLOCK_SIZE = 50;
        GRID_WIDTH = width / BLOCK_SIZE; 
        GRID_HEIGHT = height / BLOCK_SIZE; 
        X_OFFSET = BLOCK_SIZE / 2;
        Y_OFFSET = BLOCK_SIZE / 2;
    }

    /**
     * Returns Greenfoot world's X coordinate given a cell number along the x-axis.
     */
    public static int getX(int cellNumber){
        return (cellNumber * BLOCK_SIZE) + X_OFFSET;
    }

    /**
     * Returns the cell number along the x-axis given an X coordinate.
     */
    public static int getXCell(int coordinate){
        if ((coordinate - X_OFFSET) % BLOCK_SIZE == 0) {
            return (coordinate - X_OFFSET) / BLOCK_SIZE;
        }
        else return (coordinate - X_OFFSET) % BLOCK_SIZE;
    }

    /**
     * Returns Greenfoot world's Y coordinate given a cell number along the y-axis.
     */
    public static int getY(int cellNumber){
        return (cellNumber * BLOCK_SIZE) + Y_OFFSET;
    }

    /**
     * Returns the cell number along the y-axis given an Y coordinate.
     */
    public static int getYCell(int coordinate){
        if ((coordinate - Y_OFFSET) % BLOCK_SIZE == 0) {
            return (coordinate - Y_OFFSET) / BLOCK_SIZE;
        }
        else return (coordinate - Y_OFFSET) % BLOCK_SIZE;
    }

    public int[][] getMap() {
        return map;
    }

    /**
     * Gets random number from start to end exclusive.
     */
    public static int getRandomNumberInRange(int start, int end) {
        int x = Greenfoot.getRandomNumber(end - start);
        return start + x;
    }

    /**
     * Returns the weapon multiplier for weapon1 given weapon1 vs weapon2.
     * 
     * @param w1 First weapon
     * @param w2 Second weapon
     */
    public static double getWeaponMultiplier(String w1, String w2) {
        if ((w1 == "Sword" && w2 == "Bow") || (w1 == "Bow" && w2 == "Spear") || (w1 == "Spear" && w2 == "Sword") || (w1 == "Fire" && w2 == "Ice") || (w1 == "Ice" && w2 == "Water") || (w1 == "Water" && w2 == "Fire")) {
            return 1.3;
        }
        else if ((w1 == "Bow" && w2 == "Sword") || (w1 == "Spear" && w2 == "Bow") || (w1 == "Sword" && w2 == "Spear") || (w1 == "Ice" && w2 == "Fire") || (w1 == "Water" && w2 == "Ice") || (w1 == "Fire" && w2 == "Water")) {
            return 0.7;   
        }
        else return 1;
    }
    
    /**
     * Picks n random items from a list and returns them.
     */
    public static List<String> pickNRandom(List<String> lst, int n) {
        List<String> copy = new ArrayList<String>(lst);
        Collections.shuffle(copy);
        return n > copy.size() ? copy.subList(0, copy.size()) : copy.subList(0, n);
    }

    public void stopped() {
        //Soundtrack.stopAll();
    }
}

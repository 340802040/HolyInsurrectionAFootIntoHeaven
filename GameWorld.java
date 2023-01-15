import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    public GameWorld(int width, int height, int pixelSize) {    
        super(width, height, pixelSize);
        width = 1200;
        height = 800;
        BLOCK_SIZE = 50;
        GRID_WIDTH = width / BLOCK_SIZE; 
        GRID_HEIGHT = height / BLOCK_SIZE; 
        X_OFFSET = BLOCK_SIZE / 2;
        Y_OFFSET = BLOCK_SIZE / 2;
        
        setPaintOrder(Selector.class);
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
}

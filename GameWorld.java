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
        GRID_WIDTH = width / BLOCK_SIZE; // 900 / 45 = 20
        GRID_HEIGHT = height / BLOCK_SIZE; // 600 / 45 = 15
        X_OFFSET = BLOCK_SIZE / 2;
        Y_OFFSET = BLOCK_SIZE / 2;
    }

    public void act() {

    }
    
    public static int getXCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + X_OFFSET;
    }

    public static int getXCell(int coordinate){
        return (coordinate - X_OFFSET) % BLOCK_SIZE;
    }

    public static int getYCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + Y_OFFSET;
    }

    public static int getYCell(int coordinate){
        return (coordinate - Y_OFFSET) % BLOCK_SIZE;
    }
}

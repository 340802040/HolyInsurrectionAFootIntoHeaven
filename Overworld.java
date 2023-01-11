import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Overworld extends World
{
    private int width, height;
    private static int BLOCK_SIZE;
    private static int GRID_WIDTH, GRID_HEIGHT;
    private static int X_OFFSET, Y_OFFSET;
    
    public Overworld() {    
        super(1000, 750, 1);
        // Initialize variables
        width = 1000;
        height = 750;
        BLOCK_SIZE = 50;
        GRID_WIDTH = width / BLOCK_SIZE; // 900 / 45 = 20
        GRID_HEIGHT = height / BLOCK_SIZE; // 600 / 45 = 15
        X_OFFSET = BLOCK_SIZE / 2;
        Y_OFFSET = BLOCK_SIZE / 2;
        
        // Initialize grids
        int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        
        for (int r = 0; r < GRID_HEIGHT; r++) {
            for (int c = 0; c < GRID_WIDTH; c++) {
                if (map[r][c] == 0) { // grass tile
                    addObject(new Grass(), c * BLOCK_SIZE + X_OFFSET, r * BLOCK_SIZE + Y_OFFSET);
                }
            }
        }
        
        // Add player
        addObject(new Player(), X_OFFSET, Y_OFFSET);
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

    public static int getGridWidth() {
        return GRID_WIDTH;
    }
    
    public static int getGridHeight() {
        return GRID_HEIGHT;
    }
}

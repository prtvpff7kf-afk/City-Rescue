package cityrescue;

import cityrescue.exceptions.InvalidGridException;
import cityrescue.exceptions.InvalidLocationException;

//city map to manage grid size, blocked cells and legal moves.
public class CityMap {
    
    //attributes
    private final int width;
    private final int height;
    private final boolean[][] blockedCells;
    
    //constructor
    public CityMap(int width, int height) throws InvalidGridException {
        if (width <= 0 || height <= 0) {
            throw new InvalidGridException("Grid dimensions must be positive.");
        }
        this.width = width;
        this.height = height;
        this.blockedCells = new boolean[width][height];
    }

    //getters
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    //boundary validation for coordinates
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    //checks if cell is blocked
    public boolean isBlocked(int x, int y) {
        return blockedCells[x][y];
    }

    //adds obstacle
    public void addObstacle(int x, int y) throws InvalidLocationException {
        if (!inBounds(x, y)) {
            throw new InvalidLocationException("Coordinates out of bounds.");
        }
        if (isBlocked(x, y)) {
            throw new InvalidLocationException("Cell is already blocked.");
        }
        blockedCells[x][y] = true;
    }

    //removes obstacle
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (!inBounds(x, y)) {
            throw new InvalidLocationException("Coordinates out of bounds.");
        }
        if (!isBlocked(x, y)) {
            throw new InvalidLocationException("Cell is not blocked.");
        }
        blockedCells[x][y] = false;
    }

    //obstacle count
    public int countObstacles() {
        int count = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (blockedCells[x][y]) {
                    count++;
                }
            }
        }
        return count;
    }

}

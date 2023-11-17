package edu.project2.utility;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import java.util.HashMap;

public final class GridUtils {

    private GridUtils() {
    }

    public static boolean areCoordinatesInGridBounds(int gridLength, int gridHeight, int x, int y) {
        return x < gridLength && x >= 0
            && y < gridHeight && y >= 0;
    }

    public static boolean isCellAPassage(Cell[][] grid, int x, int y) {
        return grid[x][y].getType() == Cell.Type.PASSAGE;
    }

    public static boolean isCellAWall(Cell[][] grid, int x, int y) {
        return grid[x][y].getType() == Cell.Type.WALL;
    }

    public static boolean isCellUnvisited(HashMap<Coordinate, Coordinate> unvisited, int x, int y) {
        return unvisited.get(new Coordinate(x, y)) != null;
    }

}

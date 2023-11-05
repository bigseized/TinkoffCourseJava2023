package edu.project2.utility;

import edu.project2.types.Cell;

public final class ArraysUtils {
    private ArraysUtils() {}

    public static Cell[][] copyDeepCellArr(Cell[][] cellArr) {
        Cell[][] copyGrid = new Cell[cellArr.length][];
        for (int i = 0; i < cellArr.length; i++) {
            copyGrid[i] = new Cell[cellArr[i].length];
            for (int j = 0; j < cellArr[i].length; j++) {
                copyGrid[i][j] = new Cell(cellArr[i][j].getRow(), cellArr[i][j].getCol(), cellArr[i][j].getType());
            }
        }
        return copyGrid;
    }

    public static Cell[][] createArrayAllWalls(int height, int width) {
        var grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
        return grid;
    }

}

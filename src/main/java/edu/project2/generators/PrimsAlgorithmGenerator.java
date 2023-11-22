package edu.project2.generators;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Direction;
import edu.project2.utility.ArraysUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static edu.project2.utility.GridUtils.areCoordinatesInGridBounds;
import static edu.project2.utility.GridUtils.isCellAWall;

public class PrimsAlgorithmGenerator implements Generator {
    private final static Random RANDOM = new Random();
    private static final int[][] SHIFTS = {{-2, 0}, {2, 0}, {0, 2}, {0, -2}};

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = ArraysUtils.createArrayAllWalls(height, width);

        int x = RANDOM.nextInt(0, height / 2) * 2 + 1;
        int y = RANDOM.nextInt(0, width / 2) * 2 + 1;
        grid[x][y].setType(Cell.Type.PASSAGE);

        var cellsForGrowth = new ArrayList<Coordinate>();
        cellsForGrowth.add(new Coordinate(x, y));

        while (!cellsForGrowth.isEmpty()) {
            int index = RANDOM.nextInt(0, cellsForGrowth.size());
            Coordinate cell = cellsForGrowth.get(index);
            x = cell.row();
            y = cell.col();
            cellsForGrowth.remove(index);

            var directions =
                new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
            while (!directions.isEmpty()) {
                int dirIndex = RANDOM.nextInt(0, directions.size());
                var direction = directions.get(dirIndex);

                int[] shift = SHIFTS[direction.ordinal()];
                int newX = x + shift[0];
                int newY = y + shift[1];

                if (areCoordinatesInGridBounds(width, height, newX, newY)
                    && isCellAWall(grid, newX, newY)) {

                    grid[x + shift[0] / 2][y + shift[1] / 2].setType(Cell.Type.PASSAGE);
                    grid[newX][newY].setType(Cell.Type.PASSAGE);
                    cellsForGrowth.add(new Coordinate(newX, newY));
                }

                directions.remove(dirIndex);
            }
        }

        return new Maze(height, width, grid);
    }

}

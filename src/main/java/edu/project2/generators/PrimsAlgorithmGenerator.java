package edu.project2.generators;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Direction;
import edu.project2.utility.ArraysUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PrimsAlgorithmGenerator implements Generator {
    private final static Random RANDOM = new Random();

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
                switch (directions.get(dirIndex)) {
                    case NORTH -> {
                        if (y - 2 >= 0 && grid[x][y - 2].getType() == Cell.Type.WALL) {
                            grid[x][y - 1].setType(Cell.Type.PASSAGE);
                            grid[x][y - 2].setType(Cell.Type.PASSAGE);
                            cellsForGrowth.add(new Coordinate(x, y - 2));
                        }
                    }
                    case SOUTH -> {
                        if (y + 2 < width && grid[x][y + 2].getType() == Cell.Type.WALL) {
                            grid[x][y + 1].setType(Cell.Type.PASSAGE);
                            grid[x][y + 2].setType(Cell.Type.PASSAGE);
                            cellsForGrowth.add(new Coordinate(x, y + 2));
                        }
                    }
                    case EAST -> {
                        if (x + 2 < height && grid[x + 2][y].getType() == Cell.Type.WALL) {
                            grid[x + 1][y].setType(Cell.Type.PASSAGE);
                            grid[x + 2][y].setType(Cell.Type.PASSAGE);
                            cellsForGrowth.add(new Coordinate(x + 2, y));
                        }
                    }
                    case WEST -> {
                        if (x - 2 >= 0 && grid[x - 2][y].getType() == Cell.Type.WALL) {
                            grid[x - 1][y].setType(Cell.Type.PASSAGE);
                            grid[x - 2][y].setType(Cell.Type.PASSAGE);
                            cellsForGrowth.add(new Coordinate(x - 2, y));
                        }
                    }
                    default -> {
                    }
                }
                directions.remove(dirIndex);
            }
        }

        return new Maze(height, width, grid);
    }
}

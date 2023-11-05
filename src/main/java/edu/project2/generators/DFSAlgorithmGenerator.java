package edu.project2.generators;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class DFSAlgorithmGenerator implements Generator {
    private static final Random RANDOM = new Random();
    private static final int[][] SHIFTS = {{-2, 0}, {2, 0}, {0, 2}, {0, -2}};

    @Override
    public Maze generate(int height, int width) {
        var grid = new Cell[height][width];
        var unvisited = new HashMap<Coordinate, Coordinate>();

        initGrid(grid);
        fillUnvisited(grid, unvisited);

        int x = RANDOM.nextInt(0, height / 2) * 2 + 1;
        int y = RANDOM.nextInt(0, width / 2) * 2 + 1;

        fillGrid(grid, unvisited, x, y);
        return new Maze(height, width, grid);
    }


    private void initGrid(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
    }

    private void fillUnvisited(Cell[][] grid, HashMap<Coordinate, Coordinate> unvisited) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getType() == Cell.Type.PASSAGE) {
                    unvisited.put(new Coordinate(i, j), new Coordinate(i, j));
                }
            }
        }
    }

    private void fillGrid(Cell[][] grid, HashMap<Coordinate, Coordinate> unvisited, int x, int y) {
        var genStack = new Stack<Coordinate>();
        genStack.push(unvisited.get(new Coordinate(x, y)));
        unvisited.remove(new Coordinate(x, y));

        while (!genStack.isEmpty() && !unvisited.isEmpty()) {
            Coordinate currentCord = genStack.peek();
            ArrayList<Pair<Integer, Integer>> availableShifts =
                getAvailableShifts(currentCord, unvisited, new Pair<>(grid.length, grid[0].length));
            if (availableShifts.isEmpty()) {
                genStack.pop();
                continue;
            }
            int shiftIndex = RANDOM.nextInt(0, availableShifts.size());

            Pair<Integer, Integer> shift = availableShifts.get(shiftIndex);
            int newRow = currentCord.row() + shift.first();
            int newCol = currentCord.col() + shift.second();

            grid[currentCord.row() + shift.first() / 2][currentCord.col()
                + shift.second() / 2].setType(Cell.Type.PASSAGE);
            unvisited.remove(new Coordinate(newRow, newCol));
            genStack.push(new Coordinate(newRow, newCol));
        }
    }

    private ArrayList<Pair<Integer, Integer>> getAvailableShifts(
        Coordinate currentCord, HashMap<Coordinate, Coordinate> unvisited,
        Pair<Integer, Integer> mazeSize
    ) {
        ArrayList<Pair<Integer, Integer>> availableShifts = new ArrayList<>();
        for (int[] shift : SHIFTS) {
            int newRow = currentCord.row() + shift[0];
            int newCol = currentCord.col() + shift[1];
            if (newRow >= 0 && newRow < mazeSize.first() && newCol >= 0 && newCol < mazeSize.second()
                && unvisited.get(new Coordinate(newRow, newCol)) != null) {
                availableShifts.add(new Pair<>(shift[0], shift[1]));
            }
        }
        return availableShifts;
    }
}

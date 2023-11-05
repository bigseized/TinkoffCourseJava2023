package edu.project2.solvers;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.utility.ArraysUtils;
import java.util.ArrayList;
import java.util.List;

public class DFSAlgorithmSolver implements Solver {
    private final static int[][] SHIFTS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Cell[][] grid = ArraysUtils.copyDeepCellArr(maze.getGrid());

        List<Coordinate> path = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        path.add(new Coordinate(start.row(), start.col()));
        return getPath(grid, start, end, path, visited);
    }

    private List<Coordinate> getPath(
        Cell[][] grid,
        Coordinate current,
        Coordinate end,
        List<Coordinate> path,
        boolean[][] visited
    ) {
        if (current.equals(end)) {
            return path;
        }

        int row = current.row();
        int col = current.col();
        visited[row][col] = true;

        for (int[] shift : SHIFTS) {
            int newRow = row + shift[0];
            int newCol = col + shift[1];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                && !visited[newRow][newCol] && grid[newRow][newCol].getType() == Cell.Type.PASSAGE) {
                path.add(new Coordinate(newRow, newCol));
                List<Coordinate> result = getPath(grid, new Coordinate(newRow, newCol), end, path, visited);
                if (result != null) {
                    return result;
                }
                path.remove(path.size() - 1);
            }
        }

        visited[row][col] = false;
        return null;
    }

}

package edu.hw9.task3;

import edu.project2.Maze;
import edu.project2.solvers.Solver;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.utility.ArraysUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.RequiredArgsConstructor;
import static edu.project2.utility.GridUtils.areCoordinatesInGridBounds;
import static edu.project2.utility.GridUtils.isCellAPassage;

public class ParallelDFSAlgorithmSolver implements Solver {
    private final static int[][] SHIFTS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();
    private boolean[][] visited;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Cell[][] grid = ArraysUtils.copyDeepCellArr(maze.getGrid());

        visited = new boolean[grid.length][grid[0].length];

        return POOL.invoke(new DfsTask(grid, start, end));

    }

    @RequiredArgsConstructor
    private class DfsTask extends RecursiveTask<List<Coordinate>> {
        final Cell[][] grid;
        final Coordinate current;
        final Coordinate end;

        @Override
        protected List<Coordinate> compute() {
            List<Coordinate> path = new ArrayList<>();
            if (current.equals(end)) {
                path.add(new Coordinate(current.row(), current.col()));
                return path;
            }

            List<ForkJoinTask<List<Coordinate>>> subTasks = createSubtasks();

            for (ForkJoinTask<List<Coordinate>> subTask : subTasks) {
                List<Coordinate> subTaskResult = subTask.join();
                if (!subTaskResult.isEmpty()) {
                    path.add(new Coordinate(current.row(), current.col()));
                    path.addAll(subTaskResult);
                }
            }
            return path;
        }

        private List<ForkJoinTask<List<Coordinate>>> createSubtasks() {
            List<ForkJoinTask<List<Coordinate>>> subTasks = new ArrayList<>();

            int row = current.row();
            int col = current.col();
            visited[row][col] = true;
            for (int[] shift : SHIFTS) {
                int newRow = row + shift[0];
                int newCol = col + shift[1];
                if (!areCoordinatesInGridBounds(grid.length, grid[0].length, newRow, newCol)
                    || visited[newRow][newCol]
                    || !isCellAPassage(grid, newRow, newCol)) {
                    continue;
                }

                subTasks.add(new DfsTask(grid, new Coordinate(newRow, newCol), end).fork());
            }
            return subTasks;
        }
    }

}

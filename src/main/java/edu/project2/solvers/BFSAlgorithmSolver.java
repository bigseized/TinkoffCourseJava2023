package edu.project2.solvers;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Pointer;
import edu.project2.utility.ArraysUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static edu.project2.utility.GridUtils.areCoordinatesInGridBounds;
import static edu.project2.utility.GridUtils.isCellAPassage;

public class BFSAlgorithmSolver implements Solver {
    private final static int[][] SHIFTS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final Queue<Pointer> path = new LinkedList<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        path.clear();
        Cell[][] grid = ArraysUtils.copyDeepCellArr(maze.getGrid());
        path.add(new Pointer(start.row(), start.col(), null));

        while (!path.isEmpty()) {
            Pointer p = path.remove();

            if (p.getCol() == end.row() && p.getRow() == end.col()) {
                return getSolvePath(p);
            }

            for (var shift: SHIFTS) {
                int newX = p.getCol() + shift[0];
                int newY = p.getRow() + shift[1];
                if (areCoordinatesInGridBounds(grid.length, grid[0].length, newX, newY)
                    && isCellAPassage(grid, newX, newY)) {

                    grid[p.getCol()][p.getRow()].setType(Cell.Type.WALL);
                    Pointer nextP = new Pointer(newX, newY, p);
                    path.add(nextP);
                }
            }

        }
        return null;
    }

    private List<Coordinate> getSolvePath(Pointer p) {
        List<Coordinate> list = new ArrayList<>();
        Pointer temp = p;
        list.add(new Coordinate(temp.getCol(), temp.getRow()));
        do {
            temp = temp.getParent();
            list.add(new Coordinate(temp.getCol(), temp.getRow()));
        }
        while (temp.getParent() != null);

        return list;
    }

}

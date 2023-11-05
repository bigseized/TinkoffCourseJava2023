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

public class BFSAlgorithmSolver implements Solver {
    public static Queue<Pointer> path = new LinkedList<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Cell[][] grid = ArraysUtils.copyDeepCellArr(maze.getGrid());
        path.add(new Pointer(start.row(), start.col(), null));

        while (!path.isEmpty()) {
            Pointer p = path.remove();

            if (p.getCol() == end.row() && p.getRow() == end.col()) {
                return getSolvePath(p);
            }

            if (p.getCol() + 1 < grid.length
                && grid[p.getCol() + 1][p.getRow()].getType() == Cell.Type.PASSAGE) {
                grid[p.getCol()][p.getRow()].setType(Cell.Type.WALL);
                Pointer nextP = new Pointer(p.getCol() + 1, p.getRow(), p);
                path.add(nextP);
            }

            if (p.getCol() - 1 >= 0
                && grid[p.getCol() - 1][p.getRow()].getType() == Cell.Type.PASSAGE) {
                grid[p.getCol()][p.getRow()].setType(Cell.Type.WALL);
                Pointer nextP = new Pointer(p.getCol() - 1, p.getRow(), p);
                path.add(nextP);
            }

            if (p.getRow() + 1 < grid[p.getCol()].length
                && grid[p.getCol()][p.getRow() + 1].getType() == Cell.Type.PASSAGE) {
                grid[p.getCol()][p.getRow()].setType(Cell.Type.WALL);
                Pointer nextP = new Pointer(p.getCol(), p.getRow() + 1, p);
                path.add(nextP);
            }

            if (p.getRow() - 1 >= 0
                && grid[p.getCol()][p.getRow() - 1].getType() == Cell.Type.PASSAGE) {
                grid[p.getCol()][p.getRow()].setType(Cell.Type.WALL);
                Pointer nextP = new Pointer(p.getCol(), p.getRow() - 1, p);
                path.add(nextP);
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

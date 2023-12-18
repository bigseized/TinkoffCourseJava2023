package edu.hw9.task3;

import edu.project2.Maze;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelDFSAlgorithmSolverTest {
    public Maze maze = getMaze();
    public List<Coordinate> path = getPath();

    private Maze getMaze() {
        Cell.Type[] mazeTypes = {
            Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE,
            Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.PASSAGE,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.PASSAGE,
            Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.PASSAGE, Cell.Type.PASSAGE,
            Cell.Type.PASSAGE, Cell.Type.WALL, Cell.Type.PASSAGE, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL,
            Cell.Type.WALL, Cell.Type.WALL, Cell.Type.WALL
        };
        Cell[][] grid = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell(i, j, mazeTypes[j + i * 9]);
            }
        }
        return new Maze(9, 9, grid);
    }

    public List<Coordinate> getPath() {
        return new ArrayList<>(Arrays.asList(
            new Coordinate(1, 5),
            new Coordinate(1, 6),
            new Coordinate(1, 7),
            new Coordinate(2, 7),
            new Coordinate(3, 7),
            new Coordinate(4, 7),
            new Coordinate(5, 7),
            new Coordinate(6, 7),
            new Coordinate(7, 7)
        ));
    }

    @BeforeEach
    public void init() {
        maze = getMaze();
        path = getPath();
    }

    @AfterEach
    public void clear() {
        maze = null;
        path = null;
    }

    @Test
    @DisplayName("Проверка работы алгоритма решения BFS")
    void check_if_BFS_solve_right() {
        Maze maze = getMaze();
        ParallelDFSAlgorithmSolver dfsAlgorithmSolver = new ParallelDFSAlgorithmSolver();
        List<Coordinate> coordinateList = dfsAlgorithmSolver.solve(maze, new Coordinate(7, 7), new Coordinate(1, 5));
        assertThat(coordinateList).containsExactlyInAnyOrderElementsOf(path);
    }
}

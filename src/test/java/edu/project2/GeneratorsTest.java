package edu.project2;

import edu.project2.generators.DFSAlgorithmGenerator;
import edu.project2.generators.PrimsAlgorithmGenerator;
import edu.project2.solvers.BFSAlgorithmSolver;
import edu.project2.types.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneratorsTest {
    @Test
    @DisplayName("Проверка работы алгоритма генерации DFS")
    void check_DFS_Algorithm() {
        DFSAlgorithmGenerator dfsAlgorithmGenerator = new DFSAlgorithmGenerator();
        Maze maze = dfsAlgorithmGenerator.generate(9, 9);
        assertNotNull(maze);
    }

    @Test
    @DisplayName("Проверка работы алгоритма генерации Прима")
    void check_PRIMS_Algorithm() {
        PrimsAlgorithmGenerator primsAlgorithmGenerator = new PrimsAlgorithmGenerator();
        Maze maze = primsAlgorithmGenerator.generate(9, 9);
        assertNotNull(maze);
    }

    @Test
    @DisplayName("Проверка на решаемоcть лабиринта сгенерированного по алгоритму DFS")
    void check_DFS_Algorithm_ReturnCorrectMaze() {
        DFSAlgorithmGenerator dfsAlgorithmGenerator = new DFSAlgorithmGenerator();
        Maze maze = dfsAlgorithmGenerator.generate(9, 9);
        assertNotNull(new BFSAlgorithmSolver().solve(maze, new Coordinate(1, 1), new Coordinate(7, 7)));
    }

    @Test
    @DisplayName("Проверка на решаемочть лабиринта сгенерированного по алгоритму Прима")
    void check_PRIMS_Algorithm_ReturnCorrectMaze() {
        PrimsAlgorithmGenerator primsAlgorithmGenerator = new PrimsAlgorithmGenerator();
        Maze maze = primsAlgorithmGenerator.generate(9, 9);
        assertNotNull(new BFSAlgorithmSolver().solve(maze, new Coordinate(1, 1), new Coordinate(7, 7)));
    }
}

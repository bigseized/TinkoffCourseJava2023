package edu.project2;

import edu.project2.drawers.ConsoleDrawer;
import edu.project2.generators.DFSAlgorithmGenerator;
import edu.project2.generators.Generator;
import edu.project2.generators.PrimsAlgorithmGenerator;
import edu.project2.solvers.BFSAlgorithmSolver;
import edu.project2.solvers.DFSAlgorithmSolver;
import edu.project2.solvers.Solver;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Pair;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MazeSolver {

    private final static Logger LOGGER = Logger.getLogger(MazeSolver.class.getName());
    private final static Pattern CHOOSE_PATTERN = Pattern.compile("^[1,2](.*)$");
    private final static Pattern MAZE_SIZE_PATTERN = Pattern.compile("([0-9]+)(\\D+)([0-9]+)(.*)");
    private final Scanner in = new Scanner(System.in);
    private final static Integer FIRST_NUMBER_IN_STRING = 1;
    private final static Integer SECOND_NUMBER_IN_STRING = 3;
    private final static Integer MIN_MAZE_SIZE = 4;

    public void run() {
        LOGGER.info("Введите размер лабиринта:");
        Pair<Integer, Integer> mazeSize = getMazeSizeInput();

        LOGGER.info("Выберите алгоритм генерации:\n1) Алгоритм Прима\n2) Алгоритм DFS");
        Integer generationVariant = getChooseInput();
        Maze maze = mazeCreate(mazeSize, generationVariant);
        drawMaze(maze);

        LOGGER.info("Выберите алгоритм решения:\n1) Алгоритм BFS\n2) Алгоритм DFS");
        Integer solutionVariant = getChooseInput();
        LOGGER.info("Введите координаты входа и выхода");
        Pair<Coordinate, Coordinate> solutionCoordinates = getSolutionCoordinatesInput(maze.getGrid());
        List<Coordinate> solutionPath = mazeSolve(maze, solutionVariant, solutionCoordinates);
        drawMaze(maze, solutionPath);
    }

    private Pair<Integer, Integer> getPairOfIntsInput() {
        while (true) {
            String input = in.nextLine();

            Matcher matcher = MAZE_SIZE_PATTERN.matcher(input);

            if (matcher.matches()) {
                return new Pair<>(
                    Integer.parseInt(matcher.group(FIRST_NUMBER_IN_STRING)),
                    Integer.parseInt(matcher.group(SECOND_NUMBER_IN_STRING))
                );
            } else {
                LOGGER.info("Необходимо ввести 2 числа!");
            }
        }
    }

    private Pair<Integer, Integer> getMazeSizeInput() {
        while (true) {
            Pair<Integer, Integer> mazeSize = getPairOfIntsInput();
            if (mazeSize.first() % 2 == 1 && mazeSize.second() % 2 == 1) {
                if (mazeSize.first() >= MIN_MAZE_SIZE || mazeSize.second() >= MIN_MAZE_SIZE) {
                    return mazeSize;
                } else {
                    LOGGER.info("Размер лабиринта не может быть меньше 3");
                }
            } else {
                LOGGER.info("Размеры лабиринта не могут быть чётными!");
            }
        }
    }

    private Pair<Coordinate, Coordinate> getSolutionCoordinatesInput(Cell[][] grid) {
        Coordinate startCoordinate;
        Coordinate endCoordinate;
        LOGGER.info("Введите координаты начала решения:");
        do {
            Pair<Integer, Integer> start = getPairOfIntsInput();
            startCoordinate = new Coordinate(start.first(), start.second());
        }
        while (isCoordinatesCorrect(startCoordinate, grid));

        LOGGER.info("Введите координаты конца решения:");
        do {
            Pair<Integer, Integer> start = getPairOfIntsInput();
            endCoordinate = new Coordinate(start.first(), start.second());
        }
        while (isCoordinatesCorrect(endCoordinate, grid));

        return new Pair<>(startCoordinate, endCoordinate);
    }

    private boolean isCoordinatesCorrect(Coordinate coordinate, Cell[][] grid) {
        if (coordinate.row() < grid.length && coordinate.col() < grid[0].length) {
            if (grid[coordinate.row()][coordinate.col()].getType() == Cell.Type.PASSAGE) {
                return false;
            }
            LOGGER.info("Точка является стеной!");
        } else {
            LOGGER.info("Координаты выходят за границы лабиринта!");
        }
        return true;
    }

    private int getChooseInput() {
        while (true) {
            String input = in.nextLine();
            Matcher matcher = CHOOSE_PATTERN.matcher(input);
            if (matcher.matches()) {
                return Integer.parseInt(input);
            } else {
                LOGGER.info("Введите 1 или 2 для выбора варианта!");
            }
        }
    }

    private Maze mazeCreate(Pair<Integer, Integer> size, Integer genVariant) {
        Generator generator = switch (genVariant) {
            case 1 -> new PrimsAlgorithmGenerator();
            case 2 -> new DFSAlgorithmGenerator();
            default -> throw new IllegalStateException("Неверный выбор алгоритма генерации: " + genVariant);
        };
        return generator.generate(size.first(), size.second());
    }

    private List<Coordinate> mazeSolve(
        Maze maze,
        Integer solveVariant,
        Pair<Coordinate, Coordinate> solutionCoordinates
    ) {
        Solver solver = switch (solveVariant) {
            case 1 -> new BFSAlgorithmSolver();
            case 2 -> new DFSAlgorithmSolver();
            default -> throw new IllegalStateException("Неверный выбор алгоритма решения: " + solveVariant);
        };
        return solver.solve(maze, solutionCoordinates.first(), solutionCoordinates.second());
    }

    private void drawMaze(Maze maze) {
        new ConsoleDrawer().draw(maze.getGrid());
    }

    private void drawMaze(Maze maze, List<Coordinate> path) {
        new ConsoleDrawer().draw(maze.getGrid(), path);
    }

}

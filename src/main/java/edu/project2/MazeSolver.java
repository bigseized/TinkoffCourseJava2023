package edu.project2;

import edu.project2.drawers.ConsoleDrawer;
import edu.project2.generators.Generator;
import edu.project2.generators.GeneratorFactory;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverFactory;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Pair;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.java.Log;

@Log

public class MazeSolver {
    private final static Pattern CHOOSE_PATTERN = Pattern.compile("^[1,2](.*)$");
    private final static Pattern MAZE_SIZE_PATTERN = Pattern.compile("([0-9]+)(\\D+)([0-9]+)(.*)");
    private final static Integer FIRST_NUMBER_IN_STRING = 1;
    private final static Integer SECOND_NUMBER_IN_STRING = 3;
    private final static Integer MIN_MAZE_SIZE = 4;
    private final GeneratorFactory generatorFactory = new GeneratorFactory();
    private final SolverFactory solverFactory = new SolverFactory();
    private final Scanner in = new Scanner(System.in);

    public void run() {
        log.info("Введите размер лабиринта:");
        Pair<Integer, Integer> mazeSize = getMazeSizeInput();

        log.info("Выберите алгоритм генерации:\n1) Алгоритм Прима\n2) Алгоритм DFS");
        Integer generationVariant = getChoiceInput();
        Maze maze = createMaze(mazeSize, generationVariant);
        drawMaze(maze);

        log.info("Выберите алгоритм решения:\n1) Алгоритм BFS\n2) Алгоритм DFS");
        Integer solutionVariant = getChoiceInput();
        log.info("Введите координаты входа и выхода");
        Pair<Coordinate, Coordinate> solutionCoordinates = getSolutionCoordinatesInput(maze.getGrid());
        List<Coordinate> solutionPath = solveMaze(maze, solutionVariant, solutionCoordinates);
        drawMazeWithPath(maze, solutionPath);
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
                log.log(Level.SEVERE, "Необходимо ввести 2 числа!");
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
                    log.log(Level.SEVERE, "Размер лабиринта не может быть меньше 3");
                }
            } else {
                log.log(Level.SEVERE, "Размеры лабиринта не могут быть чётными!");
            }
        }
    }

    private Pair<Coordinate, Coordinate> getSolutionCoordinatesInput(Cell[][] grid) {

        log.info("Введите координаты начала решения:");
        Coordinate startCoordinate = getCoordinates(grid);

        log.info("Введите координаты конца решения:");
        Coordinate endCoordinate = getCoordinates(grid);

        return new Pair<>(startCoordinate, endCoordinate);
    }

    private Coordinate getCoordinates(Cell[][] grid) {
        Coordinate coordinate;
        do {
            Pair<Integer, Integer> start = getPairOfIntsInput();
            coordinate = new Coordinate(start.first(), start.second());
        } while (areCoordinatesCorrect(coordinate, grid));

        return coordinate;
    }

    private boolean areCoordinatesCorrect(Coordinate coordinate, Cell[][] grid) {
        if (coordinate.row() < grid.length && coordinate.col() < grid[0].length) {
            if (grid[coordinate.row()][coordinate.col()].getType() == Cell.Type.PASSAGE) {
                return false;
            }
            log.log(Level.SEVERE, "Точка является стеной!");
        } else {
            log.log(Level.SEVERE, "Координаты выходят за границы лабиринта!");
        }
        return true;
    }

    private int getChoiceInput() {
        while (true) {
            String input = in.nextLine();
            Matcher matcher = CHOOSE_PATTERN.matcher(input);
            if (matcher.matches()) {
                return Integer.parseInt(input);
            } else {
                log.log(Level.SEVERE, "Введите 1 или 2 для выбора варианта!");
            }
        }
    }

    private Maze createMaze(Pair<Integer, Integer> size, Integer genVariant) {
        Generator generator = generatorFactory.getGenerator(genVariant);
        return generator.generate(size.first(), size.second());
    }

    private List<Coordinate> solveMaze(
        Maze maze,
        Integer solveVariant,
        Pair<Coordinate, Coordinate> solutionCoordinates
    ) {
        Solver solver = solverFactory.getSolver(solveVariant);
        return solver.solve(maze, solutionCoordinates.first(), solutionCoordinates.second());
    }

    private void drawMaze(Maze maze) {
        new ConsoleDrawer().draw(maze.getGrid());
    }

    private void drawMazeWithPath(Maze maze, List<Coordinate> path) {
        new ConsoleDrawer().drawWithPath(maze.getGrid(), path);
    }

}

package edu.project2.drawers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.utility.ArraysUtils;
import edu.project2.utility.BannerUtils;
import java.util.List;

public final class ConsoleDrawer implements Drawer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    @SuppressWarnings("RegexpSinglelineJava")
    public void draw(Cell[][] grid) {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.getType() == Cell.Type.WALL) {
                    printCell(ANSI_BLACK_BACKGROUND);
                } else {
                    printCell(ANSI_WHITE_BACKGROUND);
                }
            }
            System.out.print('\n');
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void draw(Cell[][] grid, List<Coordinate> path) {
        System.out.println(BannerUtils.solutionBanner);

        Cell[][] copyGrid = ArraysUtils.copyDeepCellArr(grid);
        boolean[][] pathInGrid = new boolean[copyGrid.length][copyGrid[0].length];

        for (var cell : path) {
            pathInGrid[cell.row()][cell.col()] = true;
        }
        for (int i = 0; i < copyGrid.length; i++) {
            for (int j = 0; j < copyGrid[i].length; j++) {
                if (pathInGrid[i][j]) {
                    printCell(ANSI_YELLOW_BACKGROUND);
                    continue;
                }
                if (copyGrid[i][j].getType() == Cell.Type.WALL) {
                    printCell(ANSI_BLACK_BACKGROUND);
                } else {
                    printCell(ANSI_WHITE_BACKGROUND);
                }
            }
            System.out.print('\n');
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printCell(String ansiBackgroundColor) {
        System.out.print(ansiBackgroundColor + "   " + ANSI_RESET);
    }
}

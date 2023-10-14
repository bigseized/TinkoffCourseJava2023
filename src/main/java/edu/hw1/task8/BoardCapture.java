package edu.hw1.task8;

public final class BoardCapture {

    private static final String ERROR_MESSAGE = "Illegal argument";

    private static final int BOARD_SIDE = 8;
    private final static int[][] POSSIBLE_KNIGHT_MOVES = {
        {-2, 1}, {-2, -1},
        {2, 1}, {2, -1},
        {-1, 2}, {1, 2},
        {-1, -2}, {1, -2}
    };

    private BoardCapture() {}

    @SuppressWarnings("checkstyle:MagicNumber") public static boolean knightBoardCapture(int[][] knightPlacement) {
        int size = knightPlacement.length;
        if (size != BOARD_SIDE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        for (int i = 0; i < BOARD_SIDE; i++) {
            if (knightPlacement[i].length != BOARD_SIDE) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        }
        for (int i = 0; i < knightPlacement.length - 1; i++) {
            for (int j = 0; j < knightPlacement[i].length - 1; j++) {
                if (knightPlacement[i][j] == 0) {
                    continue;
                }
                for (int[] move : POSSIBLE_KNIGHT_MOVES) {
                    int newX = i + move[0];
                    int newY = j + move[1];

                    if (newX >= 0 && newX < size && newY >= 0 && newY < size && knightPlacement[newX][newY] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

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

    private BoardCapture() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static boolean knightBoardCapture(int[][] knightPlacement) {
        int size = knightPlacement.length;
        if (size != BOARD_SIDE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        for (int i = 0; i < BOARD_SIDE; i++) {
            if (knightPlacement[i].length != BOARD_SIDE) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        }
        for (int y = 0; y < knightPlacement.length - 1; y++) {
            for (int x = 0; x < knightPlacement[y].length - 1; x++) {
                if (isKnightCapture(knightPlacement, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isKnightCapture(int[][] knightPlacement, int posX, int posY) {
        if (knightPlacement[posY][posX] == 0) {
            return false;
        }
        for (int[] move : POSSIBLE_KNIGHT_MOVES) {
            int newX = posY + move[0];
            int newY = posX + move[1];

            if (newX >= 0 && newX < BOARD_SIDE && newY >= 0 && newY < BOARD_SIDE && knightPlacement[newX][newY] == 1) {
                return true;
            }
        }
        return false;
    }
}

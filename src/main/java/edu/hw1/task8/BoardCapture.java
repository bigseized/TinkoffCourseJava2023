package edu.hw1.task8;

public final class BoardCapture {
    private BoardCapture() {}

    @SuppressWarnings("checkstyle:MagicNumber") public static boolean knightBoardCapture(int[][] knightPlacement) {
        int size = knightPlacement.length;
        for (int i = 0; i < knightPlacement.length - 1; i++) {
            for (int j = 0; j < knightPlacement[i].length - 1; j++) {
                if (knightPlacement[i][j] == 0) {
                    continue;
                }
                int[][] possibleMoves = {
                    {-2, 1}, {-2, -1},
                    {2, 1}, {2, -1},
                    {-1, 2}, {1, 2},
                    {-1, -2}, {1, -2}
                };

                for (int[] move : possibleMoves) {
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

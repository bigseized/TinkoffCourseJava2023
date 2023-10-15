package edu.hw1.task7;

public final class BitShift {

    private static final char EMPTY_BUFFER = '-';

    private BitShift() {}

    public static int rotateRight(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (n < 2) {
            return n;
        }
        String numberInBinaryString = Integer.toBinaryString(n);
        char[] numberInCharArr = numberInBinaryString.toCharArray();
        char buffer = EMPTY_BUFFER;
        for (int i = 0; i < shift; i++) {
            for (int j = 0; j < numberInCharArr.length - 1; j++) {
                if (buffer == EMPTY_BUFFER) {
                    buffer = numberInCharArr[j + 1];
                    numberInCharArr[j + 1] = numberInCharArr[j];
                } else {
                    char tempDigit = numberInCharArr[j + 1];
                    numberInCharArr[j + 1] = buffer;
                    buffer = tempDigit;
                }
            }
            numberInCharArr[0] = buffer;
        }
        numberInBinaryString = new String(numberInCharArr);
        return Integer.parseInt(numberInBinaryString, 2);
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (n < 2) {
            return n;
        }
        String numberInBinaryString = Integer.toBinaryString(n);
        char[] numberInCharArr = numberInBinaryString.toCharArray();
        char buffer = EMPTY_BUFFER;
        for (int i = 0; i < shift; i++) {
            for (int j = numberInCharArr.length - 1; j > 0; j--) {
                if (buffer == EMPTY_BUFFER) {
                    buffer = numberInCharArr[j - 1];
                    numberInCharArr[j - 1] = numberInCharArr[j];
                } else {
                    char tempDigit = numberInCharArr[j - 1];
                    numberInCharArr[j - 1] = buffer;
                    buffer = tempDigit;
                }
            }
            numberInCharArr[numberInCharArr.length - 1] = buffer;
        }
        numberInBinaryString = new String(numberInCharArr);
        return Integer.parseInt(numberInBinaryString, 2);
    }
}

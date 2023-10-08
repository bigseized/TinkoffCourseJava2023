package edu.hw1.task2;

public final class CountDigits {
    private static final int MOD = 10;
    private static final int COMPARE_NUMBER = 9;

    private CountDigits() {}

    public static int countDigits(int number) {
        int num = number;
        int counter = 1;
        while (num > COMPARE_NUMBER) {
            num /= MOD;
            counter++;
        }
        return counter;
    }

}

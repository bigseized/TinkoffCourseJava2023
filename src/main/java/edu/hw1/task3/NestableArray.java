package edu.hw1.task3;

public final class NestableArray {

    private NestableArray() {}

    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int j : firstArray) {
            if (j < firstMin) {
                firstMin = j;
            }
            if (j > firstMax) {
                firstMax = j;
            }
        }
        for (int i = 0; i < secondArray.length; i++) {
            if (secondArray[i] < secondMin) {
                secondMin = secondArray[i];
            }
            if (firstArray[i] > secondMax) {
                secondMax = secondArray[i];
            }
        }
        return (firstMin > secondMin || firstMax < secondMax);

    }
}

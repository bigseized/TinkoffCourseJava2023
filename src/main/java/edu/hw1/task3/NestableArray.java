package edu.hw1.task3;

public final class NestableArray {

    private NestableArray() { }


    public static boolean isNestable(int[] firstArray, int[] secondArray) {

        MinAndMaxOfArray minAndMaxOfFirstArray = findMinAndMaxOfArray(firstArray);
        MinAndMaxOfArray minAndMaxOfSecondArray = findMinAndMaxOfArray(secondArray);

        return (minAndMaxOfFirstArray.min() > minAndMaxOfSecondArray.min()
            || minAndMaxOfFirstArray.max() < minAndMaxOfSecondArray.max());
    }

    private static MinAndMaxOfArray findMinAndMaxOfArray(int[] array) {
        int minNumberOfArray = Integer.MAX_VALUE;
        int maxNumberOfArray = Integer.MIN_VALUE;
        for (int j : array) {
            if (j < minNumberOfArray) {
                minNumberOfArray = j;
            }
            if (j > maxNumberOfArray) {
                maxNumberOfArray = j;
            }
        }
        return new MinAndMaxOfArray(minNumberOfArray, maxNumberOfArray);
    }

    private record MinAndMaxOfArray(int min, int max){}

}

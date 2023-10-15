package edu.hw1.task6;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class KaprekarsConstant {

    private final static int KAPREKARS_CONST = 6174;
    private final static int MIN_AVAILABLE_VALUE = 1000;
    private final static int MAX_AVAILABLE_VALUE = 9998;
    private final static int MAX_ITERATIONS = 7;

    private KaprekarsConstant() {}

    public static int countK(int number) {
        if (number < MIN_AVAILABLE_VALUE || number > MAX_AVAILABLE_VALUE || areAllDigitsEqual(number)) {
            return -1;
        }
        if (number == KAPREKARS_CONST) {
            return 0;
        }
        int numberForTransforms = number;

        int counterOfTransforms = 0;

        while (counterOfTransforms <= MAX_ITERATIONS) {
            String numberInStringFormat = String.valueOf(numberForTransforms);
            char[] numberInCharArrayFormat = numberInStringFormat.toCharArray();
            Character[] numberInWrapCharArrFormat = typecastCharArrToWrappedCharArr(numberInCharArrayFormat);

            Character[] ascendingNumber = Arrays.copyOf(numberInWrapCharArrFormat, numberInWrapCharArrFormat.length);
            Arrays.sort(ascendingNumber);

            Character[] descendingNumber = Arrays.copyOf(numberInWrapCharArrFormat, numberInWrapCharArrFormat.length);
            Arrays.sort(descendingNumber, Collections.reverseOrder());

            int minNumber = typecastWrappedCharacterArrToInt(ascendingNumber);
            int maxNumber = typecastWrappedCharacterArrToInt(descendingNumber);
            if (maxNumber - minNumber == KAPREKARS_CONST) {
                counterOfTransforms++;
                return counterOfTransforms;
            } else {
                numberForTransforms = maxNumber - minNumber;
                counterOfTransforms++;
            }
        }
        return -1;
    }

    private static Character[] typecastCharArrToWrappedCharArr(char[] charArray) {
        Character[] wrappedCharArray = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            wrappedCharArray[i] = charArray[i];
        }
        return wrappedCharArray;
    }

    private static int typecastWrappedCharacterArrToInt(Character[] charactersArray) {
        StringBuilder arrInStringFormat = new StringBuilder();
        for (char i : charactersArray) {
            arrInStringFormat.append(i);
        }
        return Integer.parseInt(arrInStringFormat.toString());
    }

    private static boolean areAllDigitsEqual(int number) {
        String numberInStringFormat = String.valueOf(number);
        Pattern numberWithEqualDigitsPattern = Pattern.compile("^([0-9])\\1{3}$");
        Matcher matcher = numberWithEqualDigitsPattern.matcher(numberInStringFormat);
        return matcher.matches();
    }
}

package edu.hw1.task6;

import java.util.Arrays;
import java.util.Collections;

public final class KaprekarsConstant {

    final static int KAPREKARS_CONST = 6174;

    private KaprekarsConstant() {}

    private static Character[] typecastCharArrToWrappedCharArr(char[] charArray) {
        Character[] wrappedCharArray = new Character[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            wrappedCharArray[i] = charArray[i];
        }
        return wrappedCharArray;
    }

    private static int typecastWrappedCharacterArrToInt(Character[] charactersArray) {
        String arrInStringFormat = "";
        for (char i : charactersArray) {
            arrInStringFormat = arrInStringFormat.concat(String.valueOf(i));
        }
        return Integer.parseInt(arrInStringFormat);
    }

    public static int countK(int number) {
        if (number == KAPREKARS_CONST) {
            return 0;
        }
        int numberForTransforms = number;

        int counterOfTransforms = 0;

        while (true) {
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

    }
}

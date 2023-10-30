package edu.hw3.task4;

public final class NumbersConvert {

    private static final int MAX_ROMAN_NUMBER = 3999;
    private static final int MIN_ROMAN_NUMBER = 1;

    private NumbersConvert() {}

    public static String convertToRoman(int arabicNumber) {
        int tempNumber = arabicNumber;
        if (!(tempNumber >= MIN_ROMAN_NUMBER && tempNumber <= MAX_ROMAN_NUMBER)) {
            throw new IllegalArgumentException("Число должно принадлежать диапазону [1, 3999]");
        }
        StringBuilder romanNumber = new StringBuilder();
        int i = 0;
        while (tempNumber > 0) {
            if (tempNumber >= RomanNumbers.values()[i].getValue()) {
                romanNumber.append(RomanNumbers.values()[i]);
                tempNumber -= RomanNumbers.values()[i].getValue();
            } else {
                i++;
            }
        }
        return romanNumber.toString();
    }
}

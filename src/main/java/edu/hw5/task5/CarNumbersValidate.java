package edu.hw5.task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarNumbersValidate {
    private final static Pattern CAR_NUMBER_PATTERN = Pattern.compile("^[А-Я]\\d{3}[А-Я]{2}\\d{3}$");

    private CarNumbersValidate() {
    }

    public static boolean isCarNumberCorrect(String carNumber) {
        Matcher matcher = CAR_NUMBER_PATTERN.matcher(carNumber);
        return matcher.matches();
    }
}

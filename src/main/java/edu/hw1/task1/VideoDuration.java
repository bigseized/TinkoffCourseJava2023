package edu.hw1.task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VideoDuration {
    private static final int MAX_SECONDS = 60;
    private static final Pattern NOT_NEGATIVE_NUMBER_REGEX = Pattern.compile("^[0-9]*$");

    private VideoDuration() {
    }

    public static int convertTimeToSeconds(String input) {
        String[] splitedString = input.split(":");

        if (!isStringNumberValid(splitedString[0]) || !isStringNumberValid(splitedString[1])) {
            return -1;
        }

        int minutes = Integer.parseInt(splitedString[0]);
        int seconds = Integer.parseInt(splitedString[1]);

        if (seconds >= MAX_SECONDS || splitedString.length != 2) {
            return -1;
        }

        return minutes * MAX_SECONDS + seconds;
    }

    private static boolean isStringNumberValid(String numberInStringFormat) {
        Matcher matcher = NOT_NEGATIVE_NUMBER_REGEX.matcher(numberInStringFormat);
        return matcher.matches();
    }
}

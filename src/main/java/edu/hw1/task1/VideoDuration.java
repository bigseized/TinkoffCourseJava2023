package edu.hw1.task1;

public final class VideoDuration {
    private static final int MAX_SECONDS = 60;

    private VideoDuration() {
    }

    public static int convertTimeToSeconds(String input) {
        String[] splitedString = input.split(":");
        String notNegativeNumberRegex = "^[0-9]*$";
        int minutes;
        int seconds;

        if (splitedString[0].matches(notNegativeNumberRegex) && splitedString[1].matches(notNegativeNumberRegex)) {
            minutes = Integer.parseInt(splitedString[0]);
            seconds = Integer.parseInt(splitedString[1]);
        } else {
            return -1;
        }

        if (seconds >= MAX_SECONDS || splitedString.length != 2) {
            return -1;
        }

        return minutes * MAX_SECONDS + seconds;
    }
}

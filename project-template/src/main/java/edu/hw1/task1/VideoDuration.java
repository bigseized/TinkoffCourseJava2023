package edu.hw1.task1;

public final class VideoDuration {
    private static final int MAX_SECONDS = 60;

    private VideoDuration() {
    }

    static int minutesToSeconds(String input) {
        String[] splitedString = input.split(":");
        int minutes;
        int seconds;
        try {
            minutes = Integer.parseInt(splitedString[0]);
            seconds = Integer.parseInt(splitedString[1]);
        } catch (Exception e) {
            return -1;
        }

        if (minutes < 0 || seconds >= MAX_SECONDS || seconds < 0 || splitedString.length != 2) {
            return -1;
        } else {
            return minutes * MAX_SECONDS + seconds;
        }
    }
}

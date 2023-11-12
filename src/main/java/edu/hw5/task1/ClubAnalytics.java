package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClubAnalytics {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private final static String DASH = " - ";
    private static final Pattern SESSION_PATTERN =
        Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$");

    private ClubAnalytics() {
    }

    public static String calculateAverage(String[] intervals) {
        if (intervals == null || intervals.length == 0) {
            throw new IllegalArgumentException("Пустая статистика сессий");
        }

        long sumOfTimes = 0L;

        Duration[] durations = new Duration[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Matcher matcher = SESSION_PATTERN.matcher(intervals[i]);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Неверный формат ввода сессии");
            }
            String startTime = intervals[i].split(DASH)[0];
            String endTime = intervals[i].split(DASH)[1];
            LocalDateTime startLocalTime = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
            LocalDateTime endLocalTime = LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);

            durations[i] = Duration.between(startLocalTime, endLocalTime);
            sumOfTimes += durations[i].toSeconds();
        }
        LocalTime ldt = LocalTime.ofSecondOfDay(sumOfTimes / intervals.length);

        return ldt.getHour() + "ч " + ldt.getMinute() + "м";
    }
}

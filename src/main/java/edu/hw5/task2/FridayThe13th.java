package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class FridayThe13th {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static int BAD_NUMBER = 13;

    private FridayThe13th() {
    }

    public static List<String> findBadFridays(int year) {
        List<String> fridays = new ArrayList<>();

        LocalDate localDate = LocalDate.of(year, 1, 1);
        LocalDate nextFriday = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday.getYear() == year) {
            if (nextFriday.getDayOfMonth() == BAD_NUMBER) {
                fridays.add(nextFriday.format(DATE_TIME_FORMATTER));
            }
            nextFriday = nextFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return fridays;
    }

    public static LocalDate findNextBadFriday(LocalDate date) {
        LocalDate tempDate = date;
        while (tempDate.getDayOfMonth() != BAD_NUMBER || tempDate.getDayOfWeek() != DayOfWeek.FRIDAY) {
            tempDate = tempDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return tempDate;
    }
}

package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SlashDelimiterReverseParser extends AbstractDateParser {
    final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    @Override
    Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(LocalDate.parse(date, DATE_TIME_FORMATTER));
        } catch (Exception e) {
            return parseNext(date);
        }
    }
}

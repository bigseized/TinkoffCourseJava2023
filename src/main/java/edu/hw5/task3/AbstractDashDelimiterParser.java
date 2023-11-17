package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class AbstractDashDelimiterParser extends AbstractDateParser {
    final static DateTimeFormatter ONE_DIGIT_PATTERN = DateTimeFormatter.ofPattern("yyyy-M-d");
    final static DateTimeFormatter TWO_DIGIT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter currentPattern;

    public AbstractDashDelimiterParser(DateTimeFormatter pattern) {
        currentPattern = pattern;
    }

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(LocalDate.parse(date, currentPattern));
        } catch (Exception e) {
            return parseNext(date);
        }
    }
}

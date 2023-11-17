package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class AbstractSlashDelimiterParser extends AbstractDateParser {
    final static DateTimeFormatter FOUR_DIGIT_YEAR_PATTERN = DateTimeFormatter.ofPattern("d/M/yyyy");
    final static DateTimeFormatter TWO_DIGIT_YEAR_PATTERN = DateTimeFormatter.ofPattern("d/M/yy");
    private final DateTimeFormatter currentPattern;

    public AbstractSlashDelimiterParser(DateTimeFormatter pattern) {
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

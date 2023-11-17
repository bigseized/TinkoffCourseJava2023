package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelativeDatesParser2 extends AbstractDateParser {

    private final static Pattern CURRENT_DATE_FORMAT = Pattern.compile("^([0-9]+) days ago$");

    @Override
    public Optional<LocalDate> parse(String date) {
        Matcher dateMatcher = CURRENT_DATE_FORMAT.matcher(date);
        if (date.equals("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (dateMatcher.matches()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(dateMatcher.group(1))));
        } else {
            return parseNext(date);
        }
    }
}

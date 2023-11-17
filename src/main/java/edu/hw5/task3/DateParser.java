package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {

    private DateParser() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException();
        }

        AbstractDateParser parser = AbstractDateParser.makeChain(
            new AbstractDashDelimiterParser(AbstractDashDelimiterParser.TWO_DIGIT_PATTERN) {},
            new AbstractDashDelimiterParser(AbstractDashDelimiterParser.ONE_DIGIT_PATTERN) {},
            new AbstractSlashDelimiterParser(AbstractSlashDelimiterParser.FOUR_DIGIT_YEAR_PATTERN) {},
            new AbstractSlashDelimiterParser(AbstractSlashDelimiterParser.TWO_DIGIT_YEAR_PATTERN) {},
            new RelativeDatesParser(),
            new RelativeDatesParser2()
        );
        return parser.parse(date);
    }
}

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
            new DashDelimiterOneDigitParser(),
            new DashDelimiterTwoDigitsParser(),
            new SlashDelimiterReverseParser(),
            new SlashDelimiterReverseParser2(),
            new RelativeDatesParser(),
            new RelativeDatesParser2()
        );
        return parser.parse(date);
    }
}

package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class AbstractDateParser {
    AbstractDateParser nextParser;

    public static AbstractDateParser makeChain(AbstractDateParser first, AbstractDateParser... dateParsers) {
        AbstractDateParser currentParser = first;
        for (var parser : dateParsers) {
            currentParser.nextParser = parser;
            currentParser = parser;
        }
        return first;
    }

    public abstract Optional<LocalDate> parse(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (nextParser != null) {
            return nextParser.parse(date);
        } else {
            return Optional.empty();
        }
    }
}

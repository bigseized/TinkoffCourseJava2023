package edu.project3.dataPreparation.dataManagers;

import edu.project3.types.Format;
import edu.project3.types.ParsedData;
import edu.project3.types.ParsedLog;
import edu.project3.types.RawArgs;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ArgsResolver {

    private final static String MIDNIGHT = "T00:00:00+00:00";
    private final static String MIN_DATE = "0001-01-01";
    private final static String MAX_DATE = "9999-12-30";
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        .withLocale(Locale.US);
    private final SourceManager sourceManager = new SourceManager();

    public ParsedData getParsedData(RawArgs arguments) {
        ParsedLog[] logs = getLogs(arguments);
        RawArgs args = copyArgs(arguments);
        setEmptyDates(args);
        logs = filterLogs(
            OffsetDateTime.parse(args.getStartDate() + MIDNIGHT, DATE_TIME_FORMATTER),
            OffsetDateTime.parse(args.getEndDate() + MIDNIGHT, DATE_TIME_FORMATTER),
            logs
        );
        return ParsedData
            .builder()
            .logs(logs)
            .format(getFormat(args.getFormat()))
            .build();
    }

    public List<String> getPaths() {
        return sourceManager.getPaths();
    }

    private ParsedLog[] getLogs(RawArgs args) {
        return sourceManager.getParsedLogs(args.getDataSource());
    }

    private ParsedLog[] filterLogs(OffsetDateTime start, OffsetDateTime end, ParsedLog[] logs) {
        return Arrays
            .stream(logs)
            .filter(entry -> entry.timeStamp().isAfter(start) && entry.timeStamp().isBefore(end))
            .toArray(ParsedLog[]::new);
    }

    private void setEmptyDates(RawArgs args) {
        if (args.getStartDate().equals("-")) {
            args.setStartDate(MIN_DATE);
        }
        if (args.getEndDate().equals("-")) {
            args.setEndDate(MAX_DATE);
        }
    }

    private Format getFormat(String format) {
        return switch (format) {
            case "adoc" -> Format.ADOC;
            case "markdown" -> Format.MARKDOWN;
            default -> throw new IllegalArgumentException("Указан невеный формат вывода");
        };
    }

    private RawArgs copyArgs(RawArgs args) {
        RawArgs rawArgs = new RawArgs();
        rawArgs.setDataSource(args.getDataSource());
        rawArgs.setEndDate(args.getEndDate());
        rawArgs.setStartDate(args.getStartDate());
        rawArgs.setFormat(args.getFormat());
        return rawArgs;
    }
}

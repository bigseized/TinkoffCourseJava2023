package edu.project3;

import edu.project3.types.Format;
import edu.project3.types.MetricParams;
import edu.project3.types.ParsedData;
import edu.project3.types.ParsedLog;
import edu.project3.types.RawArgs;
import edu.project3.types.Request;
import edu.project3.types.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TestData {

        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd/LLL/yyyy:HH:mm:ss ZZ",
            Locale.US
        );

        public static final RawArgs rawArgs = new RawArgs("tests", "-","-","markdown");

        public static final List<String> paths = List.of("test");

        public static final ParsedLog[] parsedLogsForTests =  new ParsedLog[] {
            new ParsedLog(
                "11.71.87.42",
                "-",
                OffsetDateTime.parse("23/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/multi-state/orchestration.png",
                    "HTTP/1.1"
                ),
                new Response(400, 38),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new ParsedLog(
                "141.96.175.104",
                "-",
                OffsetDateTime.parse("25/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/architecture/attitude-oriented/success/Cross-platform-neutral.css",
                    "HTTP/1.1"
                ),
                new Response(200, 2134),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new ParsedLog(
                "165.138.198.30",
                "-",
                OffsetDateTime.parse("27/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/info-mediaries.php",
                    "HTTP/1.1"
                ),
                new Response(200, 2778),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new ParsedLog(
                "185.253.246.248",
                "-",
                OffsetDateTime.parse("30/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/Focused-encoding.svg",
                    "HTTP/1.1"
                ),
                new Response(200, 2468),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new ParsedLog(
                "204.196.83.88",
                "-",
                OffsetDateTime.parse("02/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "PUT",
                    "/Future-proofed/Customer-focused/Upgradable/internet%20solution_Re-contextualized.css",
                    "HTTP/1.1"
                ),
                new Response(200, 992),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"

            ),
            new ParsedLog(
                "72.153.133.99",
                "-",
                OffsetDateTime.parse("05/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/exuding-Secured/contingency%20Future-proofed.css",
                    "HTTP/1.1"
                ),
                new Response(200, 2024),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new ParsedLog(
                "72.153.133.97",
                "-",
                OffsetDateTime.parse("07/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
                new Request(
                    "GET",
                    "/exuding-Secured/contingency%20Future-proofed.css",
                    "HTTP/1.1"
                ),
                new Response(200, 2024),
                "-",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            )
        };

        public static MetricParams metricParams = new MetricParams(
            ParsedData.builder()
                .logs(parsedLogsForTests)
                .format(Format.MARKDOWN)
                .build() ,
            rawArgs,
            paths
        );
}

package edu.project3.dataPreparation.dataParsers;

import edu.project3.types.ParsedLog;
import edu.project3.types.Request;
import edu.project3.types.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("MagicNumber")
public class LogsParser {

    private final static Pattern NGINX_LOG = Pattern
        .compile("^(.*) - (.*) \\[(.*)] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/LLL/yyyy:HH:mm:ss ZZ",
        Locale.US
    );

    public static ParsedLog[] getParsedLogs(List<String> userLogs) {
        return userLogs
            .stream()
            .map(LogsParser::parse)
            .toArray(ParsedLog[]::new);
    }

    private static ParsedLog parse(String log) {
        Matcher matcher = NGINX_LOG.matcher(log);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат NGINX лога");
        }
        return
            ParsedLog.builder()
                .ip(matcher.group(1))
                .userName(matcher.group(2))
                .timeStamp(OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER))
                .request(Request.builder()
                    .method(matcher.group(4))
                    .url(matcher.group(5))
                    .protocol(matcher.group(6))
                    .build())
                .response(Response.builder()
                    .statusCode(Integer.parseInt(matcher.group(7)))
                    .numberOfBytes(Integer.parseInt(matcher.group(8)))
                    .build())
                .referer(matcher.group(9))
                .userAgent(matcher.group(10))
                .build();
    }

}

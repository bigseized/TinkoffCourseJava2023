package edu.project3.types;

import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record ParsedLog(
    String ip,
    String userName,
    OffsetDateTime timeStamp,
    Request request,
    Response response,
    String referer,
    String userAgent) {
}

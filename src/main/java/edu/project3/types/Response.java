package edu.project3.types;

import lombok.Builder;

@Builder
public record Response(int statusCode, int numberOfBytes) {
}

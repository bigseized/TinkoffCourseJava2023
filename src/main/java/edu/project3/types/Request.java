package edu.project3.types;

import lombok.Builder;

@Builder
public record Request(String method, String url, String protocol) {
}

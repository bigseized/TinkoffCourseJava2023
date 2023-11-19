package edu.project3.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParsedData {
    ParsedLog[] logs;
    Format format;
}

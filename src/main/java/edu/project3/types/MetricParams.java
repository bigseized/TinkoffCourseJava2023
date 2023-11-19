package edu.project3.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetricParams {
    ParsedData parsedData;
    RawArgs rawArgs;
    List<String> paths;
}

package edu.project3.types;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MetricsData {
    String title;
    String[][] data;
}

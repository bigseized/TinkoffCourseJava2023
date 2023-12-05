package edu.project3.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawArgs {
    private String dataSource;
    private String startDate;
    private String endDate;
    private String format;
}

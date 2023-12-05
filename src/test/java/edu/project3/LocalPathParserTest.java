package edu.project3;

import edu.project3.dataPreparation.dataParsers.LocalPathParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalPathParserTest {
    private static Stream<Arguments> paths() {
        String firstLog = "src/main/resources/dir/logs123.txt";
        String thirdLog = "src/main/resources/logs.txt";
        return Stream.of(
            Arguments.of(
                "src/**/dir/*",
                List.of(
                    firstLog
                )
            ),
            Arguments.of(
                "src/main/resources/*.txt",
                List.of(
                    thirdLog
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paths")
    @DisplayName("Parsing path input test")
    public void getPaths_shouldReturnListOfPaths(String path, List<String> expectedPaths) {
        assertThat(LocalPathParser.parseLocalPath(path)
                .stream()
                .map(entry -> entry.replace("\\", "/"))
                .toList())
            .usingRecursiveComparison()
            .isEqualTo(expectedPaths);
    }
}

package edu.project3;


import edu.project3.dataPreparation.dataParsers.CLIArgsParser;
import edu.project3.types.RawArgs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CLIArgsParserTest {
    private static Stream<Arguments> commands() {
        return Stream.of(
            Arguments.of(
                "--path src/main/resources/project3/* --from 2023-09-27 --to 2023-10-07 --format markdown".split(" "),
                new RawArgs(
                    "src/main/resources/project3/*",
                    "2023-09-27",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --to 2023-10-07 --format markdown".split(" "),
                new RawArgs(
                    "src/main/resources/project3/*",
                    "-",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --from 2023-09-27 --format markdown".split(" "),
                new RawArgs(
                    "src/main/resources/project3/*",
                    "2023-09-27",
                    "-",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/main/resources/project3/* --format markdown".split(" "),
                new RawArgs(
                    "src/main/resources/project3/*",
                    "-",
                    "-",
                    "markdown"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("commands")
    @DisplayName("Correct commands test")
    public void getParseFormat_shouldCorrectlyParses_andReturnParseFormat(String[] command, RawArgs expectedFormat) {
        CLIArgsParser cliArgsParser = new CLIArgsParser();
        assertThat(cliArgsParser.parseArguments(command))
            .usingRecursiveComparison()
            .isEqualTo(expectedFormat);
    }

    @Test
    @DisplayName("Command without --pass test")
    public void getParseFormat_shouldThrowException_whenStringDoesntContainPath() {
        CLIArgsParser cliArgsParser = new CLIArgsParser();
        assertThatThrownBy(() -> cliArgsParser.parseArguments("--from 2023-09-27 --format markdown".split(" "))).isInstanceOf(
            IllegalArgumentException.class);
    }
}

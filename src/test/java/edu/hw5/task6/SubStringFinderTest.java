package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task6.SubStringsFinder.isSubString;

public final class SubStringFinderTest {
    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("achfdbaabgabcaabg", "abc", true),
            Arguments.of("qwertyu", "ty", true),
            Arguments.of("biba", "boba", false)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("Корректный ввод")
    public void test1(
        String text,
        String substring,
        boolean isCorrect
    ) {
        assertThat(isSubString(text, substring)).isEqualTo(isCorrect);
    }
}

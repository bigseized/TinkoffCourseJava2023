package edu.hw5.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task5.CarNumbersValidate.isCarNumberCorrect;

public final class CarNumbersValidateTest {
    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВЕ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }
    @ParameterizedTest
    @MethodSource("data")
    public void isNumberValid_shouldReturnTrue_whenNumberIsValid(String carNumber, boolean isValid) {
        assertThat(isCarNumberCorrect(carNumber)).isEqualTo(isValid);
    }

}

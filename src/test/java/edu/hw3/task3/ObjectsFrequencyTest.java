package edu.hw3.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectsFrequencyTest {
    private static Stream<Arguments> getArgumentWhenCorrectInputTest() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("getArgumentWhenCorrectInputTest")
    @DisplayName("Проверка на обработку корректных списков")
    public void calculateFrequency_shouldReturnFrequencyDictionary_whenCorrectInput(List<Object> testList, Map<Object, Integer> expected) {
        Map<Object, Integer> actual = ObjectsFrequency.calculateFrequency(testList);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка на пустой список")
    public void calculateFrequency_shouldReturnException_whenEmptyList(){
        List<Object> list = new ArrayList<>();
        assertThatThrownBy(() -> ObjectsFrequency.calculateFrequency(list)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка на пустой список")
    public void calculateFrequency_shouldReturnException_whenNullList(){
        assertThatThrownBy(() -> ObjectsFrequency.calculateFrequency(null)).isInstanceOf(IllegalArgumentException.class);
    }
}

package edu.hw3.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BracketClusterizeTest {

    private static Stream<Arguments> getArgumentsForCorrectInputTest(){
        return Stream.of(
            Arguments.of("()()()", new ArrayList<>(Arrays.asList("()", "()", "()"))),
            Arguments.of("((()))", new ArrayList<>(List.of("((()))"))),
            Arguments.of("((()))(())()()(()())", new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())"))),
            Arguments.of("((())())(()(()()))",new ArrayList<>(Arrays.asList("((())())", "(()(()()))")))
        );
    }
    @ParameterizedTest
    @MethodSource("getArgumentsForCorrectInputTest")
    @DisplayName("Ввод корректных строк")
    public void encodeString_shouldReturnValue_whenCorrectInput(String input, ArrayList<String> ans) {
        assertThat(BracketClusterize.clusterize(input)).isEqualTo(ans);
    }

    @ParameterizedTest
    @CsvSource({
        "()(())#",
        "(()"
    })
    @DisplayName("Ввод некорректных строк")
    public void encodeString_shouldThrowException_whenIncorrectInput(String input){
        assertThatThrownBy(() ->BracketClusterize.clusterize(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод пустой строки")
    public void encodeString_shouldThrowException_whenEmptyInput(){
        assertThatThrownBy(() ->BracketClusterize.clusterize("")).isInstanceOf(IllegalArgumentException.class);
    }


}

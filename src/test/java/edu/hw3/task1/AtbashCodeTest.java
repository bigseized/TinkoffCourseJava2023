package edu.hw3.task1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AtbashCodeTest {
    @ParameterizedTest
    @CsvSource(value = {
        "Hello world!, Svool dliow!",
        "understand. ― Martin Fowler, fmwvihgzmw. ― Nzigrm Uldovi",
        "!№;%:?*()_, !№;%:?*()_"
    })
    @DisplayName("Ввод различных строк")
    public void encodeString_shouldReturnValue_whenCorrectInput(String input, String ans) {
        assertThat(AtbashCode.encodeString(input)).isEqualTo(ans);
    }

    @Test
    @DisplayName("Ввод пустой строки")
    public void encodeString_shouldReturnException_whenEmptyString() {
        assertThatThrownBy(()-> AtbashCode.encodeString("")).isInstanceOf(IllegalArgumentException.class);
    }

}

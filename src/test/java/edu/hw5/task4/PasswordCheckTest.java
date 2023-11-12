package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static edu.hw5.task4.PasswordCheck.isPasswordCorrect;

public class PasswordCheckTest {
    private static Stream<Arguments> correctPasswords() {
        return Stream.of(
            Arguments.of("~qwert"),
            Arguments.of("$%^&*(")
        );
    }

    private static Stream<Arguments> wrongPasswords() {
        return Stream.of(
            Arguments.of("qwerty"),
            Arguments.of(""),
            Arguments.of("qwerty__")
        );
    }

    @ParameterizedTest
    @MethodSource("correctPasswords")
    @DisplayName("Проверка пароля содержащего необходимые символы")
    public void test1(String signs) {
        assertTrue(isPasswordCorrect(signs));
    }

    @ParameterizedTest
    @MethodSource("wrongPasswords")
    @DisplayName("Проверка пароля не содержащего необходимые символы")
    public void test2(String password) {
        assertFalse(isPasswordCorrect(password));
    }
}

package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HangmanGameTest {
    @Test
    @DisplayName("Проверка на исключение если слово некорректной длины")
    public void session_shouldThrowException_whenGuessedWordSize_incorrect() {
        Dictionary dictionary = new InMemoryDictionary(new String[] {"tooolong", "anotherlong"});
        assertThatThrownBy(() -> new Session(dictionary)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("При превышении количества попыток игра останавливается")
    public void session_shouldReturnLose_whenMaxAttempts_Exceeded() {
        Dictionary dictionary = new InMemoryDictionary(new String[] {"bbbbb"});
        Session session = new Session(dictionary);
        session.guess('a');
        session.guess('a');
        session.guess('a');
        session.guess('a');
        session.guess('a');
        GuessResult guessResult = session.guess('a');
        assertThat(guessResult.message()).isEqualTo("You lost!");
    }

    @Test
    @DisplayName("При победе игра отстанавливается")
    public void session_shouldReturnWin_whenWordGuessed() {
        Dictionary dictionary = new InMemoryDictionary(new String[] {"abcd"});
        Session session = new Session(dictionary);
        session.guess('a');
        session.guess('b');
        session.guess('c');
        session.guess('d');
        GuessResult guessResult = session.guess('.');
        assertThat(guessResult.message()).isEqualTo("You won!");
    }


    @ParameterizedTest
    @ArgumentsSource(ArgumentProvider.class)
    @DisplayName("Проверка сообщений GuessResult")
    public void check_Intermediate_States(char letter, String output) {
        Dictionary dictionary = new InMemoryDictionary(new String[] {"bbbbb"});
        Session session = new Session(dictionary);
        GuessResult guessResult = session.guess(letter);
        assertThat(guessResult.message()).isEqualTo(output);
    }

}

final class ArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
            Arguments.of('a', "Missed, mistake 1 out of 5.\n"),
            Arguments.of('b', "Hit!\n"),
            Arguments.of('?', "You lost!")
        );
    }
}


package edu.hw3.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContactListTest {
    private static Stream<Arguments> getArgumentWhenCorrectInput() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                )
            ),
            Arguments.of(new String[] {"Paul", "Leonhard", "Carl"}, "DESC",
                List.of(
                    new Contact("Paul"),
                    new Contact("Leonhard"),
                    new Contact("Carl")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getArgumentWhenCorrectInput")
    @DisplayName("Проверка рыботы при корректном вводе")
    public void parseContact_shouldReturnSortedList_whenCorrectInput(
        String[] personsList,
        String sortType,
        List<Contact> ans
    ) {
        assertThat(ContactsList.parseContacts(personsList, sortType)).isEqualTo(ans);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустой массив или null")
    public void parseContact_shouldReturnEmptyList_whenInputIsNullOrEmpty(String[] emptyStringArray) {
        assertThatThrownBy(() -> ContactsList.parseContacts(emptyStringArray, "ASC")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Неверный ввод типа сортировки")
    public void parseContact_shouldReturnEmptyList_whenInputIsNullOrEmpty() {
        assertThatThrownBy(() -> ContactsList.parseContacts(new String[] {"John Locke"}, "ASCENDING")).isInstanceOf(IllegalArgumentException.class);
    }

}

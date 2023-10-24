package edu.hw3.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TreeMapComparatorTest {

    @Test
    @DisplayName("Проверка корректной работы с ключом null")
    public void addNullShouldWork_ifCorrect(){
        TreeMap<String, String> tree = new TreeMap<>(new TreeMapComparator());
        tree.put("b","value");
        tree.put("a","test");
        tree.put("normalString",null);
        tree.put(null, "test");
        assertAll(
            () -> assertThat(tree.containsKey(null)).isTrue(),
            () -> assertThat(tree.firstKey()).isEqualTo(null)
        );
    }
}

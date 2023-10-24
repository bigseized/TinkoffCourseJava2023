package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Проверка на обратный вывод List")
    public void list_shouldReturnBackwardList() {
        Iterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly(3, 2, 1);
    }

    @Test
    @DisplayName("Проверка на обратный вывод LinkedHashSet")
    public void linkedHashSet_shouldReturnBackwardList() {
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(Set.of(3, 2, 1, 4));
        Iterator<Integer> backwardIterator = new BackwardIterator<>(hashSet);
        List<Integer> list = new ArrayList<>(hashSet);
        List<Integer> backwardList = new ArrayList<>();
        while (backwardIterator.hasNext()) {
            backwardList.add(backwardIterator.next());
        }
        assertThat(backwardList).isEqualTo(list.reversed());
    }
}

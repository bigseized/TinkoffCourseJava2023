package edu.hw1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NestableArrayTest {
    @Test
    @DisplayName("isNestable([1, 2, 3, 4], [0, 6]) -> true")
    public void isNestable_shouldReturnValue_whenCorrectInput_1Test() {
        int[] firstArray = {1,2,3,4};
        int[] secondArray = {0,6};
        boolean ans = true;
        assertThat(NestableArray.isNestable(firstArray, secondArray)).isEqualTo(ans);
    }
    @Test
    @DisplayName("isNestable([3, 1], [4, 0]) -> true")
    public void isNestable_shouldReturnValue_whenCorrectInput_2Test() {
        int[] firstArray = {3,1};
        int[] secondArray = {4,0};
        boolean ans = true;
        assertThat(NestableArray.isNestable(firstArray, secondArray)).isEqualTo(ans);
    }
    @Test
    @DisplayName("isNestable([9, 9, 8], [8, 9]) -> false")
    public void isNestable_shouldReturnValue_whenCorrectInput_3Test() {
        int[] firstArray = {9,9,8};
        int[] secondArray = {8,9};
        boolean ans = false;
        assertThat(NestableArray.isNestable(firstArray, secondArray)).isEqualTo(ans);
    }

    @Test
    @DisplayName("isNestable([1, 2, 3, 4], [2, 3]) -> false")
    public void isNestable_shouldReturnValue_whenCorrectInput_4Test() {
        int[] firstArray = {1,2,3,4};
        int[] secondArray = {2,3};
        boolean ans = false;
        assertThat(NestableArray.isNestable(firstArray, secondArray)).isEqualTo(ans);
    }

}

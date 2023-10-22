package edu.hw2.task4;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.DeterminantOfFunctionCallLocation.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeterminantOfFunctionCallLocationTest {

    @Test
    @DisplayName("Вывод места вызова (метод и класс)")
    public void callingInfo_shouldReturnNameOfLastCalledMethod() {
        assertThat(callingInfo()).isEqualTo(new CallingInfo(
            "edu.hw2.task4.DeterminantOfFunctionCallLocationTest",
            "callingInfo_shouldReturnNameOfLastCalledMethod"
        ));
    }

}

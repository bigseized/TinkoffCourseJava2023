package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.hw2.task1.Expr.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateExpressionsTest {
    @Test
    @DisplayName("Проверка вычислений и обработки объектов вместе с примитивами")
    public void isCalculateExpression_returnCorrectValue_test1(){
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("Проверка дробного ответа и отрицательной степени")
    public void isCalculateExpression_returnCorrectValue_test2(){
        var two = new Constant(2);
        var four = new Constant(4);
        var sumTwoFour = new Addition(two,four);
        var exp = new Exponent(sumTwoFour, -2);

        assertThat(exp.evaluate()).isEqualTo((double) 1 / 36);
    }

    @Test
    @DisplayName("Проверка реализации вычитания через Addition")
    public void isCalculateExpression_returnCorrectValue_test3(){
        var two = new Constant(2);
        var four = new Constant(4);
        var multTwoFour = new Multiplication(two,four);
        var exp = new Exponent(multTwoFour, 2);
        var sixty = new Negate(60);
        var res = new Addition(exp, sixty);

        assertThat(res.evaluate()).isEqualTo(4);
    }
}

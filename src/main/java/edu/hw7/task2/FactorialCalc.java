package edu.hw7.task2;

import java.util.stream.LongStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FactorialCalc {
    public static Long calculateFactorial(int number) {
        return LongStream
            .range(1, number + 1)
            .parallel()
            .reduce((number1, number2) -> number1 * number2)
            .orElse(-1);
    }
}

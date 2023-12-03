package edu.hw8.task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw8.task2.FibonacciCalc.getFib;
import static org.assertj.core.api.Assertions.assertThat;

class FibonacciCalcTest {
    List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);

    @Test
    @DisplayName("FibonacciTestWithPool")
    public void fib_Test() throws InterruptedException {
        List<Integer> result = new CopyOnWriteArrayList<>();
        FixedThreadPull fixedThreadPull = FixedThreadPull.create(6);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            fixedThreadPull.execute(() -> result.add(getFib(finalI)));
        }
        fixedThreadPull.start();
        Thread.sleep(1000);
        fixedThreadPull.close();
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);

    }

}

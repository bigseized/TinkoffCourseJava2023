package edu.hw11.task2;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyMethodOverrideTest {

    public static class RedefineClass {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    @DisplayName("Переопределение метода sum в существующем классе")
    @SneakyThrows
    public void overrideMethod() {
        ByteBuddyAgent.install();
        DynamicType.Loaded<ArithmeticUtils> sum = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.is(ArithmeticUtils.class.getMethod("sum", int.class, int.class)))
            .intercept(MethodDelegation.to(RedefineClass.class))
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        ArithmeticUtils rewritedArithmeticUtils = new ArithmeticUtils();
        assertEquals(rewritedArithmeticUtils.sum(5, 4), 20);
    }
}

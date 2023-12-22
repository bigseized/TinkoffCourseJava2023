package edu.hw11.task3;

import lombok.Cleanup;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyClassWithMethodTest {
    @Test
    @DisplayName("Создание класса с методом fib")
    @SneakyThrows
    public void create() {
        @Cleanup
        DynamicType.Unloaded<?> unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciClass")
            .defineMethod("fib", int.class, Modifier.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public ByteCodeAppender appender(Target target) {
                    return (mv, context, md) -> {
                        Label l1 = new Label();
                        Label l2 = new Label();

                        //L0
                        mv.visitCode();
                        // if (n == 1 || n == 0)
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitJumpInsn(Opcodes.IF_ICMPEQ, l1); // if n == 1
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitJumpInsn(Opcodes.IFNE, l2); // if n == 0

                        //L1
                        //return n
                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.IRETURN);

                        //L2
                        //return fib(n - 1) + fib(n - 2);
                        mv.visitLabel(l2);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB); // n - 1
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciClass", "fib", "(I)I");
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB); // n - 2
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciClass", "fib", "(I)I");
                        mv.visitInsn(Opcodes.IADD);
                        mv.visitInsn(Opcodes.IRETURN);
                        mv.visitEnd();
                        return new ByteCodeAppender.Size(4, 2);
                    };
                }

                @Override
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make();

        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();

        Method fibMethod = dynamicType.getDeclaredMethod("fib", int.class);
        int result = (int) fibMethod.invoke(dynamicType.getDeclaredConstructor().newInstance(), 4);

        assertEquals(result, 3);
    }
}

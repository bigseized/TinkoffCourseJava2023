package edu.hw2.task4;

public class DeterminantOfFunctionCallLocation {

    private DeterminantOfFunctionCallLocation() {}

    public static CallingInfo callingInfo() {
        Throwable throwable = new Throwable();
        StackTraceElement stackTraceElement = throwable.getStackTrace()[1];
        return new CallingInfo(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {}
}

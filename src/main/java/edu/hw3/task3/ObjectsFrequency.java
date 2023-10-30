package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjectsFrequency {

    private ObjectsFrequency() {}

    public static Map<Object, Integer> calculateFrequency(List<Object> objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Null object reference");
        }
        if (objects.isEmpty()) {
            throw new IllegalArgumentException("List of data is empty");
        }

        return objects.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }
}

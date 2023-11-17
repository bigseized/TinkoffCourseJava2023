package edu.project2.generators;

import java.util.HashMap;

public class GeneratorFactory {
    private final HashMap<Integer, Generator> generators = new HashMap<>();

    public Generator getGenerator(int generatorCode) {
        Generator generator = generators.get(generatorCode);
        if (generator == null) {
            generator = switch (generatorCode) {
                case 1 -> new PrimsAlgorithmGenerator();
                case 2 -> new DFSAlgorithmGenerator();
                default -> throw new IllegalStateException("Неверный выбор алгоритма генерации: " + generatorCode);
            };
            generators.put(generatorCode, generator);
        }
        return generator;
    }
}

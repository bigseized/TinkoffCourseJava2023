package edu.hw4;

import edu.hw4.Animal.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Sex;

public class AnimalUtils {

    private final static int MIN_HEIGHT = 100;

    private AnimalUtils() {
    }

    public static List<Animal> sortByHeight(List<Animal> animalsList) {
        return animalsList
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> sortHeadByWeight(List<Animal> animalsList, int k) {
        return animalsList.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Type, Integer> groupAnimalsByType(List<Animal> animalsList) {
        return animalsList
            .stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(elem -> 1)));
    }

    public static Animal findLongestNameAnimal(List<Animal> animalsList) {
        return animalsList
            .stream()
            .max(Comparator.comparing(elem -> elem.name().length()))
            .orElse(null);
    }

    public static Sex findMostNumerousSex(List<Animal> animalsList) {
        return Objects.requireNonNull(animalsList
            .stream()
            .collect(Collectors
                .groupingBy(Animal::sex, Collectors.summingInt(elem -> 1)))
            .entrySet()
            .stream()
            .max(Comparator.comparingInt(Map.Entry::getValue)).orElse(null)).getKey();
    }

    public static Map<Type, Animal> findMostHeavyAnimalInType(List<Animal> animalsList) {
        return animalsList
            .stream()
            .collect(Collectors
                .toMap(
                    Animal::type,
                    elem -> elem,
                    BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
                ));
    }

    public static Animal findOldestAnimal(List<Animal> animalsList, Integer k) {
        return animalsList
            .stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList()
            .get(k - 1);
    }

    public static Optional<Animal> findMostHeavyUnderHeight(List<Animal> animalsList, int k) {
        return animalsList
            .stream()
            .filter(elem -> elem.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer countPaws(List<Animal> animalsList) {
        return animalsList
            .stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> findAnimalsAgeNotEqualPaws(List<Animal> animalsList) {
        return animalsList
            .stream()
            .filter(elem -> elem.age() != elem.paws())
            .toList();
    }

    public static List<Animal> findBitingAnimals(List<Animal> animalsList) {
        return animalsList
            .stream()
            .filter(elem -> elem.height() > MIN_HEIGHT && elem.bites())
            .toList();
    }

    public static Integer findAnimalsWeightGreaterHeight(List<Animal> animalsList) {
        return (int) animalsList.stream()
            .filter(elem -> elem.weight() > elem.height())
            .count();
    }

    public static List<Animal> findAnimalsMoreThanTwoWordsName(List<Animal> animalsList) {
        return animalsList
            .stream()
            .filter(elem -> elem.name()
                .split(" ").length > 2)
            .toList();
    }

    public static Boolean isDogHeightenThanValueExist(List<Animal> animalsList, int k) {
        return animalsList
            .stream()
            .anyMatch(elem -> elem.type() == Type.DOG && elem.height() > k);
    }

    public static Integer sumWeightOfAnimalsAtAge(List<Animal> animalsList, int k, int l) {
        return animalsList
            .stream()
            .filter(elem -> elem.age() >= k && elem.age() <= l)
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> sortList(List<Animal> animalsList) {
        return animalsList.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean isSpidersBiteOftenDogs(List<Animal> animalsList) {
        Map<Type, Integer> map = animalsList
            .stream()
            .filter(elem -> elem.type() == Type.SPIDER || elem.type() == Type.DOG)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(elem -> elem.bites() ? 1 : 0)));
        return map.getOrDefault(Type.SPIDER, map.getOrDefault(Type.DOG, -1))
            > map.getOrDefault(Type.DOG, map.getOrDefault(Type.SPIDER, -1));
    }

    @SafeVarargs
    public static Animal findHeaviestFish(List<Animal>... animalLists) {
        return Arrays.stream(animalLists)
            .flatMap(Collection::stream)
            .filter(elem -> elem.type() == Type.FISH)
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> findValidationExceptionsAnimals(List<Animal> animalsList) {
        return animalsList.stream()
            .filter(elem -> !validateAnimal(elem).isEmpty())
            .collect(Collectors.toMap(Animal::name, AnimalUtils::validateAnimal));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.name().isBlank()) {
            errors.add(new ValidationError("name", "Name of animal must contains at least 1 letter"));
        }
        if (animal.age() <= 0) {
            errors.add(new ValidationError("age", "Age can't be below zero!"));
        }
        if (animal.height() <= 0) {
            errors.add(new ValidationError("height", "Height can't be below zero!"));
        }
        if (animal.weight() <= 0) {
            errors.add(new ValidationError("weight", "Weight can't be below zero!"));
        }
        return errors;
    }

    public static Map<String, String> findValidationExceptionsAnimalsPrettier(List<Animal> animalsList) {
        return animalsList
            .stream()
            .filter(elem -> !validateAnimal(elem).isEmpty())
            .collect(Collectors.toMap(Animal::name, animal -> validateAnimal(animal)
                .stream()
                .map(ValidationError::errorName)
                .collect(Collectors.joining(", "))));
    }

}


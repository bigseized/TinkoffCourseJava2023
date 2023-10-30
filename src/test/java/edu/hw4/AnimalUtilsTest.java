package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalUtilsTest {
    private List<Animal> animals;

    @BeforeEach
    public void makeList() {
        animals = new ArrayList<>();
        animals.add(new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
            5, 36, 6, true
        ));
        animals.add(new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
            4, 50, 13, true
        ));
        animals.add(new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
            10, 103, 20, true
        ));
        animals.add(new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
            11, 120, 36, false
        ));
        animals.add(new Animal("Katya", Animal.Type.BIRD, Animal.Sex.F,
            2, 10, 2, false
        ));
        animals.add(new Animal("Garik Buldog Harlamov", Animal.Type.BIRD, Animal.Sex.M,
            3, 11, 3, false
        ));
        animals.add(new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
            1, 5, 1, false
        ));
        animals.add(new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
            3, 17, 10, false
        ));
        animals.add(new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
            1, 3, 1, true
        ));
        animals.add(new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
            1, 4, 2, true
        ));

    }

    @Test
    void sortByHeight() {
        animals = AnimalUtils.sortByHeight(animals);
        int prev = 0;
        for (Animal animal : animals) {
            assertTrue(animal.height() >= prev);
            prev = animal.height();
        }
    }

    public static Stream<Arguments> weightSortData() {
        return Stream.of(
            Arguments.of(
                3,
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                )
            ),
            Arguments.of(
                1,
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("weightSortData")
    public void sortHeadByWeight(
        int firstElements,
        List<Animal> expected
    ) {
        assertThat(AnimalUtils.sortHeadByWeight(animals, firstElements))
            .isEqualTo(expected);
    }

    @Test
    void groupAnimalsByType() {
        Map<Animal.Type, Integer> map = AnimalUtils.groupAnimalsByType(animals);
        assertEquals(2, (int) map.get(Animal.Type.BIRD));
        assertEquals(2, (int) map.get(Animal.Type.CAT));
        assertEquals(2, (int) map.get(Animal.Type.DOG));
        assertEquals(2, (int) map.get(Animal.Type.SPIDER));
        assertEquals(2, (int) map.get(Animal.Type.FISH));
    }

    @Test
    void findLongestNameAnimal() {
        Animal animalLongestName = AnimalUtils.findLongestNameAnimal(animals);
        assertNotEquals("Grisha", animalLongestName.name());
        assertEquals("Garik Buldog Harlamov", animalLongestName.name());
    }

    @Test
    void findMostNumerousSex() {
        Animal.Sex mostSex = AnimalUtils.findMostNumerousSex(animals);
        assertEquals(Animal.Sex.M, mostSex);
    }

    @Test
    public void findMostHeavyAnimalInType() {
        assertThat(AnimalUtils.findMostHeavyAnimalInType(animals)).isEqualTo(Map.of(
            Animal.Type.CAT, new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                4, 50, 13, true
            ),
            Animal.Type.DOG, new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                11, 120, 36, false
            ),
            Animal.Type.FISH, new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                3, 17, 10, false
            ),
            Animal.Type.BIRD, new Animal("Garik Buldog Harlamov", Animal.Type.BIRD, Animal.Sex.M,
                3, 11, 3, false
            ),
            Animal.Type.SPIDER, new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                1, 4, 2, true
            )
        ));
    }

    public static Stream<Arguments> findOldestData() {
        return Stream.of(
            Arguments.of(
                1,
                new Animal(
                    "Alisa", Animal.Type.DOG, Animal.Sex.F,
                    11, 120, 36, false
                )
            ),
            Arguments.of(
                2,
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findOldestData")
    public void findOldestAnimal(int mostOldest, Animal expected) {
        assertThat(AnimalUtils.findOldestAnimal(animals, mostOldest)).isEqualTo(expected);
    }

    @Test
    public void findMostHeavyUnderHeight() {
        assertAll(
            () -> assertFalse(AnimalUtils.findMostHeavyUnderHeight(animals, 2)
                .isPresent()),
            () -> assertThat(AnimalUtils.findMostHeavyUnderHeight(animals, 10)
                .orElse(null))
                .isEqualTo(new Animal(
                    "Liza", Animal.Type.SPIDER, Animal.Sex.M,
                    1, 4, 2, true
                ))
        );
    }

    @Test
    public void countPaws() {
        assertThat(AnimalUtils.countPaws(animals)).isEqualTo(36);
    }

    @Test
    public void findAnimalsAgeNotEqualPaws() {
        assertThat(AnimalUtils.findAnimalsAgeNotEqualPaws(animals)).isEqualTo(
            List.of(
                new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
                    5, 36, 6, true
                ),
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                ),
                new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                    11, 120, 36, false
                ),
                new Animal("Garik Buldog Harlamov", Animal.Type.BIRD, Animal.Sex.M,
                    3, 11, 3, false
                ),
                new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
                    1, 5, 1, false
                ),
                new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                    3, 17, 10, false
                ),
                new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
                    1, 3, 1, true
                ),
                new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                    1, 4, 2, true
                )
            )
        );
    }

    @Test
    public void findBitingAnimals() {
        assertThat(AnimalUtils.findBitingAnimals(animals)).isEqualTo(
            List.of(
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                )
            )
        );
    }

    public static Stream<Arguments> heavierThanHeightTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Albert", Animal.Type.FISH, Animal.Sex.M,
                        3, 20, 21, false
                    ),
                    new Animal("Franchesco", Animal.Type.FISH, Animal.Sex.M,
                        3, 20, 15, false
                    )
                ), 1
            ),
            Arguments.of(
                List.of(
                    new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
                        5, 36, 6, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    ),
                    new Animal("Katya", Animal.Type.BIRD, Animal.Sex.F,
                        2, 10, 2, false
                    ), new Animal("Garik", Animal.Type.BIRD, Animal.Sex.M,
                        3, 11, 3, false
                    ),
                    new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
                        1, 5, 1, false
                    ),
                    new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                        3, 17, 10, false
                    ), new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
                        1, 1, 2, true
                    ),
                    new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                        1, 4, 2, true
                    )
                ), 1
            )
        );
    }

    @ParameterizedTest
    @MethodSource("heavierThanHeightTest")
    public void findAnimalsWeightGreaterHeight(List<Animal> animals, int counter) {
        assertThat(AnimalUtils.findAnimalsWeightGreaterHeight(animals)).isEqualTo(counter);
    }

    @Test
    public void findAnimalsMoreThanTwoWordsName() {
        assertThat(AnimalUtils.findAnimalsMoreThanTwoWordsName(animals)).isEqualTo(List.of(new Animal(
            "Garik Buldog Harlamov",
            Animal.Type.BIRD,
            Animal.Sex.M,
            3,
            11,
            3,
            false
        )));
    }

    @Test
    public void isDogHeightenThanValueExist() {
        assertAll(
            () -> assertTrue(AnimalUtils.isDogHeightenThanValueExist(animals, 100)),
            () -> assertFalse(AnimalUtils.isDogHeightenThanValueExist(animals, 130))
        );
    }

    @Test
    public void sumWeightOfAnimalsAtAge() {
        assertThat(AnimalUtils.sumWeightOfAnimalsAtAge(animals, 0, 4)).isEqualTo(32);
    }

    @Test
    public void sortList() {
        List<Animal> animalsCopy = new ArrayList<>(animals);

        animals = AnimalUtils.sortList(animals);
        animalsCopy.sort(Comparator.comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name));
        assertEquals(animals, animalsCopy);
    }

    @Test
    public void isSpidersBiteOftenDogs() {
        List<Animal> listWithNoDogsAndSpiders = List.of(
            new Animal("Igor", Animal.Type.CAT, Animal.Sex.M, 1, 15, 7, false),
            new Animal("Ira", Animal.Type.CAT, Animal.Sex.F, 1, 15, 7, false)
        );
        List<Animal> list = List.of(
            new Animal("Igor", Animal.Type.DOG, Animal.Sex.M, 1, 30, 7, true),
            new Animal("Ilya", Animal.Type.DOG, Animal.Sex.M, 1, 30, 7, true),
            new Animal("Ira", Animal.Type.SPIDER, Animal.Sex.F, 1, 15, 2, true)
        );
        assertAll(
            () -> assertTrue(AnimalUtils.isSpidersBiteOftenDogs(animals)),
            () -> assertFalse(AnimalUtils.isSpidersBiteOftenDogs(listWithNoDogsAndSpiders)),
            () -> assertFalse(AnimalUtils.isSpidersBiteOftenDogs(list))
        );
    }

    @Test
    public void findHeaviestFish() {
        List<Animal> list = List.of(
            new Animal("Igor", Animal.Type.FISH, Animal.Sex.M, 1, 14, 12, true),
            new Animal("Ilya", Animal.Type.FISH, Animal.Sex.M, 1, 15, 7, true)
        );
        assertThat(AnimalUtils.findHeaviestFish(animals, list)).isEqualTo(new Animal(
            "Igor",
            Animal.Type.FISH,
            Animal.Sex.M,
            1,
            14,
            12,
            true
        ));
    }

    public static Stream<Arguments> validationErrors() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                ),
                Map.of(
                    "Alisa", Set.of(new ValidationError("height", "Height can't be below zero!"))
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        -10, 103, -20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        -4, -50, -13, true
                    )
                ),
                Map.of(
                    "Alisa", Set.of(new ValidationError("height", "Height can't be below zero!")),
                    "Grisha", Set.of(
                        new ValidationError("age", "Age can't be below zero!"),
                        new ValidationError("weight", "Weight can't be below zero!")
                    ),
                    "Gena", Set.of(
                        new ValidationError("age", "Age can't be below zero!"),
                        new ValidationError("height", "Height can't be below zero!"),
                        new ValidationError("weight", "Weight can't be below zero!")
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("validationErrors")
    public void findValidationExceptionsAnimals(
        List<Animal> animals,
        Map<String, Set<ValidationError>> errors
    ) {
        assertThat(AnimalUtils.findValidationExceptionsAnimals(animals)).isEqualTo(errors);
    }

    public static Stream<Arguments> validationErrorsPrettier() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                ),
                Map.of(
                    "Alisa", "height")
            ),
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        -10, 103, -20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        -4, -50, -13, true
                    )
                ),
                Map.of(
                    "Alisa", "height",
                    "Grisha", "weight, age",
                    "Gena", "weight, height, age"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("validationErrorsPrettier")
    public void findValidationExceptionsAnimalsPrettier(
        List<Animal> animals,
        Map<String, String> errors
    ) {
        assertThat(AnimalUtils.findValidationExceptionsAnimalsPrettier(animals)).isEqualTo(errors);
    }
}

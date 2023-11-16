package edu.hw6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {
    private final Path filePath = Path
        .of("src\\main\\java\\edu\\hw6\\task1\\diskMap.txt");
    private final Map<String, String> mapForTest = Map.of(
        "jo", "buba",
        "weis", "plug"
    );

    @Test
    @DisplayName("Creating DiskMap file test")
    public void shouldCreateNewFileDiskMapFile() {
        DiskMap diskMap = new DiskMap();
        assertTrue(Files.exists(filePath));
    }

    @Test
    @DisplayName("isEmpty method test")
    public void isEmpty_shouldReturnTrue_whenFileIsEmpty() {
        assertTrue(new DiskMap().isEmpty());
    }

    @Test
    @DisplayName("put method test")
    public void put_shouldPutStringInCreatedFile() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("Java", "Tinkoff");
        try {
            var lines = Files.readAllLines(filePath);
            assertTrue(lines.contains("Java:Tinkoff"));
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("putAll method test")
    public void putAll_shouldPutElementsOfMapInFile() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        try {
            var lines = Files.readAllLines(filePath);
            assertTrue(lines.containsAll(List.of("jo:buba", "weis:plug")));
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("containsKey method test")
    public void containsKey_shouldReturnTrue_whenKeyInFile() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        assertTrue(diskMap.containsKey("jo"));
    }

    @Test
    @DisplayName("containsValue method test")
    public void containsValue_shouldReturnTrue_whenValueInFile() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        assertTrue(diskMap.containsValue("buba"));
    }

    @Test
    @DisplayName("get method test")
    public void get_shouldReturnStringFromMap() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        assertEquals(diskMap.get("jo"), "buba");
    }

    @Test
    @DisplayName("size method test")
    public void size_shouldReturnCorrectSizeOfMap() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("Java", "Tinkoff");
        diskMap.putAll(mapForTest);
        diskMap.remove("jo");
        assertEquals(2, diskMap.size());
    }

    @Test
    @DisplayName("remove method test")
    public void remove_shouldDeleteLineFromMap() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        diskMap.remove("jo");
        try {
            assertThat(Files.readAllLines(filePath)).containsExactly("weis:plug");
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("clear method test")
    public void clear_shouldDeleteAllLinesFromFile() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
    }

    @Test
    @DisplayName("keySet method test")
    public void keySet_shouldReturnSetOfKeysOfMap() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        var keys = diskMap.keySet();
        assertThat(keys).isEqualTo(Set.of("jo", "weis"));
    }

    @Test
    @DisplayName("values method test")
    public void values_shouldReturnSetOfValuesOfMap() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        var values = diskMap.values();
        assertThat(values).isEqualTo(Set.of("buba", "plug"));
    }

    @Test
    @DisplayName("entry set test")
    public void entrySet_shouldReturnData() {
        DiskMap diskMap = new DiskMap();
        diskMap.putAll(mapForTest);
        var values = diskMap.entrySet();
        assertThat(values).isEqualTo(Set.of(new AbstractMap.SimpleEntry("jo", "buba"),
            new AbstractMap.SimpleEntry("weis", "plug")));
    }
}

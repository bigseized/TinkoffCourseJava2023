package edu.hw8.task3;

import java.util.HexFormat;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw8.task3.Bruteforce.resolvePassword;
import static org.assertj.core.api.Assertions.assertThat;

class BruteforceTest {
    Map<String, byte[]> database =
        Map.of("a.v.petrov", HexFormat.of().parseHex("1a1dc91c907325c69271ddf0c944bc72"),
            "v.v.belov", HexFormat.of().parseHex("ad51b6e936950dd05d828d6c0469e9d5"),
            "a.s.ivanov", HexFormat.of().parseHex("f1f1de48de3dadd17c8fb788ce132e72"),
            "k.p.maslov", HexFormat.of().parseHex("9c448c3ecfe7dac878aa3858cae7eaf8")
        );

    Map<String, String> result =
        Map.of("a.v.petrov", "pass",
            "v.v.belov", "QwE2",
            "a.s.ivanov", "BubA1",
            "k.p.maslov", "hQ1X"
        );

    @Test
    @DisplayName("oneThreadTest")
    public void bruteForce_oneThreadTest() {
        Map<String, String> res = resolvePassword(1, database);
        assertThat(res).containsAllEntriesOf(result);
    }

    @Test
    @DisplayName("asyncTest")
    public void bruteForce_AsyncTest() {
        Map<String, String> res = resolvePassword(1, database);
        assertThat(res).containsAllEntriesOf(result);
    }

}

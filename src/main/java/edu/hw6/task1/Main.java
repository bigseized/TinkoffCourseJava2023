package edu.hw6.task1;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DiskMap.deleteFile();
        DiskMap diskMap = new DiskMap();
        diskMap.put("piska", "zalupa");
        diskMap.put("siska", "zalupa");
        diskMap.putAll(Map.of("неистовый гей","мой повелитель","очко вдребездень","вся конча уже внутри"));
        diskMap.remove("siska");
        System.out.println(diskMap.entrySet());
    }
}

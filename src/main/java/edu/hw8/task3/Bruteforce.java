package edu.hw8.task3;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Bruteforce {
    private static final int MAX_PASS_LENGTH = 6;
    public static final int ALPHABET_SIZE = 10 + 26 + 26;
    private volatile boolean isAllResolved = false;
    public static byte[] characterSet;
    static Map<String, String> result = new HashMap<>();
    static Map<String, byte[]> database;

    /**
     * При numberOfThreads = 1 - однопоточная версия
     */
    @SneakyThrows
    public static Map<String, String> resolvePassword(int numberOfThreads, Map<String, byte[]> data) {
        characterSet = getPossibleChars();
        database = data;
        if (numberOfThreads == 1) {
            return generateOneThread();
        }
        return generateAsync(numberOfThreads);

    }

    private static byte[] getPossibleChars() {
        byte[] possibleCharsBytes = new byte[ALPHABET_SIZE];

        int index = 0;
        for (byte c = '0'; c <= '9'; c++) {
            possibleCharsBytes[index++] = c;
        }
        for (byte c = 'A'; c <= 'Z'; c++) {
            possibleCharsBytes[index++] = c;
        }
        for (byte c = 'a'; c <= 'z'; c++) {
            possibleCharsBytes[index++] = c;
        }

        return possibleCharsBytes;
    }

    private static Map<String, String> generateOneThread() {
        for (int i = 1; i < MAX_PASS_LENGTH; i++) {
            generatePasswords(new byte[i], characterSet, i);
        }
        return result;
    }

    @SneakyThrows
    private static Map<String, String> generateAsync(int numberOfThreads) {
        @Cleanup
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 1; i < MAX_PASS_LENGTH; i++) {
            for (byte character : characterSet) {
                byte[] prefix = new byte[i];
                prefix[i - 1] = character;
                Worker worker = new Worker(prefix, characterSet, i - 1);
                executorService.execute(worker);
            }
        }
        executorService.shutdown();
        if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
            return result;
        } else {
            throw new RuntimeException("ExecutorService closing Interrupted");
        }
    }

    private static void generatePasswords(byte[] prefix, byte[] characterSet, int remainingLength) {
        if (isAllResolved || (remainingLength == 0 && !isAllResolved)) {
            comparePass(prefix);
            return;
        }

        for (byte currentChar : characterSet) {
            prefix[remainingLength - 1] = currentChar;
            generatePasswords(prefix, characterSet, remainingLength - 1);
        }
    }

    @SneakyThrows
    private static void comparePass(byte[] prefix) {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        byte[] encodedGenPass = messageDigest.digest(prefix);
        String user = getNameIfExist(encodedGenPass);
        if (user != null && !user.isEmpty()) {
            synchronized (Bruteforce.class) {
                result.put(user, new String(prefix));
                if (result.size() == database.size()) {
                    isAllResolved = true;
                }
            }
        }
    }

    private static String getNameIfExist(byte[] encodedGenPass) {
        for (var value : database.entrySet()) {
            if (MessageDigest.isEqual(encodedGenPass, value.getValue())) {
                return value.getKey();
            }
        }
        return null;
    }

    @AllArgsConstructor
    private static class Worker implements Runnable {
        byte[] prefix;
        byte[] characterSet;
        int remainingLength;

        @Override
        public void run() {
            generatePasswords(prefix, characterSet, remainingLength);
        }

    }
}

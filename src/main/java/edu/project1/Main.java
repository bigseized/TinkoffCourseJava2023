package edu.project1;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman consoleHangman = new ConsoleHangman("surf", "java", "drain");
        consoleHangman.run();
    }
}

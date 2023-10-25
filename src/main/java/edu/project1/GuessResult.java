package edu.project1;

sealed interface GuessResult {
    char[] state();

    String message();

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public String message() {
            return "You lost!";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public String message() {
            return "You won!";
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public String message() {
            return "Hit!\n";
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public String message() {
            return String.format("Missed, mistake %d out of %d.\n", attempt, maxAttempts);
        }
    }
}

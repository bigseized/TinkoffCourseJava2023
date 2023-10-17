package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        public Constant(Expr number) {
            this(number.evaluate());
        }

        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(double number) implements Expr {
        public Negate(Expr number) {
            this(number.evaluate());
        }

        @Override
        public double evaluate() {
            return -number;
        }
    }

    record Exponent(double number, double power) implements Expr {
        public Exponent(Expr number, Expr power) {
            this(number.evaluate(), power.evaluate());
        }

        public Exponent(Expr number, double power) {
            this(number.evaluate(), power);
        }

        public Exponent(double number, Expr power) {
            this(number, power.evaluate());
        }

        @Override
        public double evaluate() {
            return Math.pow(number, power);
        }
    }

    record Addition(double firstNumber, double secondNumber) implements Expr {
        public Addition(Expr firstNumber, Expr secondNumber) {
            this(firstNumber.evaluate(), secondNumber.evaluate());
        }

        public Addition(Expr firstNumber, double secondNumber) {
            this(firstNumber.evaluate(), secondNumber);
        }

        public Addition(double firstNumber, Expr secondNumber) {
            this(firstNumber, secondNumber.evaluate());
        }

        @Override
        public double evaluate() {
            return firstNumber + secondNumber;
        }
    }

    record Multiplication(double firstNumber, double secondNumber) implements Expr {
        public Multiplication(Expr firstNumber, Expr secondNumber) {
            this(firstNumber.evaluate(), secondNumber.evaluate());
        }

        public Multiplication(Expr firstNumber, double secondNumber) {
            this(firstNumber.evaluate(), secondNumber);
        }

        public Multiplication(double firstNumber, Expr secondNumber) {
            this(firstNumber, secondNumber.evaluate());
        }

        @Override
        public double evaluate() {
            return firstNumber * secondNumber;
        }
    }
}

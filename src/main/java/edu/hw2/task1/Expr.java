package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        @Override
        public String toString() {
            return String.valueOf(number);
        }

        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expr number) implements Expr {

        @Override
        public String toString() {
            return "-(" + number.evaluate() + ")";
        }

        @Override
        public double evaluate() {
            return -number.evaluate();
        }
    }

    record Exponent(Expr number, double power) implements Expr {

        @Override
        public String toString() {
            return "(" + number.evaluate() + ")" + " ^ " + power;
        }

        @Override
        public double evaluate() {
            return Math.pow(number.evaluate(), power);
        }
    }

    record Addition(Expr firstNumber, Expr secondNumber) implements Expr {
        @Override
        public String toString() {
            return "(" + firstNumber.evaluate() + " + " + secondNumber.evaluate() + ")";
        }

        @Override
        public double evaluate() {
            return firstNumber.evaluate() + secondNumber.evaluate();
        }
    }

    record Multiplication(Expr firstNumber, Expr secondNumber) implements Expr {
        @Override
        public String toString() {
            return "(" + firstNumber.evaluate() + " * " + secondNumber.evaluate() + ")";
        }

        @Override
        public double evaluate() {
            return firstNumber.evaluate() * secondNumber.evaluate();
        }
    }
}

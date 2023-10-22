package edu.hw2.task2;

public class Square extends Rectangle {

    public Square() {
    }

    public Square(Rectangle rectangle) {
        this();
    }

    public void setSide(int side) {
        super.setWidth(side);
        super.setHeight(side);
    }
}

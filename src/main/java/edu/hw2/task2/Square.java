package edu.hw2.task2;

public class Square extends Rectangle {
    private int side = 0;
    private boolean isHeightDefined = false;
    private boolean isWidthDefined = false;

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        side = width;
        isWidthDefined = true;
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        side = height;
        isHeightDefined = true;
    }

    @Override
    public double area() {
        if (isHeightDefined && isWidthDefined) {
            return super.area();
        }
        return side * side;
    }
}

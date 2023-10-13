package edu.hw2.task2;

class Rectangle {
    private int width;
    private int height;

    void setWidth(int width) {
        this.width = width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    double area() {
        return width * height;
    }
}

class Square extends Rectangle {
    private int side;
    private boolean isHeightDefined = false;
    private boolean isWidthDefined = false;

    @Override
    void setWidth(int width) {
        super.setWidth(width);
        side = width;
        isWidthDefined = true;
    }

    @Override
    void setHeight(int height) {
        super.setHeight(height);
        side = height;
        isHeightDefined = true;
    }

    @Override
    double area() {
        if(isHeightDefined && isWidthDefined){
            return super.area();
        }else{
            return side*side;
        }
    }
}

package com.unicornkit.example.cleancode;

class Rectangle extends AbstractShape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double calculateArea() {
        return getWidth() * getHeight();
    }
}

package com.unicornkit.example.part1_java_polymorph_objects;

class Rectangle extends AbstractShape {
    protected double width;
    protected double height;

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

    public void setWidth(double value) {
        width = value;
    }

    public void setHeight(double value) {
        height = value;
    }

    @Override
    public double calculateArea() {
        return getWidth() * getHeight();
    }
}

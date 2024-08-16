package com.unicornkit.example.part1_java_polymorph_objects;

class Triangle extends AbstractShape {
    protected double base;
    protected double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public void setBase(double value) {
        base = value;
    }

    public void setHeight(double value) {
        height = value;
    }

    @Override
    public double calculateArea() {
        return 0.5 * getBase() * getHeight();
    }
}

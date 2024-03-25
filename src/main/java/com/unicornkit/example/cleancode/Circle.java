package com.unicornkit.example.cleancode;

class Circle extends AbstractShape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return getRadius() * 2 * Math.PI;
    }
}

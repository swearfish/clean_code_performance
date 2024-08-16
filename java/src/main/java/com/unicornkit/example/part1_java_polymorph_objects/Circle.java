package com.unicornkit.example.part1_java_polymorph_objects;

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
        return getRadius() * getRadius() * Math.PI;
    }
}

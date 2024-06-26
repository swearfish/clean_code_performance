package com.unicornkit.example.part6_pojo;

import com.unicornkit.example.Shape;

final class ShapeObject implements Shape {

    public static final int RECTANGLE = 0;

    public static final int CIRCLE = 1;

    public static final int TRIANGLE = 2;

    static final double coeff[] = {1.0, Math.PI, 0.5};

    final double width;

    final double height;

    final int type;

    ShapeObject(double width, double height, int type) {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public static ShapeObject rectangle(double width, double height) {
        return new ShapeObject(width, height, RECTANGLE);
    }

    public static ShapeObject square(double size) {
        return rectangle(size, size);
    }

    public static ShapeObject circle(double radius) {
        return new ShapeObject(radius, radius, CIRCLE);
    }

    public static ShapeObject triangle(double base, double height) {
        return new ShapeObject(base, height, TRIANGLE);
    }

    @Override
    public double calculateArea() {
        return switch (type) {
            case RECTANGLE -> width * height;
            case CIRCLE -> width * width * Math.PI;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}

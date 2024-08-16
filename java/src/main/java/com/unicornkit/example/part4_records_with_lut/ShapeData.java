package com.unicornkit.example.part4_records_with_lut;

import com.unicornkit.example.Shape;

final class ShapeData implements Shape {

    private static final int RECTANGLE = 0;

    private static final int CIRCLE = 1;

    private static final int TRIANGLE = 2;

    private static final double[] coeff = {1.0, Math.PI, 0.5f};

    private final double width;

    private final double height;

    private final int type;

    private ShapeData(double width, double height, int type) {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public static ShapeData rectangle(double width, double height) {
        return new ShapeData(width, height, RECTANGLE);
    }

    public static ShapeData square(double size) {
        return rectangle(size, size);
    }

    public static ShapeData circle(double radius) {
        return new ShapeData(radius, radius, CIRCLE);
    }

    public static ShapeData triangle(double base, double height) {
        return new ShapeData(base, height, TRIANGLE);
    }

    @Override
    public double calculateArea() {
        return width * coeff[type] * height;
    }
}

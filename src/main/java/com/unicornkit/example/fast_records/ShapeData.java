package com.unicornkit.example.fast_records;

import com.unicornkit.example.Shape;

final class ShapeData implements Shape {

    private static final int RECTANGLE = 0;

    private static final int CIRCLE = 1;

    private static final double[] widthCoeff = {1.0, 2 * Math.PI};

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

    public static ShapeData square(double width) {
        return rectangle(width, width);
    }

    public static ShapeData circle(double radius) {
        return new ShapeData(radius, 1.0, CIRCLE);
    }

    @Override
    public double calculateArea() {
        return width * widthCoeff[type] * height;
    }
}

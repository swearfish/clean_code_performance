package com.unicornkit.example.part3_multipurpose_record;

import com.unicornkit.example.Shape;

final class ShapeData implements Shape {

    private enum Type {
        RECTANGLE,
        CIRCLE
    }

    private final double width;

    private final double height;

    private final Type type;

    private ShapeData(double width, double height, Type type) {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public static ShapeData rectangle(double width, double height) {
        return new ShapeData(width, height, Type.RECTANGLE);
    }

    public static ShapeData square(double width) {
        return rectangle(width, width);
    }

    public static ShapeData circle(double radius) {
        return new ShapeData(radius, 1.0, Type.CIRCLE);
    }

    @Override
    public double calculateArea() {
        return switch (type) {
            case RECTANGLE -> width * height;
            case CIRCLE -> width * width * Math.PI;
        };
    }
}

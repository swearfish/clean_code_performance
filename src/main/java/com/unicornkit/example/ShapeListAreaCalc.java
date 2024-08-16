package com.unicornkit.example;

import com.unicornkit.example.part3_multipurpose_record.ShapeData;

import java.util.stream.StreamSupport;

public final class ShapeListAreaCalc {

    private ShapeListAreaCalc() {}

    public static double calcUsingStream(Iterable<Shape> shapes, boolean parallel) {
        return StreamSupport.stream(shapes.spliterator(), parallel).mapToDouble(Shape::calculateArea).sum();
    }

    public static double calcUsingForEach(Iterable<Shape> shapes) {
        var ref = new Object() {
            double result = 0;
        };
        shapes.forEach(s -> ref.result += s.calculateArea());
        return ref.result;
    }

    public static double calcUsingForEach_ShapeData(Iterable<ShapeData> shapes) {
        var ref = new Object() {
            double result = 0;
        };
        shapes.forEach(s -> ref.result += s.calculateArea());
        return ref.result;
    }

    public static double calcUsingForEachLoop(Iterable<Shape> shapes) {
        double result = 0;
        for (var shape: shapes) {
            result += shape.calculateArea();
        }
        return result;
    }
}

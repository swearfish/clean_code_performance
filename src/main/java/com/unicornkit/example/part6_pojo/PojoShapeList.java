package com.unicornkit.example.part6_pojo;

import com.unicornkit.example.ShapeListBuilder;

import java.util.ArrayList;

public final class PojoShapeList implements ShapeListBuilder {

    final ArrayList<ShapeObject> shapes = new ArrayList<>();

    @Override
    public void addRectangle(double width, double height) {
        shapes.add(ShapeObject.rectangle(width, height));
    }

    @Override
    public void addSquare(double size) {
        shapes.add(ShapeObject.square(size));
    }

    @Override
    public void addCircle(double radius) {
        shapes.add(ShapeObject.circle(radius));
    }

    @Override
    public void addTriangle(double base, double height) {
        shapes.add(ShapeObject.triangle(base, height));
    }

    public double calcTotalArea() {
        final double[] result = {0};
        shapes.forEach(s -> {
            if (s.type == ShapeObject.CIRCLE) {
                result[0] += s.width * s.width * Math.PI;
            } else if (s.type == ShapeObject.TRIANGLE) {
                result[0] += s.width * s.height * 0.5;
            } else {
                result[0] += s.width * s.height;
            }
        });
        return result[0];
    }

    public double calcTotalAreaFast() {
        final double[] result = {0};
        shapes.forEach(s -> {
            result[0] += s.width * s.height * ShapeObject.coeff[s.type];
        });
        return result[0];
    }
}

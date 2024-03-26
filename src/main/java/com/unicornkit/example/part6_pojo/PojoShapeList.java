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

    public double calcTotalArea() {
        final double[] result = {0};
        shapes.forEach(s -> {
            if (s.type == ShapeObject.CIRCLE) {
                result[0] += s.width * 2 * Math.PI;
            } else {
                result[0] += s.width * s.height;
            }
        });
        return result[0];
    }
}

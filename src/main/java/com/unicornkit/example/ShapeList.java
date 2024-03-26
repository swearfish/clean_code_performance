package com.unicornkit.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ShapeList implements Iterable<Shape>, ShapeListBuilder {

    final List<Shape> shapes = new ArrayList<>();

    private final ShapeFactory factory;

    public ShapeList(ShapeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addRectangle(double width, double height) {
        shapes.add(factory.createRectangle(width, height));
    }

    @Override
    public void addSquare(double size) {
        shapes.add(factory.createSquare(size));
    }

    @Override
    public void addCircle(double radius) {
        shapes.add(factory.createCircle(radius));
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }
}

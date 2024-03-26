package com.unicornkit.example.part1_java_polymorph_objects;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;
import com.unicornkit.example.ShapeList;

public class PolymorphShapeFactory implements ShapeFactory {
    public static ShapeList createList() {
        return new ShapeList(new PolymorphShapeFactory());
    }

    @Override
    public Shape createRectangle(double width, double height) {
        return new Rectangle(width, height);
    }

    @Override
    public Shape createSquare(double size) {
        return new Square(size);
    }

    @Override
    public Shape createCircle(double radius) {
        return new Circle(radius);
    }
}

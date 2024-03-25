package com.unicornkit.example.cleancode;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;

public class CleanCodeShapeFactory implements ShapeFactory {
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

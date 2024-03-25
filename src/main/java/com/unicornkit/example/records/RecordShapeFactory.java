package com.unicornkit.example.records;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;

public class RecordShapeFactory implements ShapeFactory {
    @Override
    public Shape createRectangle(double width, double height) {
        return ShapeData.rectangle(width, height);
    }

    @Override
    public Shape createSquare(double size) {
        return ShapeData.square(size);
    }

    @Override
    public Shape createCircle(double radius) {
        return ShapeData.circle(radius);
    }
}

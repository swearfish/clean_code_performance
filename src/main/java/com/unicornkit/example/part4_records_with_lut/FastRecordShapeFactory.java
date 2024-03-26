package com.unicornkit.example.part4_records_with_lut;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;
import com.unicornkit.example.ShapeList;

public class FastRecordShapeFactory implements ShapeFactory {
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

    public static ShapeList createList() {
        return new ShapeList(new FastRecordShapeFactory());
    }
}

package com.unicornkit.example.part3_multipurpose_record;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;
import com.unicornkit.example.ShapeList;

public class MultipurposeRecordShapeFactory implements ShapeFactory {
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
        return new ShapeList(new MultipurposeRecordShapeFactory());
    }
}

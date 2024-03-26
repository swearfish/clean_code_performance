package com.unicornkit.example.part2_polymorph_records;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;
import com.unicornkit.example.ShapeList;

public class PolymorphRecordShapeFactory implements ShapeFactory {
    @Override
    public Shape createRectangle(double width, double height) {
        return new Rectangle(width, height);
    }

    @Override
    public Shape createSquare(double size) {
        return new Rectangle(size, size);
    }

    @Override
    public Shape createCircle(double radius) {
        return new Circle(radius);
    }

    public static ShapeList createList() {
        return new ShapeList(new PolymorphRecordShapeFactory());
    }
}

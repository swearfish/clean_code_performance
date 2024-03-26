package com.unicornkit.example.part6_pojo;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;
import com.unicornkit.example.ShapeList;

public class PojoShapeFactory implements ShapeFactory {
    @Override
    public Shape createRectangle(double width, double height) {
        return ShapeObject.rectangle(width, height);
    }

    @Override
    public Shape createSquare(double size) {
        return ShapeObject.square(size);
    }

    @Override
    public Shape createCircle(double radius) {
        return ShapeObject.circle(radius);
    }

    @Override
    public Shape createTriangle(double base, double height) {
        return ShapeObject.triangle(base, height);
    }

    public static ShapeList createList() {
        return new ShapeList(new PojoShapeFactory());
    }
}

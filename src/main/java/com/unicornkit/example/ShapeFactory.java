package com.unicornkit.example;

public interface ShapeFactory {
    Shape createRectangle(double width, double height);
    Shape createSquare(double size);
    Shape createCircle(double radius);
    Shape createTriangle(double base, double height);
}

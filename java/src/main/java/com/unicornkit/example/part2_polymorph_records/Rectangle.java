package com.unicornkit.example.part2_polymorph_records;

import com.unicornkit.example.Shape;

record Rectangle(double width, double height) implements Shape {

    @Override
    public double calculateArea() {
        return width * height;
    }
}

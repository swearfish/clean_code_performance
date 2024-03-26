package com.unicornkit.example.part2_polymorph_records;

import com.unicornkit.example.Shape;

record Circle(double radius) implements Shape {

    @Override
    public double calculateArea() {
        return radius * radius * Math.PI;
    }
}

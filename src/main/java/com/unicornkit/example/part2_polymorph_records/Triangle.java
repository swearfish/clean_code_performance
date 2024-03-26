package com.unicornkit.example.part2_polymorph_records;

import com.unicornkit.example.Shape;

record Triangle(double base, double height) implements Shape {

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

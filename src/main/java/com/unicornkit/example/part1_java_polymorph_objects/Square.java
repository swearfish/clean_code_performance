package com.unicornkit.example.part1_java_polymorph_objects;

class Square extends Rectangle {

    public Square(double size) {
        super(size, size);
    }

    @Override
    public void setWidth(double value) {
        width = height = value;
    }

    @Override
    public void setHeight(double value) {
        width = height = value;
    }
}

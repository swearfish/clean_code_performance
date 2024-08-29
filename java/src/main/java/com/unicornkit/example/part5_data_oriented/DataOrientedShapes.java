package com.unicornkit.example.part5_data_oriented;

import com.unicornkit.example.ShapeListBuilder;

public class DataOrientedShapes implements ShapeListBuilder {

    private enum Type {
        RECTANGLE,
        CIRCLE,
        TRIANGLE,
    }

    private double[] attributeBuffer = null;
    private Type[] typeBuffer = null;
    private int count = 0;

    private void resizeAttributeBuffer(int newLength) {
        if (attributeBuffer == null) {
            attributeBuffer = new double[newLength*2];
            return;
        }
        if (newLength <= attributeBuffer.length) return;
        var newBuffer = new double[newLength * 2];
        System.arraycopy(attributeBuffer, 0, newBuffer, 0, attributeBuffer.length);
        attributeBuffer = newBuffer;
    }

    private void resizeTypeBuffer(int newLength) {
        if (typeBuffer == null) {
            typeBuffer = new Type[newLength*2];
            return;
        }
        if (newLength <= typeBuffer.length) return;
        var newBuffer = new Type[newLength * 2];
        System.arraycopy(typeBuffer, 0, newBuffer, 0, typeBuffer.length);
        typeBuffer = newBuffer;
    }

    private void resize(int newLength) {
        resizeAttributeBuffer(newLength*2);
        resizeTypeBuffer(newLength);
    }

    private void shape(Type type, double width, double height) {
        resize(count + 1);
        var attributeOffset = count * 2;
        var typeOffset = count;
        count += 1;
        attributeBuffer[attributeOffset] = width;
        attributeBuffer[attributeOffset + 1] = height;
        typeBuffer[typeOffset] = type;
    }

    @Override
    public void addRectangle(double width, double height) {
        shape(Type.RECTANGLE, width, height);
    }

    @Override
    public void addSquare(double size) {
        shape(Type.RECTANGLE, size, size);
    }

    @Override
    public void addCircle(double radius) {
        shape(Type.CIRCLE, radius, radius);
    }

    @Override
    public void addTriangle(double base, double height) {
        shape(Type.TRIANGLE, base, height);
    }

    public double calcTotalArea() {
        double result = 0;
        int attributeOffset = 0;
        for (int i=0; i<count; i++) {
            var w = attributeBuffer[attributeOffset];
            var h = attributeBuffer[attributeOffset+1];
            var t = typeBuffer[i];
            attributeOffset += 2;
            var area = switch (t) {
                case RECTANGLE -> w * h;
                case CIRCLE -> w * w * Math.PI;
                case TRIANGLE -> w * h * 0.5;
            };
            result += area;
        }
        return result;
    }

}

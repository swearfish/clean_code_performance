package com.unicornkit.example.data_oriented;

import com.unicornkit.example.Shape;
import com.unicornkit.example.ShapeFactory;

import java.util.List;

public class DataOrientedShapes {

    private double[] buffer = null;
    private int count = 0;

    private void resize(int newLength) {
        if (buffer == null) {
            buffer = new double[newLength];
        }
        if (newLength < buffer.length) return;
        double[] newBuffer = new double[newLength * 2];
        System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
        buffer = newBuffer;
    }

    private void shape(double width, double height, double widthCoeff) {
        resize(3 * (count + 1));
        var offset = count * 3;
        count += 1;
        buffer[offset] = width;
        buffer[offset + 1] = height;
        buffer[offset + 2] = widthCoeff;
    }

    public void addRectangle(double width, double height) {
        shape(width, height, 1.0);
    }

    public void addSquare(double size) {
        shape(size, size, 1.0);
    }

    public void addCircle(double radius) {
        shape(radius, 1.0, 2 * Math.PI);
    }

    public double calcTotalArea() {
        double result = 0;
        int offset = 0;
        for (int i=0; i<count; i++) {
            var area = buffer[offset] * buffer[offset+1] * buffer[offset+2];
            offset += 3;
            result += area;
        }
        return result;
    }

    public double calcTotalAreaUnroll() {
        double result = 0;
        int offset = 0;
        int countPer4 = count / 4;
        int countMod4 = count % 4;
        while (countPer4 > 0) {
            var area = buffer[offset] * buffer[offset+1] * buffer[offset+2];
            offset += 3;
            result += area;
            area = buffer[offset] * buffer[offset+1] * buffer[offset+2];
            offset += 3;
            result += area;
            area = buffer[offset] * buffer[offset+1] * buffer[offset+2];
            offset += 3;
            result += area;
            area = buffer[offset] * buffer[offset+1] * buffer[offset+2];
            offset += 3;
            result += area;
            countPer4--;
        }
        while (countMod4 > 0) {
            var area = buffer[offset] * buffer[offset + 1] * buffer[offset + 2];
            offset += 3;
            result += area;
            countMod4--;
        }
        return result;
    }

    public ShapeFactory createFactory() {
        return new Factory();
    }

    private final class Factory implements ShapeFactory {

        @Override
        public Shape createRectangle(double width, double height) {
            addRectangle(width, height);
            return null;
        }

        @Override
        public Shape createSquare(double size) {
            addSquare(size);
            return null;
        }

        @Override
        public Shape createCircle(double radius) {
            addCircle(radius);
            return null;
        }
    }
}

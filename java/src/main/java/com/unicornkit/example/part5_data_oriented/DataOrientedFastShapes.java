package com.unicornkit.example.part5_data_oriented;

import com.unicornkit.example.ShapeListBuilder;

public class DataOrientedFastShapes implements ShapeListBuilder {

    private double[] buffer = null;
    private int count = 0;

    private void resize(int newLength) {
        if (buffer == null) {
            buffer = new double[newLength*2];
            return;
        }
        if (newLength <= buffer.length) return;
        double[] newBuffer = new double[newLength * 2];
        System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
        buffer = newBuffer;
    }

    private void shape(double width, double height, double coeff) {
        resize(3 * (count + 1));
        var offset = count * 3;
        count += 1;
        buffer[offset] = width;
        buffer[offset + 1] = height;
        buffer[offset + 2] = coeff;
    }

    @Override
    public void addRectangle(double width, double height) {
        shape(width, height, 1.0);
    }

    @Override
    public void addSquare(double size) {
        shape(size, size, 1.0);
    }

    @Override
    public void addCircle(double radius) {
        shape(radius, radius, Math.PI);
    }

    @Override
    public void addTriangle(double base, double height) {
        shape(base, height, 0.5);
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
}

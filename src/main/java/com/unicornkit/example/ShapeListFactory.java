package com.unicornkit.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ShapeListFactory {

    private ShapeListFactory() {}

    public static List<Shape> fromFile(String filename, ShapeFactory factory)
            throws IOException {
        try (var br = new BufferedReader(new FileReader(filename))) {
            return fromReader(br, factory);
        }
    }

    public static List<Shape> fromResource(String resourceName, ShapeFactory factory)
            throws IOException {
        try (var br = Utility.openResource(resourceName)) {
            return fromReader(br, factory);
        }
    }

    public static List<Shape> fromReader(BufferedReader br, ShapeFactory factory)
            throws IOException {
        var shapeList = new ArrayList<Shape>();
        String line;
        while ((line = br.readLine()) != null) {
            var shape = parse(line, factory);
            shapeList.add(shape);
        }

        return shapeList;
    }

    private static Shape parse(String line, ShapeFactory factory) throws IllegalArgumentException {
        String[] parts = line.split("\\s+");
        return parse(parts, factory);
    }

    private static Shape parse(String[] parts, ShapeFactory factory) throws IllegalArgumentException {
        switch (parts[0]) {
            case "R" -> {
                double width = Double.parseDouble(parts[1]);
                double height = Double.parseDouble(parts[2]);
                return factory.createRectangle(width, height);
            }
            case "C" -> {
                double radius = Double.parseDouble(parts[1]);
                return factory.createCircle(radius);
            }
            case "S" -> {
                double size = Double.parseDouble(parts[1]);
                return factory.createSquare(size);
            }
            default -> throw new IllegalArgumentException("Unknown shape type: " + parts[0]);
        }
    }
}
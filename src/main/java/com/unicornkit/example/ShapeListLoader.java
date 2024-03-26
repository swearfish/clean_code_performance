package com.unicornkit.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class ShapeListLoader {

    private ShapeListLoader() {}

    public static void fromFile(String filename, ShapeListBuilder list)
            throws IOException {
        try (var br = new BufferedReader(new FileReader(filename))) {
            fromReader(br, list);
        }
    }

    public static void fromResource(String resourceName, ShapeListBuilder list)
            throws IOException {
        try (var br = Utility.openResource(resourceName)) {
            fromReader(br, list);
        }
    }

    public static void fromReader(BufferedReader br, ShapeListBuilder list)
            throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            parse(line, list);
        }
    }

    private static void parse(String line, ShapeListBuilder list) throws IllegalArgumentException {
        String[] parts = line.split("\\s+");
        parse(parts, list);
    }

    private static void parse(String[] parts, ShapeListBuilder list) throws IllegalArgumentException {
        switch (parts[0]) {
            case "R" -> {
                double width = Double.parseDouble(parts[1]);
                double height = Double.parseDouble(parts[2]);
                list.addRectangle(width, height);
            }
            case "C" -> {
                double radius = Double.parseDouble(parts[1]);
                list.addCircle(radius);
            }
            case "S" -> {
                double size = Double.parseDouble(parts[1]);
                list.addSquare(size);
            }
            default -> throw new IllegalArgumentException("Unknown shape type: " + parts[0]);
        }
    }
}
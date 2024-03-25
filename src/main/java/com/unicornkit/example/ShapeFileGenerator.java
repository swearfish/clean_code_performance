package com.unicornkit.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public final class ShapeFileGenerator {

    public static void main(String[] args) {
        try {
            generate(Paths.get("jmh", "resources", "shapes.txt"),
                    10000,
                    50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generate(Path fileName, int count, int maxSize) throws IOException {
        var content = generate(count, maxSize);
        Files.write(fileName, content.getBytes());
    }

    public static String generate(int count, int maxSize) {
        var rnd = new Random();
        var sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            var shapeType = rnd.nextInt(3);
            var w = 1 + rnd.nextInt(maxSize);
            var h = 1 + rnd.nextInt(maxSize);
            switch (shapeType) {
                case 0 -> sb.append("S ").append(w).append("\n");
                case 1 -> sb.append("C ").append(w).append("\n");
                case 2 -> sb.append("R ").append(w).append(" ").append(h).append("\n");
            }
        }

        return sb.toString();
    }
}
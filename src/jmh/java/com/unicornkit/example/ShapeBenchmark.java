package com.unicornkit.example;

import com.unicornkit.example.cleancode.CleanCodeShapeFactory;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;

public class ShapeBenchmark {

    @Benchmark
    public void cleanCode() {
        try {
            var shapes = ShapeListFactory.fromResource("shapes.txt", new CleanCodeShapeFactory());
            var totalArea = shapes.stream().mapToDouble(Shape::calculateArea).sum();
//            System.out.println(totalArea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

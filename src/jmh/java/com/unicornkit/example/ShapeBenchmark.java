package com.unicornkit.example;

import com.unicornkit.example.data_oriented.DataOrientedShapes;
import com.unicornkit.example.fast_records.FastRecordShapeFactory;
import com.unicornkit.example.polymorph.PolymorphShapeFactory;
import com.unicornkit.example.records.RecordShapeFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;
import java.util.List;



public class ShapeBenchmark {

    private static final List<Shape> polyShapes;
    private static final List<Shape> records;

    private static final List<Shape> fastRecords;

    private static final DataOrientedShapes dataOrientedShapes = new DataOrientedShapes();

    static {
        try {
            polyShapes = ShapeListFactory.fromResource("shapes.txt", new PolymorphShapeFactory());
            records = ShapeListFactory.fromResource("shapes.txt", new RecordShapeFactory());
            fastRecords = ShapeListFactory.fromResource("shapes.txt", new FastRecordShapeFactory());
            ShapeListFactory.fromResource("shapes.txt", dataOrientedShapes.createFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void cleanCode() {
        var totalArea = polyShapes.stream().mapToDouble(Shape::calculateArea).sum();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void records() {
        var totalArea = records.stream().mapToDouble(Shape::calculateArea).sum();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void recordsWithoutStream() {
        var totalArea = 0.0;
        for (var s: records) {
            totalArea += s.calculateArea();
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void fastRecords() {
        var totalArea = 0.0;
        for (var s: fastRecords) {
            totalArea += s.calculateArea();
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void dataOriented() {
        var totalArea = dataOrientedShapes.calcTotalArea();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void dataOrientedUnroll() {
        var totalArea = dataOrientedShapes.calcTotalAreaUnroll();
    }
}

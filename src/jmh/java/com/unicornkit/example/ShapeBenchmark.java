package com.unicornkit.example;

import com.unicornkit.example.part1_java_polymorph_objects.PolymorphShapeFactory;
import com.unicornkit.example.part2_polymorph_records.PolymorphRecordShapeFactory;
import com.unicornkit.example.part3_multipurpose_record.MultipurposeRecordShapeFactory;
import com.unicornkit.example.part4_records_with_lut.FastRecordShapeFactory;
import com.unicornkit.example.part5_data_oriented.DataOrientedShapes;
import com.unicornkit.example.part6_pojo.PojoShapeList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;


@SuppressWarnings("unused")
public class ShapeBenchmark {

    private static final ShapeList polyShapes = new ShapeList(new PolymorphShapeFactory());

    private static final ShapeList records = PolymorphRecordShapeFactory.createList();

    private static final ShapeList multipurposeRecords = MultipurposeRecordShapeFactory.createList();

    private static final ShapeList fastRecords = FastRecordShapeFactory.createList();

    private static final DataOrientedShapes dataOrientedShapes = new DataOrientedShapes();

    private static final PojoShapeList pojoShapes = new PojoShapeList();

    static {
        try {
            ShapeListLoader.fromResource("shapes.txt", polyShapes);
            ShapeListLoader.fromResource("shapes.txt", records);
            ShapeListLoader.fromResource("shapes.txt", multipurposeRecords);
            ShapeListLoader.fromResource("shapes.txt", fastRecords);
            ShapeListLoader.fromResource("shapes.txt", dataOrientedShapes);
            ShapeListLoader.fromResource("shapes.txt", pojoShapes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part1a_polymorph_shapes_stream() {
        ShapeListAreaCalc.calcUsingStream(polyShapes, false);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part1b_polymorph_shapes_stream_parallel() {
        ShapeListAreaCalc.calcUsingStream(polyShapes, true);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part1c_polymorph_shapes_forEach() {
        ShapeListAreaCalc.calcUsingForEach(polyShapes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part1d_polymorph_shapes_for() {
        ShapeListAreaCalc.calcUsingForLoop(polyShapes);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part2_polymorph_records() {
        var totalArea = ShapeListAreaCalc.calcUsingForEach(records);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part3_multipurpose_records() {
        var totalArea = ShapeListAreaCalc.calcUsingForEach(multipurposeRecords);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part4_fast_records() {
        var totalArea = ShapeListAreaCalc.calcUsingForEach(fastRecords);
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part5a_data_oriented() {
        var totalArea = dataOrientedShapes.calcTotalArea();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part5b_data_oriented_loop_unrolling() {
        var totalArea = dataOrientedShapes.calcTotalAreaUnroll();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part6a_pojo() {
        var totalArea = pojoShapes.calcTotalArea();
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    public void part6b_pojo_ctab() {
        var totalArea = pojoShapes.calcTotalAreaFast();
    }
}

package com.unicornkit.example;

import com.unicornkit.example.part1_java_polymorph_objects.PolymorphShapeFactory;
import com.unicornkit.example.part2_polymorph_records.PolymorphRecordShapeFactory;
import com.unicornkit.example.part3_multipurpose_record.MultipurposeRecordShapeFactory;
import com.unicornkit.example.part4_records_with_lut.FastRecordShapeFactory;
import com.unicornkit.example.part6_pojo.PojoShapeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapesTest {
    private final double totalArea;

    ShapesTest() throws IOException {
        var polyShapes = PolymorphShapeFactory.createList();
        ShapeListLoader.fromResource("shapes.txt", polyShapes);
        totalArea = ShapeListAreaCalc.calcUsingForEachLoop(polyShapes);
    }

    @Test
    void testRecords() throws IOException {
        var recordShapes = PolymorphRecordShapeFactory.createList();
        ShapeListLoader.fromResource("shapes.txt", recordShapes);
        var totalArea = ShapeListAreaCalc.calcUsingForEach(recordShapes);
        assertEquals(this.totalArea, totalArea, 1/1000.0);
    }

    @Test
    void testMultipurposeRecords() throws IOException {
        var recordShapes = MultipurposeRecordShapeFactory.createList();
        ShapeListLoader.fromResource("shapes.txt", recordShapes);
        var totalArea = ShapeListAreaCalc.calcUsingForEach(recordShapes);
        assertEquals(this.totalArea, totalArea, 1/1000.0);
    }

    @Test
    void testFastRecords() throws IOException {
        var recordShapes = FastRecordShapeFactory.createList();
        ShapeListLoader.fromResource("shapes.txt", recordShapes);
        var totalArea = ShapeListAreaCalc.calcUsingForEach(recordShapes);
        assertEquals(this.totalArea, totalArea, 1/1000.0);
    }

    @Test
    void testPojo() throws IOException {
        var shapes = new PojoShapeList();
        ShapeListLoader.fromResource("shapes.txt", shapes);
        var totalArea = shapes.calcTotalArea();
        assertEquals(this.totalArea, totalArea, 1/1000.0);
    }

    @Test
    void testPojoFast() throws IOException {
        var shapes = new PojoShapeList();
        ShapeListLoader.fromResource("shapes.txt", shapes);
        var totalArea = shapes.calcTotalAreaFast();
        assertEquals(this.totalArea, totalArea, 1/1000.0);
    }
}

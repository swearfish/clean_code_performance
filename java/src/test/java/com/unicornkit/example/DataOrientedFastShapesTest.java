package com.unicornkit.example;

import com.unicornkit.example.part1_java_polymorph_objects.PolymorphShapeFactory;
import com.unicornkit.example.part5_data_oriented.DataOrientedFastShapes;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataOrientedFastShapesTest {

    private final double totalArea;

    private final DataOrientedFastShapes dataOrientedShapes = new DataOrientedFastShapes();
    private final ShapeList polyShapes;

    DataOrientedFastShapesTest() throws IOException {
        polyShapes = PolymorphShapeFactory.createList();
        ShapeListLoader.fromResource("shapes.txt", polyShapes);
        totalArea = ShapeListAreaCalc.calcUsingForEachLoop(polyShapes);
        ShapeListLoader.fromResource("shapes.txt", dataOrientedShapes);
    }

    @Test
    void calcTotalArea() {
        var totalArea = dataOrientedShapes.calcTotalArea();
        assertEquals(this.totalArea, totalArea, 1.0/1000);
    }

    @Test
    void calcTotalAreaUnroll() {
        var totalArea = dataOrientedShapes.calcTotalAreaUnroll();
        assertEquals(this.totalArea, totalArea, 1.0/1000);
    }
}
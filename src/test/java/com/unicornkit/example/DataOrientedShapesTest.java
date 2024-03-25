package com.unicornkit.example;

import com.unicornkit.example.data_oriented.DataOrientedShapes;
import com.unicornkit.example.polymorph.PolymorphShapeFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataOrientedShapesTest {

    private final double totalArea;

    private final DataOrientedShapes dataOrientedShapes = new DataOrientedShapes();

    DataOrientedShapesTest() throws IOException {
        var polyShapes = ShapeListFactory.fromResource("shapes.txt", new PolymorphShapeFactory());
        totalArea = polyShapes.stream().mapToDouble(Shape::calculateArea).sum();
        ShapeListFactory.fromResource("shapes.txt", dataOrientedShapes.createFactory());
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
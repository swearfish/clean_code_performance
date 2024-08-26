use crate::factory::{Shape, ShapeCollection};

// Define the Shape trait
// Implement Shape for Triangle
struct Triangle {
    base: f64,
    height: f64,
}

impl Shape for Triangle {
    fn area(&self) -> f64 {
        0.5 * self.base * self.height
    }
}

// Implement Shape for Circle
struct Circle {
    radius: f64,
}

impl Shape for Circle {
    fn area(&self) -> f64 {
        std::f64::consts::PI * self.radius * self.radius
    }
}

// Implement Shape for Rectangle
struct Rectangle {
    width: f64,
    height: f64,
}

impl Shape for Rectangle {
    fn area(&self) -> f64 {
        self.width * self.height
    }
}

// Implement Shape for Square
struct Square {
    side: f64,
}

impl Shape for Square {
    fn area(&self) -> f64 {
        self.side * self.side
    }
}

pub struct SolidShapeCollection {
    shapes: Vec<Box<dyn Shape>>,
}

impl SolidShapeCollection {
    pub fn new() -> Self {
        SolidShapeCollection { shapes: Vec::new() }
    }
}

impl ShapeCollection for SolidShapeCollection {
    fn add_rectangle(&mut self, width: f64, height: f64) {
        self.shapes.push(Box::new(Rectangle { width, height }));
    }

    fn add_square(&mut self, side: f64) {
        self.shapes.push(Box::new(Square { side }));
    }

    fn add_triangle(&mut self, base: f64, height: f64) {
        self.shapes.push(Box::new(Triangle { base, height }));
    }

    fn add_circle(&mut self, radius: f64) {
        self.shapes.push(Box::new(Circle { radius }));
    }

    fn get_total_area(&self) -> f64 {
        let mut total_area = 0.0;
        for shape in &self.shapes {
            total_area += shape.area();
        }
        total_area
    }
}
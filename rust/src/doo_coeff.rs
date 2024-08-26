use crate::doo::{ShapeDataCtor, ShapeType, ShapeDataCollection, PI, HALF};
use crate::factory::Shape;

pub struct ShapeDataCoeff {
    shape_type: ShapeType,
    width: f64,
    height: f64,
    coefficient: f64,
}

impl ShapeDataCtor for ShapeDataCoeff {
    fn new(shape_type: ShapeType, width: f64, height: f64) -> Self {
        let coefficient = match shape_type {
            ShapeType::Circle => PI,
            ShapeType::Rectangle => 1.0,
            ShapeType::Square => 1.0,
            ShapeType::Triangle => HALF,
        };
        ShapeDataCoeff { shape_type, width, height, coefficient }
    }
}

impl Shape for ShapeDataCoeff {
    fn area(&self) -> f64 {
        self.coefficient * self.width * self.height
    }
}

pub fn calc_area_fast(shape_collection: &ShapeDataCollection<ShapeDataCoeff>) -> f64 {
    let mut total_area = 0.0;
    for shape in &shape_collection.shapes {
        total_area += shape.width * shape.height * shape.coefficient;
    }
    total_area
}
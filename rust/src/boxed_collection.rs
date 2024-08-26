use crate::doo::{ShapeDataCtor, ShapeType};
use crate::factory::{Shape, ShapeCollection};

pub struct ShapeDataBoxedCollection<T: Shape + ShapeDataCtor> {
    pub shapes: Vec<Box<T>>,
}

impl<T: Shape + ShapeDataCtor> ShapeDataBoxedCollection<T> {
    pub fn new() -> Self {
        ShapeDataBoxedCollection { shapes: Vec::new() }
    }

    fn add_shape(&mut self, shape: T) {
        self.shapes.push(Box::new(shape));
    }

    pub fn get_total_area(&self) -> f64 {
        self.shapes.iter().map(|shape| shape.area()).sum()
    }
}

impl<T: Shape + ShapeDataCtor> ShapeCollection for ShapeDataBoxedCollection<T> {
    fn add_rectangle(&mut self, width: f64, height: f64) {
        self.add_shape(T::new(ShapeType::Rectangle, width, height));
    }

    fn add_square(&mut self, side: f64) {
        self.add_shape(T::new(ShapeType::Square, side, side));
    }

    fn add_triangle(&mut self, base: f64, height: f64) {
        self.add_shape(T::new(ShapeType::Triangle, base, height));
    }

    fn add_circle(&mut self, radius: f64) {
        self.add_shape(T::new(ShapeType::Circle, radius, radius));
    }

    fn get_total_area(&self) -> f64 {
        self.get_total_area()
    }
}
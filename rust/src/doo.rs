use crate::factory::{Shape, ShapeCollection};

pub enum ShapeType {
    Circle,
    Rectangle,
    Square,
    Triangle,
}

pub const PI: f64 = std::f64::consts::PI;
pub const HALF: f64 = 0.5;

pub trait ShapeDataCtor {
    fn new(shape_type: ShapeType, width: f64, height: f64) -> Self;
}

pub struct ShapeData {
    shape_type: ShapeType,
    width: f64,
    height: f64,
}

impl ShapeDataCtor for ShapeData {
    fn new(shape_type: ShapeType, width: f64, height: f64) -> Self {
        ShapeData { shape_type, width, height }
    }
}

impl Shape for ShapeData {
    fn area(&self) -> f64 {
        match self.shape_type {
            ShapeType::Circle => PI * self.width * self.width,
            ShapeType::Rectangle => self.width * self.height,
            ShapeType::Square => self.width * self.width,
            ShapeType::Triangle => HALF * self.width * self.height,
        }
    }
}

pub struct ShapeDataCollection<T: Shape + ShapeDataCtor> {
    pub shapes: Vec<T>,
}

impl<T: Shape + ShapeDataCtor> ShapeDataCollection<T> {
    pub fn new() -> Self {
        ShapeDataCollection { shapes: Vec::new() }
    }

    fn add_shape(&mut self, shape: T) {
        self.shapes.push(shape);
    }

    pub fn get_total_area(&self) -> f64 {
        self.shapes.iter().map(|shape| shape.area()).sum()
    }
}

impl<T: Shape + ShapeDataCtor> ShapeCollection for ShapeDataCollection<T> {
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
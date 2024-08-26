use std::fs::File;
use std::io;
use std::io::BufRead;
use std::path::Path;

pub trait Shape {
    fn area(&self) -> f64;
}

pub trait ShapeCollection {
    fn add_rectangle(&mut self, width: f64, height: f64);
    fn add_square(&mut self, side: f64);
    fn add_triangle(&mut self, base: f64, height: f64);
    fn add_circle(&mut self, radius: f64);

    fn get_total_area(&self) -> f64;
}

pub fn read_shapes_from_file<P: AsRef<Path>>(file_path: P, shape_collection: &mut impl ShapeCollection) -> Result<(), io::Error> {
    let file = File::open(file_path)?;
    let reader = io::BufReader::new(file);

    for line in reader.lines() {
        let line = line?;
        let parts: Vec<&str> = line.split_whitespace().collect();
        match parts[0] {
            "R" => {
                let width: f64 = parts[1].parse().unwrap();
                let height: f64 = parts[2].parse().unwrap();
                shape_collection.add_rectangle(width, height);
            }
            "S" => {
                let side: f64 = parts[1].parse().unwrap();
                shape_collection.add_square(side);
            }
            "T" => {
                let base: f64 = parts[1].parse().unwrap();
                let height: f64 = parts[2].parse().unwrap();
                shape_collection.add_triangle(base, height);
            }
            "C" => {
                let radius: f64 = parts[1].parse().unwrap();
                shape_collection.add_circle(radius);
            }
            _ => {}
        }
    }
    Ok(())
}
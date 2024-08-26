mod solid;
mod boxed_collection;
mod factory;
mod doo;
mod doo_coeff;
mod assert_eq_eps;

use factory::read_shapes_from_file;
use crate::doo::{ShapeData, ShapeDataCollection};
use crate::factory::ShapeCollection;

fn main() {
    let mut solid_shapes = solid::SolidShapeCollection::new();
    let mut doo_shapes: ShapeDataCollection<ShapeData> = ShapeDataCollection::new();
    let mut doo_coeff_shapes: ShapeDataCollection<doo_coeff::ShapeDataCoeff> = ShapeDataCollection::new();
    read_shapes_from_file("shapes.txt", &mut solid_shapes).expect("Failed to read shapes from file");
    read_shapes_from_file("shapes.txt", &mut doo_shapes).expect("Failed to read shapes from file");
    read_shapes_from_file("shapes.txt", &mut doo_coeff_shapes).expect("Failed to read shapes from file");

    let solid_area = solid_shapes.get_total_area();
    let doo_area = doo_shapes.get_total_area();
    let doo_coeff_area = doo_coeff_shapes.get_total_area();

    assert_eq_eps!(solid_area, doo_area, 1e-10);
    assert_eq_eps!(solid_area, doo_coeff_area, 1e-10);
}


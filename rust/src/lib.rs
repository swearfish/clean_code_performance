pub mod solid;
pub mod boxed_collection;
pub mod factory;
pub mod doo;
pub mod doo_coeff;
pub mod assert_eq_eps;

pub use factory::{read_shapes_from_file, ShapeCollection};
pub use doo::{ShapeData, ShapeDataCollection};
pub use doo_coeff::ShapeDataCoeff;
pub use boxed_collection::ShapeDataBoxedCollection;
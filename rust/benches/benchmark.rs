use criterion::{black_box, criterion_group, criterion_main, Criterion};
use rust::{read_shapes_from_file, ShapeData, ShapeDataCoeff, ShapeDataCollection, ShapeCollection, ShapeDataBoxedCollection};
use rust::doo_coeff::calc_area_fast;
use rust::solid::SolidShapeCollection;

fn benchmark_solid_shapes(c: &mut Criterion) {
    let mut solid_shapes = SolidShapeCollection::new();
    read_shapes_from_file(black_box("shapes.txt"), &mut solid_shapes).expect("Failed to read shapes from file");
    c.bench_function("solid_shapes_total_area", |b| {
        b.iter(|| {
            solid_shapes.get_total_area()
        })
    });
}

fn benchmark_boxed_shapes(c: &mut Criterion) {
    let mut boxed_shapes: ShapeDataBoxedCollection<ShapeData> = ShapeDataBoxedCollection::new();
    read_shapes_from_file(black_box("shapes.txt"), &mut boxed_shapes).expect("Failed to read shapes from file");
    c.bench_function("boxed_shapes_total_area", |b| {
        b.iter(|| {
            boxed_shapes.get_total_area()
        })
    });
}

fn benchmark_doo_shapes(c: &mut Criterion) {
    let mut doo_shapes: ShapeDataCollection<ShapeData> = ShapeDataCollection::new();
    read_shapes_from_file(black_box("shapes.txt"), &mut doo_shapes).expect("Failed to read shapes from file");
    c.bench_function("doo_shapes_total_area", |b| {
        b.iter(|| {
            doo_shapes.get_total_area()
        })
    });
}

fn benchmark_doo_coeff_shapes(c: &mut Criterion) {
    let mut doo_coeff_shapes: ShapeDataCollection<ShapeDataCoeff> = ShapeDataCollection::new();
    read_shapes_from_file(black_box("shapes.txt"), &mut doo_coeff_shapes).expect("Failed to read shapes from file");
    c.bench_function("doo_coeff_shapes_total_area", |b| {
        b.iter(|| {
            doo_coeff_shapes.get_total_area()
        })
    });
    c.bench_function("doo_coeff_shapes_total_area_fast", |b| {
        b.iter(|| {
            calc_area_fast(&doo_coeff_shapes)
        })
    });
}

fn benchmark_boxed_coeff_shapes(c: &mut Criterion) {
    let mut boxed_shapes: ShapeDataBoxedCollection<ShapeDataCoeff> = ShapeDataBoxedCollection::new();
    read_shapes_from_file(black_box("shapes.txt"), &mut boxed_shapes).expect("Failed to read shapes from file");
    c.bench_function("boxed_coeff_shapes_total_area", |b| {
        b.iter(|| {
            boxed_shapes.get_total_area()
        })
    });
}


criterion_group!(benches,
    benchmark_solid_shapes,
    benchmark_boxed_shapes,
    benchmark_doo_shapes,
    benchmark_doo_coeff_shapes,
    benchmark_boxed_coeff_shapes,
);

criterion_main!(benches);
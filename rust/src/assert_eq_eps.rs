#[macro_export]
macro_rules! assert_eq_eps {
    ($a:expr, $b:expr, $eps:expr) => {
        assert!(($a - $b).abs() < $eps, "assertion failed: `(left == right)` (left: `{}`, right: `{}`, epsilon: `{}`)", $a, $b, $eps);
    };
}
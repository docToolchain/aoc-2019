use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;
use std::path::Path;

fn read_puzzle_input(path: &Path) -> std::vec::Vec<f64> {
    let file = File::open(path).unwrap();
    let content = BufReader::new(&file);
    let mut vec = Vec::new();
    for line in content.lines() {
        let number: f64 = line.unwrap().parse().unwrap();
        vec.push(number);
    }
    return vec
}

fn calculate_fuel(mass: f64) -> f64 {
    return (mass / 3.0).floor() - 2.0;
}

fn calculate_fuel_recursive(mass: f64) -> f64 {
    let fuel: f64 = calculate_fuel(mass);
    if fuel < 0.0 {
        return 0.0
    } else {
        return fuel + calculate_fuel_recursive(fuel)
    };
}

fn main() {
    let input_path = Path::new("input.txt");
    let puzzle_input = read_puzzle_input(&input_path);

    let solution_part_1: f64 = puzzle_input.iter().map(|&mass| calculate_fuel(mass)).sum();
    println!("Solution to part 1: {}", solution_part_1);

    let solution_part_2: f64 = puzzle_input.iter().map(|&mass| calculate_fuel_recursive(mass)).sum();
    println!("Solution to part 2: {}", solution_part_2);
}

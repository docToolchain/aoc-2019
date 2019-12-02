use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> io::Result<()> {
    let file = File::open("input.txt")?;
    let reader = BufReader::new(file);

    let mut fuel_con_sum: i32 = 0;
    let mut fuel_con_recurs_sum: i32 = 0;

    for line in reader.lines() {
        let line_string: String = line?;
        
        let mass: i32 = line_string
        .trim()
        .parse()
        .expect("Wanted a number");
        
        fuel_con_sum += calc_fuel_cons(mass);
        fuel_con_recurs_sum += calc_fuel_cons_recurs(mass);
    }

    println!("Non fuel weight aware sum {}", fuel_con_sum);
    println!("Fuel weight aware sum: {}", fuel_con_recurs_sum);

    Ok(())
}

fn calc_fuel_cons(mass: i32) -> i32 {
    return (mass / 3) - 2;
}

fn calc_fuel_cons_recurs (mass: i32) -> i32 {
    let fuel_needed: i32 = (mass / 3) - 2;

    if fuel_needed <= 0 {
        return 0;
    }

    return fuel_needed + calc_fuel_cons_recurs(fuel_needed);
}
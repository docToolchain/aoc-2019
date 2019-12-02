#!/usr/bin/env groovy

int fuel = 0

// iterate over all modules
new File('input.txt').eachLine('UTF-8') { line ->
    int mass = Integer.parseInt(line)
    
    // Calculate fuel for single modules
    int tempFuel = calcFuel(mass)
    fuel += tempFuel

    // Calculate fuel for transporting the fuel
    // tag::recursion[]
    while (tempFuel > 0) {
        tempFuel = calcFuel(tempFuel) < 0 ? 0 : calcFuel(tempFuel)
        fuel += tempFuel
    }
    // end::recursion[]
}

// calc fuel for mass
int calcFuel(int mass) {
    return (mass / 3).trunc() - 2
}

println fuel
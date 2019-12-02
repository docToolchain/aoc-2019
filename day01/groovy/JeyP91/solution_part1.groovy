#!/usr/bin/env groovy

int fuel = 0

// iterate over all modules
new File('input.txt').eachLine('UTF-8') { line ->
    int mass = Integer.parseInt(line)
    fuel += calcFuel(mass)
}

// tag::calcFuel[]
int calcFuel(int mass) {
    return (mass / 3).trunc() - 2
}
// end::calcFuel[]

println fuel
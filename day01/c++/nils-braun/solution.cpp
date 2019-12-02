// Solution for day01: Read in a file
#include <fstream>
#include <iostream>
#include <assert.h>

// From the exercise: take its mass, divide by three, round down, and subtract 2.
unsigned int calculateFuel(unsigned int mass) {
    if(mass < 6) {
        return 0;
    }
    return static_cast<unsigned int>(mass / 3) - 2;
}

// Iterate until we do not need to add fuel anymore
unsigned int iterativelyAddFuel(unsigned int fuel) {
    unsigned int fuelToAdd = calculateFuel(fuel);
    while(fuelToAdd != 0) {
        fuel += fuelToAdd;
        fuelToAdd = calculateFuel(fuelToAdd);
    }
    return fuel;
}

int main()
{
    unsigned int totalFuelFirstStar = 0;
    unsigned int totalFuelSecondStar = 0;

    // Read in the file and add up the fuel costs
    std::ifstream inputFile("input.txt");

    unsigned int mass = 0;
    while(inputFile >> mass) {
        const unsigned int moduleFuel = calculateFuel(mass);
        totalFuelFirstStar += moduleFuel;
        totalFuelSecondStar += iterativelyAddFuel(moduleFuel);
    }

    // Print the results
    std::cout << "Fuel costs of modules: " << totalFuelFirstStar << std::endl;
    std::cout << "Fuel costs of modules and fuel: " << totalFuelSecondStar << std::endl;
}

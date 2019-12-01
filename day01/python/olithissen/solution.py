#!/usr/bin/env python3

import math

def calculateFuel(weight):
    fuel = math.floor(weight / 3) - 2
    return fuel

def calculateFuelRecursive(weight):
    fuel = math.floor(weight / 3) - 2
    if fuel > 0:
        return fuel + calculateFuelRecursive(fuel)
    else:
        return 0

totalFuelRequired = 0

input = open("input.txt", "r")
for line in input:
    fuelRequired = calculateFuelRecursive(int(line))
    totalFuelRequired += fuelRequired
    print("Fuel required: ", fuelRequired)
input.close()
print("Total fuel required: ", totalFuelRequired)

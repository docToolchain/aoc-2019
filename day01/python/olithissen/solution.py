#!/usr/bin/env python3

import math

def solutionStar1(inputFile):
    totalFuelRequired = 0
    
    with open(inputFile, "r") as input:
        for line in input:
            fuelRequired = calculateFuel(int(line))
            totalFuelRequired += fuelRequired
        return totalFuelRequired

def solutionStar2(inputFile):
    totalFuelRequired = 0
    
    with open(inputFile, "r") as input:
        for line in input:
            fuelRequired = calculateFuelRecursive(int(line))
            totalFuelRequired += fuelRequired
        return totalFuelRequired

def calculateFuel(weight):
    fuel = math.floor(weight / 3) - 2
    return fuel

def calculateFuelRecursive(weight):
    fuel = math.floor(weight / 3) - 2
    if fuel > 0:
        return fuel + calculateFuelRecursive(fuel)
    else:
        return 0

sampleResultStar1 = solutionStar1("sample.txt")
realResultStar1 = solutionStar1("input.txt")
sampleResultStar2 = solutionStar2("sample.txt")
realResultStar2 = solutionStar2("input.txt")

print("Sample result *1: ", sampleResultStar1)
assert sampleResultStar1 == 34241

print("Real result *1: ", realResultStar1)
assert realResultStar1 == 3087896

print("Sample result *2: ", sampleResultStar2)
assert sampleResultStar2 == 51316


print("Real result *2: ", realResultStar2)
assert realResultStar2 == 4628989

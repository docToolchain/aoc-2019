#!/usr/bin/python
import math

# Part 1

data = open("input.txt", "r").read().split('\n')

sum = 0
for mass in data:
    # calculate required fuel for each module
    fuel = math.floor(int(mass)/3)-2
    # calculate sum of required fuel
    sum += fuel

print("Sum of the fuel requirements: ", sum)

# Part 2

def calculate_required_fuel(mass):
    return math.floor(int(mass)/3)-2

sum_total = 0
for mass in data:
    # calculate required fuel for each module
    fuel_mass = calculate_required_fuel(mass)
    # calculate sum of required fuel
    sum_total += fuel_mass

    round = 1
    while (fuel_mass > 0):
        fuel_mass = calculate_required_fuel(fuel_mass)
        if fuel_mass < 0:
            fuel_mass = 0
        #print("round:", round, fuel_mass)
        sum_total += fuel_mass
        round = round + 1

print("Sum of the fuel requirements with additional fuel: ", sum_total)
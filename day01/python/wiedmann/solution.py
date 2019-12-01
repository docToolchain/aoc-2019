import math

def fuel_needed(mass):
    return math.floor(int(mass)/3 - 2)

def fuel_needed_recursive(mass):
    fuel_needed_i = fuel_needed(mass)
    if (fuel_needed_i <= 0):
        return 0
    return fuel_needed_i + fuel_needed_recursive(fuel_needed_i)

total_fuel = 0
total_fuel_recursive = 0

with open("input.txt", "r") as fp:
    for line in fp:
        total_fuel += fuel_needed(line)
        total_fuel_recursive += fuel_needed_recursive(line)

print("Total fuel: " + str(total_fuel))
print("Total fuel recursive: " + str(total_fuel_recursive))

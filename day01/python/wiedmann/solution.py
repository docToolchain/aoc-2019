import math

def fuel_needed(mass):
    return math.floor(int(mass)/3 - 2)

total_fuel = 0

with open("input.txt", "r") as fp:
    for line in fp:
        total_fuel += fuel_needed(line)

print("Total fuel: " + str(total_fuel))

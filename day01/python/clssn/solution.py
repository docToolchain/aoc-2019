total_fuel_mass = 0
total_fuel_mass_recursive = 0

def calc_fuel(m):
    return int(m)/3 - 2

def calc_fuel_recursive(m):
    fuel_m = calc_fuel(m)
    return (fuel_m + calc_fuel_recursive(fuel_m)) if fuel_m >= 0 else 0

with open("input.txt", "r") as f:
    for line in f:
        total_fuel_mass += calc_fuel(line)
        total_fuel_mass_recursive += calc_fuel_recursive(line)

print("Fuel first star:  {}".format(total_fuel_mass))
print("Fuel second star: {}".format(total_fuel_mass_recursive))

import math

def calculate_fuel_requirement(mass):
    """Calculates the fuel requirement
    based on the given mass. (Part 1)

    Parameters:
    mass (int): mass of the module

    Returns:
    int:Fuel required

    """
    return math.floor(mass / 3) - 2

def calculate_fuel_requirement_plus(fuel):
    """Calculates the fuel requirement
    based on the given fuel mass. (Part 2)

    Parameters:
    fuel (int): mass of the fuel

    Returns:
    int:Additional fuel required

    """
    add = math.floor(fuel / 3) - 2 
    if add > 0:
        return add + calculate_fuel_requirement_plus(add)
    else:
        return 0

with open("input.txt", "r") as masses:
    fuel = 0
    fueladd = 0
    for mass in masses:
        mfuel = calculate_fuel_requirement(int(mass))
        fuel = fuel + mfuel
        fueladd = fueladd + calculate_fuel_requirement_plus(mfuel)
    print('Fuel required: {0}'.format(str(fuel)))
    print('Fuel required based on fuel: {0}'.format(str(fueladd)))
    print('Fuel required total: {0}'.format(str(fuel + fueladd)))
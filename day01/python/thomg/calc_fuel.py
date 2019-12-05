with open("input") as file:
    massList = file.readlines()

def calcModuleFuel(fuel, mass):
    addedFuel = int(mass/3)-2
    if addedFuel <=0:
        return fuel
    else:
        return calcModuleFuel(fuel+addedFuel, addedFuel)

def calcFuelSum(massList):
    fuelSum = 0
    for m in massList:
        m = float(m)
        fuelSum  += calcModuleFuel(0, m)
    return fuelSum

print calcFuelSum(massList)
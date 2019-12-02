def calc_fuel_con (mass):
    return int((mass / 3) - 2)

def calc_fuel_con_iter (mass):
    fuel_sum = 0
    fuel_con = int((mass / 3) - 2)

    while fuel_con > 0:
        fuel_sum += fuel_con
        fuel_con = int((fuel_con / 3) - 2)

    return fuel_sum

with open("input.txt", "r") as fd:
    masses = [int(line.strip()) for line in fd]

    fuel_consum = [calc_fuel_con(mass) for mass in masses]
    fuel_consum_iter = [calc_fuel_con_iter(mass) for mass in masses]

    print(sum(fuel_consum))
    print(sum(fuel_consum_iter))

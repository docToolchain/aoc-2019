import math

INPUT_FILE_NAME = 'input.txt'

puzzle_input = None
with open(INPUT_FILE_NAME) as input_file:
    puzzle_input = [float(n) for n in input_file.readlines()]


def calculate_fuel(mass):
    return math.floor(mass / 3) - 2


solution_part_1 = sum(map(calculate_fuel, puzzle_input))
print('Solution to part 1: %i' % (solution_part_1,))


def calculate_fuel_recursive(mass):
    fuel = calculate_fuel(mass)
    return 0 if fuel < 0 else fuel + calculate_fuel_recursive(fuel)


solution_part_2 = sum(map(calculate_fuel_recursive, puzzle_input))
print('Solution to part 2: %i' % (solution_part_2,))

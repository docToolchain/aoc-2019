from typing import List
import math

def format_file(input_file) -> List[int]:
    new_list = []
    for row in input_file:
        new_list.append(int(row))
    return new_list

def calculate_fuel(all_modules: List[int], func) -> int:
    required_fuel = 0
    for module_mass in all_modules:
        fuel_required = func(module_mass)
        required_fuel += fuel_required
    return required_fuel

def solution_one(module_mass: int) -> int:
    return math.floor(module_mass / 3) - 2

def solution_two(module_mass: int) -> int:
    fuel_required = solution_one(module_mass)

    if fuel_required > 0:
        return fuel_required + solution_two(fuel_required)
    return 0

if __name__ == '__main__':
    with open('./puzzle_input.txt') as input_file:
        modules = format_file(input_file)

        fuel_one = calculate_fuel(modules, solution_one)
        print("Solution one with provided input parameters: %d" % fuel_one)

        fuel_two = calculate_fuel(modules, solution_two)
        print("Solution two with provided input parameters: %d" % fuel_two)
#!/usr/bin/ruby
def required_fuel(mass)
  (mass / 3) - 2
end

def part1(massInventory)
  fuelSum = massInventory
    .map { |mass| required_fuel(mass) }
    .reduce(0) { |sum, num| sum + num }
end

def part2(massInventory)
  fuelSum = 0
  massInventory.each{ |mass|
    fuel = required_fuel(mass)
    while fuel > 0 do
      fuelSum += fuel
      fuel = required_fuel(fuel)
    end
  }
  fuelSum
end

if caller.length == 0
  input = File.read("./input.txt")

  massInventory = input.split("\n").map(&:to_i)

  puts "Solution for part1: %d" % part1(massInventory)
  puts "Solution for part2: %d" % part2(massInventory)
end
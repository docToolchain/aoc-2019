package main

import (
	"fmt"
	"math"
)

func main() {
	input := readInput("./input.txt")
	part1(StringSlice2IntSlice(input))
	part2(StringSlice2IntSlice(input))
}

func part1(massInventory []int) {
	var fuelSum int = 0
	for _, mass := range massInventory {
		fuelSum += requiredFuel(mass)
	}
	fmt.Println("Solution for part1:", fuelSum)
}

func part2(massInventory []int) {
	var fuelSum int = 0
	for _, mass := range massInventory {
		fuelSum += requiredFuelRecursive(mass)
	}
	fmt.Println("Solution for part2:", fuelSum)
}

func requiredFuel(mass int) int {
	var massFloat = float64(mass)
	var fuelFloat = math.Floor(massFloat / 3)
	return int(fuelFloat) - 2
}

func requiredFuelRecursive(mass int) int {
	var massFloat = float64(mass)
	var fuelFloat = math.Floor(massFloat/3) - 2
	var fuelInt = int(math.Max(fuelFloat, 0))
	if fuelInt > 0 {
		var additionalFuel = requiredFuelRecursive(fuelInt)
		fuelInt += additionalFuel
	}
	return fuelInt
}

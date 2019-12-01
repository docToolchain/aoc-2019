package main

import (
	"fmt"
	"math"
)

// tag::part1[]
func main() {
	input := readInput("./input.txt")
	part1(StringSlice2IntSlice(input))
}

func part1(massInventory []int) {
	var fuelSum int = 0
	for _, mass := range massInventory {
		fmt.Println(mass)
		fuelSum += RequiredFuel(mass)
	}
	fmt.Println("Solution for part1:", fuelSum)
}

// end::part1[]
func part2() {

}

// tag::part1[]
func RequiredFuel(mass int) int {
	var massFloat = float64(mass)
	var fuelFloat = math.Floor(massFloat / 3)
	return int(fuelFloat) - 2
}

// end::part1[]

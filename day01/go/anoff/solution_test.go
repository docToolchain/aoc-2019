package main

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestRequiredFuel(t *testing.T) {
	m := make(map[int]int)
	m[12] = 2
	m[14] = 2
	m[1969] = 654
	m[100756] = 33583
	for mass, fuel := range m {
		res := requiredFuel(mass)
		assert.Equal(t, fuel, res, "Wrong result for mass=%d", mass)
	}
}

func TestRequiredFuelRecursive(t *testing.T) {
	m := make(map[int]int)
	m[12] = 2
	m[14] = 2
	m[1969] = 966
	m[100756] = 50346
	for mass, fuel := range m {
		res := requiredFuelRecursive(mass)
		assert.Equal(t, fuel, res, "Wrong result for mass=%d", mass)
	}
}

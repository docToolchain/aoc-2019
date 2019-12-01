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
		res := RequiredFuel(mass)
		assert.Equal(t, fuel, res, "Wrong result for mass=%d", mass)
	}
}

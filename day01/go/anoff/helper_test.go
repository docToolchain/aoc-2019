package main

import (
	"testing"
)

func TestStringSliceEqual(t *testing.T) {
	s1 := []string{"A", "CD", "BABA"}
	s2 := []string{"CD", "A", "BABA"}
	s3 := []string{"CD", "A", "D"}
	s4 := []string{"CD", "A", "D", "b", "SADF"}
	if !StringSliceEqual(s1, s2) {
		t.Error("Expected slices to be equal", s1, s2)
	}
	if StringSliceEqual(s1, s3) {
		t.Error("Expected slices to be unequal", s1, s3)
	}
	if StringSliceEqual(s1, s4) {
		t.Error("Expected slices to be unequal", s1, s4)
	}
}

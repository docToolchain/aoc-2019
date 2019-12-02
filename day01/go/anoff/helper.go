package main

import (
	"io/ioutil"
	"strconv"
	"strings"
)

func readInput(filepath string) []string {
	b, err := ioutil.ReadFile(filepath)
	if err != nil {
		panic(err)
	}

	s := string(b)
	return strings.Split(s, "\n")
}

func StringSlice2IntSlice(strNums []string) []int {
	var nums []int
	for _, s := range strNums {
		if len(s) > 0 {
			n, _ := strconv.Atoi(s)
			nums = append(nums, n)
		}
	}
	return nums
}

func StringSliceEqual(s1 []string, s2 []string) bool {
	aInB := func(a string, b []string) bool {
		for _, e := range b {
			if e == a {
				return true
			}
		}
		return false
	}

	for _, e := range s1 {
		if !aInB(e, s2) {
			return false
		}
	}
	for _, e := range s2 {
		if !aInB(e, s1) {
			return false
		}
	}
	return true
}

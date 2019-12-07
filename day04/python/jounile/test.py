import unittest
import solution

# Part 1
example_1 = 111111 # meets these criteria (double 11, never decreases).
example_2 = 223450 # does not meet these criteria (decreasing pair of digits 50).
example_3 = 123789 # does not meet these criteria (no double).

# Part 2
example_4 = 112233 # meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
example_5 = 123444 # no longer meets the criteria (the repeated 44 is part of a larger group of 444).
example_6 = 111122 # meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).

class TestSum(unittest.TestCase):

    def test_example_1(self):
        self.assertEqual(solution.passes_criteria(example_1), True, "111111 passes")
  
    def test_example_2(self):
        self.assertEqual(solution.passes_criteria(example_2), False, "223450 does not pass")

    def test_example_3(self):
        self.assertEqual(solution.passes_criteria(example_3), False, "123789 does not pass")

    def test_example_4(self):
        self.assertEqual(solution.passes_criteria(example_4), True, "112233 passes")

    def test_example_5(self):
        self.assertEqual(solution.passes_criteria(example_5), False, "123444 does not pass")

    def test_example_6(self):
        self.assertEqual(solution.passes_criteria(example_6), True, "111122 passes")

if __name__ == '__main__':
    unittest.main()






import pytest
from unittest import TestCase
from enterprise import NyotaUhura as Uhura

class Test_Enterprise(TestCase):
    def test_no_decrease_one(self):
        uhura = Uhura()
        res = uhura.valid_no_decrease(111111, 111112)
        self.assertEqual(res, ["111111"])

    def test_no_decrease_two(self):
        uhura = Uhura()
        res = uhura.valid_no_decrease(223450, 223451)
        self.assertEqual(res, [])

    def test_same_one(self):
        uhura = Uhura()
        res = uhura.valid_same(["123789"])
        self.assertEqual(res, []) 

    def test_large_one(self):
        uhura = Uhura()
        res = uhura.valid_larger_group(["112233"])
        self.assertEqual(res, ["112233"])  

    def test_large_two(self):
        uhura = Uhura()
        res = uhura.valid_larger_group(["123444"])
        self.assertEqual(res, [])   

    def test_large_three(self):
        uhura = Uhura()
        res = uhura.valid_larger_group(["111122"])
        self.assertEqual(res, ["111122"]) 

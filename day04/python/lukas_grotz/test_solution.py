import unittest
from solution import test_increase, test_length, test_range, test_adjacency

class TestSolution(unittest.TestCase):
    def test_increase(self):
        self.assertTrue(test_increase('1279'))
        self.assertFalse(test_increase('1297'))

    def test_length(self):
        self.assertFalse(test_length('12'))
        self.assertTrue(test_length('123465'))
        self.assertFalse(test_length('9876543'))
    
    def test_range(self):
        self.assertFalse(test_range('1'))
        self.assertTrue(test_range('500000'))
        self.assertFalse(test_range('10000000'))

    def test_adjacency(self):
        self.assertFalse(test_adjacency('12345'))
        self.assertFalse(test_adjacency('123245'))
        self.assertFalse(test_adjacency('111'))
        self.assertTrue(test_adjacency('111123455'))
        self.assertTrue(test_adjacency('1234588'))
        self.assertTrue(test_adjacency('122345'))
        self.assertTrue(test_adjacency('11223344'))
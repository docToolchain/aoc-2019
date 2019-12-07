import pytest
from unittest import TestCase
from enterprise import MontgomeryScott as Scotty

class Test_Enterprise(TestCase):
    def test_translate(self):
        scotty = Scotty()
        res = scotty.translate('3,0,4,0,99')
        self.assertEqual(res, [3, 0, 4, 0, 99])

    def test_execute_one(self):
        scotty = Scotty()
        trans = scotty.translate('3,0,4,0,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [3, 0, 4, 1, 99])

    def test_execute_two(self):
        scotty = Scotty()
        trans = scotty.translate('1002,4,3,4,33')
        res = scotty.execute(trans)
        self.assertEqual(res, [1002,4,3,4,99])
    
    
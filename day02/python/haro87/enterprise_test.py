import pytest
from unittest import TestCase
from enterprise import MontgomeryScott as Scotty

class Test_Enterprise(TestCase):
    def test_translate(self):
        scotty = Scotty()
        res = scotty.translate('1,0,0,3,99')
        self.assertEqual(res, [1, 0, 0, 3, 99])

    def test_execute_one(self):
        scotty = Scotty()
        trans = scotty.translate('1,0,0,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [1, 0, 0, 2, 99])
    
    def test_execute_two(self):
        scotty = Scotty()
        trans = scotty.translate('1,9,10,3,2,3,11,0,99,30,40,50')
        res = scotty.execute(trans)
        self.assertEqual(res, [3500,9,10,70,2,3,11,0,99,30,40,50])
    
    def test_execute_three(self):
        scotty = Scotty()
        trans = scotty.translate('2,3,0,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [2,3,0,6,99])

    def test_execute_four(self):
        scotty = Scotty()
        trans = scotty.translate('2,4,4,5,99,0')
        res = scotty.execute(trans)
        self.assertEqual(res, [2,4,4,5,99,9801])
    
    def test_execute_five(self):
        scotty = Scotty()
        trans = scotty.translate('1,1,1,4,99,5,6,0,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [30,1,1,4,2,5,6,0,99])

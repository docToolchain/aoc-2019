import pytest
from unittest import TestCase
from enterprise import MontgomeryScott as Scotty

class Test_Enterprise(TestCase):
    def test_translate(self):
        scotty = Scotty(input=1)
        res = scotty.translate('3,0,4,0,99')
        self.assertEqual(res, [3, 0, 4, 0, 99])

    def test_execute_pos_equal_eight(self):
        scotty = Scotty(input=8)
        trans = scotty.translate('3,9,8,9,10,9,4,9,99,-1,8')
        res = scotty.execute(trans)
        self.assertEqual(res, [1])
    
    def test_execute_pos_not_equal_eight(self):
        scotty = Scotty(input=1)
        trans = scotty.translate('3,9,8,9,10,9,4,9,99,-1,8')
        res = scotty.execute(trans)
        self.assertEqual(res, [0])
    
    def test_execute_pos_less_than_eight(self):
        scotty = Scotty(input=1)
        trans = scotty.translate('3,9,7,9,10,9,4,9,99,-1,8')
        res = scotty.execute(trans)
        self.assertEqual(res, [1])
    
    def test_execute_pos_more_than_eight(self):
        scotty = Scotty(input=9)
        trans = scotty.translate('3,9,7,9,10,9,4,9,99,-1,8')
        res = scotty.execute(trans)
        self.assertEqual(res, [0])
    
    def test_execute_im_equal_eight(self):
        scotty = Scotty(input=8)
        trans = scotty.translate('3,3,1108,-1,8,3,4,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [1])
    
    def test_execute_im_not_equal_eight(self):
        scotty = Scotty(input=1)
        trans = scotty.translate('3,3,1108,-1,8,3,4,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [0])
    
    def test_execute_im_less_than_eight(self):
        scotty = Scotty(input=1)
        trans = scotty.translate('3,3,1107,-1,8,3,4,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [1])
    
    def test_execute_im_more_than_eight(self):
        scotty = Scotty(input=9)
        trans = scotty.translate('3,3,1107,-1,8,3,4,3,99')
        res = scotty.execute(trans)
        self.assertEqual(res, [0])

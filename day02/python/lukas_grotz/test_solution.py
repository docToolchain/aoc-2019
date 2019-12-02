from unittest import TestCase
from unittest.mock import Mock, patch, mock_open
from solution import IntCode, StopCode

class TestIntCode(TestCase):
    def test_instantiation(self):
        codes = IntCode()

        self.assertIsInstance(codes, IntCode)

    def test_read_file(self):
        codes = IntCode()
        m = mock_open(read_data='1,2,3,4,5')
        mock_path = 'mock_file.txt'

        with patch('solution.open', m, create=True):
            result = codes.read_file(mock_path)
            self.assertIsInstance(result, IntCode)

        m.assert_called_once_with(mock_path)
        self.assertEqual([1,2,3,4,5], result.input_codes)

    def test_parse_code(self):
        lhs = 1
        rhs = 2

        add_func = IntCode.parse_code(1)
        mul_func = IntCode.parse_code(2)
        stop_func = IntCode.parse_code(99)

        self.assertEqual(3, add_func(lhs, rhs))
        self.assertEqual(2, mul_func(lhs, rhs))
        with self.assertRaises(StopCode):
            stop_func(lhs, rhs)

    def test_step_add(self):
        codes = [1,2,3,3,2,3,4,0,99]
        expected_output = [1,2,3,6,2,3,4,0,99]
        int_codes = IntCode(codes)

        int_codes.execute_step(0)

        self.assertEqual(expected_output, int_codes.input_codes)

    def test_step_mul(self):
        codes = [1,2,3,3,2,3,4,0,99]
        expected_output = [6,2,3,3,2,3,4,0,99]
        int_codes = IntCode(codes)

        int_codes.execute_step(4)

        self.assertEqual(expected_output, int_codes.input_codes)

    def test_step_stop(self):
        codes = [1,2,3,3,2,3,4,0,99]
        int_codes = IntCode(codes)

        with self.assertRaises(StopCode):
            int_codes.execute_step(8)

    def test_execute_all(self):
        codes = [1,2,3,3,2,3,4,0,99]
        expected_output = [12,2,3,6,2,3,4,0,99]
        int_codes = IntCode(codes)

        result = int_codes.execute_all()

        self.assertEqual(expected_output, result)
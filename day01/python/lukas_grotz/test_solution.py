from unittest import TestCase
from unittest.mock import MagicMock
from solution import calculate_fuel, solution_one, solution_two

class TestSolution(TestCase):
    def test_calculate_fuel_method_call(self):
        # arrange
        test_list = [20]
        mock_func = MagicMock()

        # act
        calculate_fuel(test_list, mock_func)

        # assert
        mock_func.assert_called_once_with(test_list[0])

    def test_calculate_fuel_result(self):
        # arrange
        test_list = [20, 30, 40]
        mock_func = MagicMock()
        mock_func.return_value = 5

        # act
        actual = calculate_fuel(test_list, mock_func)

        # assert
        self.assertEqual(15, actual)

    def test_solution_one(self):
        # arrange
        test_values = [
            {'input': 20, 'expected': 4},
            {'input': 3, 'expected': -1},
            {'input': 50, 'expected': 14},
        ]

        for value in test_values:
            # act
            actual = solution_one(value['input'])

            # assert
            self.assertEqual(value['expected'], actual)

    def test_solution_two(self):
        # arrange
        test_values = [
            {'input': 20, 'expected': 4},
            {'input': 3, 'expected': 0},
            {'input': 50, 'expected': 16},
        ]

        for value in test_values:
            # act
            actual = solution_two(value['input'])

            # assert
            self.assertEqual(value['expected'], actual)
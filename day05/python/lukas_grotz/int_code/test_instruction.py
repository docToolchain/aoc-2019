from unittest import TestCase
from .instruction import Instruction


class TestInstruction(TestCase):
    def test_instantiation(self):
        inst = Instruction(lambda x: x+1, 2, True)
        self.assertIsInstance(inst, Instruction)

    def test_return_index(self):
        inst = Instruction(lambda x: x+1, 2, True)
        test_instructions = [101, 1, 2]

        index = inst.return_index(0, test_instructions)

        self.assertEqual(2, index)

    def test_execute_test(self):
        def test_func(lhs):
            return lhs + 1
        inst = Instruction(test_func, 2, True)
        test_instructions = [101, 1, 0]

        ret_val = inst.execute(0, test_instructions, '1')

        self.assertEqual(ret_val, 3)
        self.assertEqual([2, 1, 0], test_instructions)

    def test_execute_modify_instruction_pointer(self):
        def test_func(lhs):
            return 5, True
        inst = Instruction(test_func, 1, False, True)
        test_instructions = [101, 1, 0]

        ret_val = inst.execute(0, test_instructions, '1')

        self.assertEqual(ret_val, 5)
        self.assertEqual([101, 1, 0], test_instructions)

    def test_parse_params_retval(self):
        inst = Instruction(lambda x: x+1, 4, True)
        test_instructions = [101, 10, 0, 1, 3]

        ret_val = inst.parse_params(0, test_instructions, '101')

        self.assertEqual([10, 101, 1], ret_val)

    def test_parse_params_no_retval(self):
        inst = Instruction(lambda x: x+1, 2, False)
        test_instructions = [101, 10, 0, 1, 3]

        ret_val = inst.parse_params(0, test_instructions, '1')

        self.assertEqual([10, 101], ret_val)

    def test_parse_params_position_mode_only(self):
        inst = Instruction(lambda x: x+1, 2, True)
        test_instructions = [101, 0, 2, 1, 3]

        ret_val = inst.parse_params(0, test_instructions)

        self.assertEqual([101], ret_val)

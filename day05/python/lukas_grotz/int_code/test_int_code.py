from unittest import TestCase
from .int_code import IntCodeMachine, StopCode
from .instruction import Instruction


class TestIntCodeMachine(TestCase):
    def test_instantiation(self):
        code_machine = IntCodeMachine()
        self.assertIsInstance(code_machine, IntCodeMachine)

    def test_parse_code(self):
        def add(lhs, rhs):
            return lhs + rhs, False
        code_machine = IntCodeMachine([])
        expected_instruction = Instruction(add, 3, True)
        code_machine.instructions[1] = expected_instruction

        ret_instruction, modes = code_machine.parse_code(1001)

        self.assertEqual(expected_instruction, ret_instruction)
        self.assertEqual('10', modes)

    def test_instruction_add(self):
        def add(lhs, rhs):
            return lhs + rhs
        codes = [1001, 2, 4, 3, 2, 3, 4, 0, 99]
        expected_output = [1001, 2, 4, 8, 2, 3, 4, 0, 99]
        code_machine = IntCodeMachine(codes)
        code_machine.instructions[1] = Instruction(add, 3, True)

        code_machine.execute_step()

        self.assertEqual(expected_output, code_machine.codes)
        self.assertEqual(4, code_machine.instruction_pointer)


    def test_instrucion_mul(self):
        def mul(lhs, rhs):
            return lhs * rhs
        codes = [1002, 4, 3, 4, 33]
        expected_output = [1002, 4, 3, 4, 99]
        code_machine = IntCodeMachine(codes)
        code_machine.instructions[2] = Instruction(mul, 3, True)

        code_machine.execute_step()

        self.assertEqual(expected_output, code_machine.codes)
        self.assertEqual(4, code_machine.instruction_pointer)

    def test_execute_all_simple(self):
        def add(lhs, rhs):
            return lhs + rhs

        def mul(lhs, rhs):
            return lhs * rhs
        
        def stop():
            raise StopCode

        codes = [1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50]
        expected_output = [3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50]
        code_machine = IntCodeMachine(codes)
        code_machine.instructions[1] = Instruction(add, 3, True)
        code_machine.instructions[2] = Instruction(mul, 3, True)
        code_machine.instructions[99] = Instruction(stop, 0, False)

        ret_val = code_machine.execute_all()

        self.assertEqual(expected_output, ret_val)
        self.assertEqual(9, code_machine.instruction_pointer)
    

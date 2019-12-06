from typing import List
from int_code import IntCodeMachine, Instruction, StopCode

# Instruction functions need to follow the pattern def func(...) -> result

def add(lhs: int, rhs: int):
    return lhs + rhs

def mul(lhs: int, rhs: int):
    return lhs * rhs

def stop() -> None:
    raise StopCode()

def get_input():
    print('Enter an integer:')
    my_input = int(input())
    return my_input

def output(number: int) -> None:
    print(number)

def jump_if_true(param: int, target: int):
    if param != 0:
        return target, True
    return target, False

def jump_if_false(param: int, target: int):
    if param == 0:
        return target, True
    return target, False

def less_than(lhs: int, rhs: int):
    return int(lhs < rhs)

def equals(lhs: int, rhs: int):
    return int(lhs == rhs)

if __name__ == '__main__':
    op_codes = []
    with open('input.txt') as input_file:
        content = input_file.readlines()    
        for line in content:
            elements = [ int(x) for x in line.split(',')]
            op_codes.extend(elements)

    code_machine = IntCodeMachine(op_codes)
    code_machine.instructions[1] = Instruction(add, 3, True)
    code_machine.instructions[2] = Instruction(mul, 3, True)
    code_machine.instructions[3] = Instruction(get_input, 1, True)
    code_machine.instructions[4] = Instruction(output, 1, False)
    code_machine.instructions[5] = Instruction(jump_if_true, 2, False, True)
    code_machine.instructions[6] = Instruction(jump_if_false, 2, False, True)
    code_machine.instructions[7] = Instruction(less_than, 3, True)
    code_machine.instructions[8] = Instruction(equals, 3, True)
    code_machine.instructions[99] = Instruction(stop, 0, False)

    code_machine.execute_all()

from typing import List
from .instruction import Instruction

class StopCode(Exception):
   pass


class IntCodeMachine:
    def __init__(self, code_list: List[int] = []):
        self.codes = code_list
        self.instruction_pointer = 0
        self.instructions = {}

    @property
    def current_op(self) -> int:
        return self.codes[self.instruction_pointer]

    def execute_step(self) -> None:
        instruction, modes = self.parse_code(self.current_op)
        self.instruction_pointer = instruction.execute(self.instruction_pointer, self.codes, modes)
            
    def parse_code(self, op_code: int) -> (Instruction, str):
        code = str(op_code)
        if len(code)>2:
            instrucion_part = code[-2:]
            modes_part = code[:-2]
        else:
            instrucion_part = code
            modes_part = ''
        if int(instrucion_part) not in self.instructions:
            raise ValueError('Invalid instruction: %d' % int(instrucion_part))
        return self.instructions[int(instrucion_part)], modes_part

    def execute_all(self) -> List[int]:
        try:
            while self.instruction_pointer < len(self.codes):
                self.execute_step()
        except (StopCode):
            # hit 99 or something else lol
            pass
        return self.codes

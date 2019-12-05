from typing import List

class StopCode(Exception):
   pass

class IntCode:
    def __init__(self, input_list: List[int] = []):
        self.input_codes = input_list

    @classmethod
    def read_file(cls, file_path: str) -> 'IntCode':
        with open(file_path) as input_file:
            content = input_file.readlines()
            op_codes = []
            for line in content:
                elements = [ int(x) for x in line.split(',')]
                op_codes.extend(elements)
        int_code = IntCode(op_codes)
        return int_code

    def execute_step(self, op_pos: int) -> None:
        code = self.input_codes[op_pos]
        if code not in [1, 2, 99]:
            raise ValueError('Invalid Code!')

        func =self.parse_code(code)
        if code == 99:
            func()

        lhs = self.input_codes[self.input_codes[op_pos+1]]
        rhs = self.input_codes[self.input_codes[op_pos+2]]
        ret_pos = self.input_codes[op_pos+3]
        self.input_codes[ret_pos] = func(lhs, rhs)
            

    @staticmethod
    def parse_code(op_code: int):
        def add(lhs: int, rhs: int) -> int:
            return lhs + rhs

        def multiply(lhs: int, rhs: int) -> int:
            return lhs * rhs

        def stop(*args) -> None:
            raise StopCode()

        codes = {
            1: add,
            2: multiply,
            99: stop
        }

        return codes[op_code]

    def execute_all(self) -> List[int]:
        try:
            for op in range(0, len(self.input_codes), 4):
                self.execute_step(op)
        except StopCode:
            # hit 99
            pass
        return self.input_codes

if __name__ == '__main__':
    codes = IntCode.read_file('/workspace/aoc-2019/day02/python/lukas_grotz/input.txt')
    codes.input_codes[1] = 12
    codes.input_codes[2] = 2
    codes.execute_all()
    print("Solution for one: %d" % codes.input_codes[0])

    required_output = 19690720
    for first_input in range(100):
        for second_input in range(100):
            codes = IntCode.read_file('/workspace/aoc-2019/day02/python/lukas_grotz/input.txt')
            codes.input_codes[1] = first_input
            codes.input_codes[2] = second_input
            try:
                codes.execute_all()
            except ValueError:
                # invalid code found
                pass
            if required_output == codes.input_codes[0]:
                print("Solution for two: noun=%d verb=%d" % (first_input, second_input))
                print("Solution is: %d" % (100 * first_input + second_input))

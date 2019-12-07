from typing import List


class Instruction:
    def __init__(self, func, parameter_num: int, ret_val: bool, modify_ip: bool = False):
        # func should have signature def func(*args)
        self.func = func
        self.parameter_num = parameter_num
        self.ret_val = ret_val
        self.modify_ip = modify_ip

    def return_index(self, instruction_pointer: int, codes: List[int]) -> int:
        return codes[instruction_pointer+self.parameter_num]

    def execute(self, instruction_pointer: int, codes: List[int], mode: str = '') -> int:
        input_params = self.parse_params(instruction_pointer, codes, mode)
        if self.modify_ip:
            ret_val, modify_ip = self.func(*input_params)
            if modify_ip:
                return ret_val
        elif self.ret_val:
            return_index = self.return_index(instruction_pointer, codes)
            ret_val = self.func(*input_params)
            codes[return_index] = ret_val
        else:
            self.func(*input_params)
        return self.parameter_num + instruction_pointer + 1

    def parse_params(self, instruction_pointer: int, codes: List[int], mode: str = '') -> List[int]:
        input_params = self.parameter_num - int(self.ret_val)
        modes = list(map(lambda x: x == '1', mode.zfill(input_params)))
        params = []
        for i in range(1, input_params+1):
            code = codes[instruction_pointer+i]
            if modes[-i]:
                params.append(code)
            else:
                code = codes[code]
                params.append(code)
        return params

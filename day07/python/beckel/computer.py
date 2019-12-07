debug = 0

class Computer:

    def __init__(self, code, program_input):
        self.code = code
        self.ptr = 0 # ptr: instruction pointer 
        self.program_output = []
        self.program_input = program_input
        self.state = "RUNNING"
    
    def run(self):
        while(True):
            [opcode, modes] = evaluate_instruction(self.code[self.ptr])
            if (opcode == 99):
                self.state = "TERMINATED"
                return
            if (opcode == 1):
                summand_1 = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                summand_2 = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                self.code[self.code[self.ptr+3]] = summand_1 + summand_2
                self.ptr += 4
            if (opcode == 2):
                factor_1 = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                factor_2 = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                self.code[self.code[self.ptr+3]] = factor_1 * factor_2
                self.ptr += 4
            if (opcode == 3):
                if (len(self.program_input) == 0):
                    return # wait for input
                self.code[self.code[self.ptr+1]] = self.program_input.pop(0)
                self.ptr += 2
            if (opcode == 4):
                output = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                if (debug):
                    print("Output:",str(output))
                self.program_output.append(output)
                self.ptr += 2
            if (opcode == 5):
                value = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                jump_to = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                if value != 0:
                    self.ptr = jump_to
                else:
                    self.ptr += 3
            if (opcode == 6):
                value = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                jump_to = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                if value == 0:
                    self.ptr = jump_to
                else:
                    self.ptr += 3
            if (opcode == 7):
                operand_1 = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                operand_2 = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                if operand_1 < operand_2:
                    self.code[self.code[self.ptr+3]] = 1
                else:
                    self.code[self.code[self.ptr+3]] = 0
                self.ptr += 4
            if (opcode == 8):
                operand_1 = self.code[self.ptr+1] if len(modes) >= 1 and modes[0] == 1 else self.code[self.code[self.ptr+1]]
                operand_2 = self.code[self.ptr+2] if len(modes) >= 2 and modes[1] == 1 else self.code[self.code[self.ptr+2]]
                if operand_1 == operand_2:
                    self.code[self.code[self.ptr+3]] = 1
                else:
                    self.code[self.code[self.ptr+3]] = 0
                self.ptr += 4

    def get_last_output(self):
        return self.program_output[len(self.program_output)-1]

    def provide_input(self, i):
        self.program_input.append(i)
    
    def has_terminated(self):
        return True if self.state == "TERMINATED" else False

def evaluate_instruction(instruction):
    opcode_low = instruction % 10
    instruction = int(instruction / 10)
    opcode_high = instruction % 10
    opcode = opcode_high * 10 + opcode_low
    instruction = int(instruction / 10)
    
    modes = []
    while(instruction > 0):
        modes.append(instruction % 10)
        instruction = int(instruction / 10)
        
    return [opcode, modes]
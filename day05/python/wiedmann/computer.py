# add modes

logging = 1

def compute(code):
    i = 0
    while(True):
        [opcode, modes] = evaluate_instruction(code[i])
        if (opcode == 99):
            return
        if (opcode == 1):
            summand_1 = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            summand_2 = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            code[code[i+3]] = summand_1 + summand_2
            i += 4
        if (opcode == 2):
            factor_1 = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            factor_2 = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            code[code[i+3]] = factor_1 * factor_2
            i += 4
        if (opcode == 3):
            code[code[i+1]] = int(input("Provide input: "))
            i += 2
        if (opcode == 4):
            output = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            print("Output:",str(output))
            i += 2
        if (opcode == 5):
            value = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            jump_to = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            if value != 0:
                i = jump_to
            else:
                i += 3
        if (opcode == 6):
            value = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            jump_to = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            if value == 0:
                i = jump_to
            else:
                i += 3
        if (opcode == 7):
            operand_1 = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            operand_2 = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            if operand_1 < operand_2:
                code[code[i+3]] = 1
            else:
                code[code[i+3]] = 0
            i += 4
        if (opcode == 8):
            operand_1 = code[i+1] if len(modes) >= 1 and modes[0] == 1 else code[code[i+1]]
            operand_2 = code[i+2] if len(modes) >= 2 and modes[1] == 1 else code[code[i+2]]
            if operand_1 == operand_2:
                code[code[i+3]] = 1
            else:
                code[code[i+3]] = 0
            i += 4

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
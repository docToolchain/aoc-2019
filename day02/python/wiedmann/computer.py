logging = 0

def compute(code):
    i = 0
    while(True):
        if (logging):
            print(code)
        opcode = code[i]
        if (opcode == 99):
            return
        if (opcode == 1):
            code[code[i+3]] = code[code[i+1]] + code[code[i+2]]
            i = i + 4
        if (opcode == 2):
            code[code[i+3]] = code[code[i+1]] * code[code[i+2]]
            i = i + 4
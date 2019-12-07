class IntcodeComputer:
    def __init__(self, filename):
        with open(filename) as file:
            self.program = file.readlines()[0]
        self.program = self.program.split(",")
        self.program = list(map(int, self.program))
        self.pointer = 0    
    input = []
    output = 0
    has_terminated = 0

    def parseInstruction(self, instruction):
        opcode = instruction % 100
        modes = [int(instruction/100) % 10, int(instruction/1000) % 10]
        return (opcode, modes)
    
    def run(self):
        (opcode, modes) = self.parseInstruction(self.program[self.pointer])
        val = -1
        while val < 0:
            (opcode, modes) = self.parseInstruction(self.program[self.pointer])
            val = self.executeInstruction(opcode, modes)
        return val

    def getOutput(self):
        return self.output

    def executeInstruction(self, opcode, modes):
        if opcode == 1: # add
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.program[self.program[self.pointer+3]] = arg1+arg2
            self.pointer += 4
        elif opcode == 2: # multiply
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.program[self.program[self.pointer+3]] = arg1*arg2
            self.pointer += 4
        elif opcode == 3: # input
            if not len(self.input):
               return 2 
            self.program[self.program[self.pointer+1]] = self.input[0]
            self.input.pop(0)
            self.pointer += 2
        elif opcode == 4: # output
            self.output = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            self.pointer += 2
        elif opcode == 5: # jump if true
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.pointer = arg2 if arg1 else self.pointer+3
        elif opcode == 6: # jump if false
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.pointer = arg2 if not arg1 else self.pointer+3
        elif opcode == 7: # less than
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.program[self.program[self.pointer+3]] = 1 if arg1 < arg2 else 0
            self.pointer += 4
        elif opcode == 8: # equals
            arg1 = self.program[self.pointer+1] if modes[0] else self.program[self.program[self.pointer+1]]
            arg2 = self.program[self.pointer+2] if modes[1] else self.program[self.program[self.pointer+2]]
            self.program[self.program[self.pointer+3]] = 1 if arg1 == arg2 else 0
            self.pointer += 4
        elif opcode == 99: # terminate
            self.has_terminated = 1
            return 0
        else:
            return 2
        return -1
            
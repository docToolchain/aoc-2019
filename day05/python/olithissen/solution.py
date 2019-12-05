#!/usr/bin/env python3

import os

print(os.getcwd())

class OpCodeParameterRetriever:
    @staticmethod
    def read(memory, pointer, numberOfParameters, noOutput = False):
        opcode = memory[pointer] % 100
        parameterModeNum = memory[pointer] // 100
        parameterModes = [int(d) for d in str(parameterModeNum)]
        parameterModes.reverse()
        parameters = []
        for index in range(0, numberOfParameters):
            parameterMode = 0
            if (parameterModes):
                try:
                    parameterMode = parameterModes[index]
                except IndexError:
                    parameterMode = 0

            if (parameterMode == 0 and index < numberOfParameters - 1):
                # Immediate mode
                parameters.append(memory[memory[pointer + index + 1]])
            else:
                # Address mode
                parameters.append(memory[pointer + index + 1])

        return parameters

class OpCodeAdd:
    def run(self, memory, pointer):
        numberOfParameters = 3
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        result = parameters[0] + parameters[1]
        memory[parameters[2]] = result

        print ("ADD", parameters[0], parameters[1], "=", result, "-> ADDR", parameters[2])

        return (pointer + numberOfParameters + 1, True)

class OpCodeMultiply:
    def run(self, memory, pointer):
        numberOfParameters = 3
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        result = parameters[0] * parameters[1]
        memory[parameters[2]] = result

        print ("MUL", parameters[0], parameters[1], "=", result, "-> ADDR", parameters[2])

        return (pointer + numberOfParameters + 1, True)

class OpCodeInput:
    def run(self, memory, pointer):
        numberOfParameters = 1
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        print("Program input required: ")
        # input = int(input())
        input = 1
        memory[parameters[0]] = input

        print ("INP", "VALUE", memory[parameters[0]], "-> ADDR", parameters[0])

        return (pointer + numberOfParameters + 1, True)

class OpCodeOutput:
    def run(self, memory, pointer):
        numberOfParameters = 1
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters, noOutput=True)

        print ("OUT", "VALUE", parameters[0])

        return (pointer + numberOfParameters + 1, True)

class OpCodeTerminate:
    def run(self, memory, pointer):
        pointer += 1

        return (pointer + 1, False)

class ControlComputer:
    """The AoC Control Computer"""
    pointer = 0
    noun = 0
    verb = 0
    running = True
    memory = []
    
    opCodeHandlers = {
        1: OpCodeAdd,
        2: OpCodeMultiply,
        3: OpCodeInput,
        4: OpCodeOutput,
        99: OpCodeTerminate,
    }

    def load(self, memory): 
        self.memory = memory

    def loadFile(self, filename :str):
        with open(filename, "r") as input:
            self.memory = [int(numericString) for numericString in input.read().split(",")]

    def input(self, noun :int, verb :int):
        self.noun = noun
        self.verb = verb
        self.memory[1] = noun
        self.memory[2] = verb

    def run(self):
        while self.running:
            result = self.step()
            self.pointer = result[0]
            self.running = result[1]

        return

    def step(self):
        opCode = self.memory[self.pointer] % 100
        handler = self.getOpCodeHandler(opCode)
        result = handler.run(self.memory, self.pointer)
        return result

    def getOpCodeHandler(self, opCode :int):
        return self.opCodeHandlers.get(opCode)()

    def readMemoryAt(self, address):
        return self.memory[address]

def solutionStar1(inputFile):
    x = ControlComputer()
    x.loadFile(inputFile)
    x.run()
    return x.readMemoryAt(0)

# sample1ResultStar1 = solutionStar1("sample.txt")
# print("Sample1 result *1: ", sample1ResultStar1)
# assert sample1ResultStar1 == 3500

# sample2ResultStar1 = solutionStar1("sample2.txt")
# print("Sample2 result *1: ", sample2ResultStar1)
# assert sample2ResultStar1 == 1002

# sample3ResultStar1 = solutionStar1("sample3.txt")
# print("Sample3 result *1: ", sample3ResultStar1)
# assert sample3ResultStar1 == 1101

x = ControlComputer()
x.loadFile("input.txt")
x.run()

#realResultStar1 = solutionStar1("input.txt", 12, 2)
#realResultStar1 = solutionStar1("sample3.txt", 100, -1)
# sampleResultStar2 = solutionStar2("sample.txt", 3500)
#realResultStar2 = solutionStar2("input.txt", 19690720)


#print("Real result *1: ", realResultStar1)
#assert realResultStar1 == 4576384

# print("Sample result *2: ", sampleResultStar2)
# assert sampleResultStar2 == 910

#print("Real result *2: ", realResultStar2)
#assert realResultStar2 == 5398

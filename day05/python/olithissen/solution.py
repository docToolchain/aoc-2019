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

            if (parameterMode == 0):
                # Address mode
                parameters.append({"mode": "address", "addr": memory[pointer + index + 1], "valueAt": memory[memory[pointer + index + 1]]})
            else:
                # Immediate mode
                parameters.append({"mode": "immediate", "addr": pointer + index + 1, "valueAt": memory[pointer + index + 1]})

        return parameters

class AssemblyHelper:
    @staticmethod
    def debug(data):
        pointer = data['pointer']
        opcode = data['opcode']
        opcodeNum = data['opcodeNum']
        
        parameterText = ""

        for parameter in data['parameters']:
            if (parameter['mode'] == "address"):
                parameterText += "@" + str(parameter['addr']) + "(=" + str(parameter['valueAt']) + "), "
            else:
                parameterText += str(parameter['valueAt']) + ", "

        print (f'{pointer:05}', "\t", opcode, "\t", f'{opcodeNum:05}', "\t", parameterText)

class OpCodeAdd:
    def run(self, memory, pointer):
        numberOfParameters = 3

        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        result = parameters[0]['valueAt'] + parameters[1]['valueAt']
        memory[parameters[2]['addr']] = result

        AssemblyHelper.debug({"pointer": pointer, "opcode": "ADD",  "opcodeNum": memory[pointer], "parameters": parameters})

        return (pointer + numberOfParameters + 1, True)

class OpCodeMultiply:
    def run(self, memory, pointer):
        numberOfParameters = 3
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        result = parameters[0]['valueAt'] * parameters[1]['valueAt']
        memory[parameters[2]['addr']] = result

        AssemblyHelper.debug({"pointer": pointer, "opcode": "MUL",  "opcodeNum": memory[pointer], "parameters": parameters})

        return (pointer + numberOfParameters + 1, True)

class OpCodeJumpIfTrue:
    def run(self, memory, pointer):
        numberOfParameters = 2
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        
        AssemblyHelper.debug({"pointer": pointer, "opcode": "JE",  "opcodeNum": memory[pointer], "parameters": parameters})

        if (parameters[0]['valueAt'] != 0):
            return (parameters[1]['valueAt'], True)

        return (pointer + numberOfParameters + 1, True)

class OpCodeJumpIfFalse:
    def run(self, memory, pointer):
        numberOfParameters = 2
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)

        AssemblyHelper.debug({"pointer": pointer, "opcode": "JNE",  "opcodeNum": memory[pointer], "parameters": parameters})

        if (parameters[0]['valueAt'] == 0):
            return (parameters[1]['valueAt'], True)

        return (pointer + numberOfParameters + 1, True)

class OpCodeLessThan:
    def run(self, memory, pointer):
        numberOfParameters = 3
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        AssemblyHelper.debug({"pointer": pointer, "opcode": "LT",  "opcodeNum": memory[pointer], "parameters": parameters})

        if (parameters[0]['valueAt'] < parameters[1]['valueAt']):
            memory[parameters[2]['addr']] = 1
        else:
            memory[parameters[2]['addr']] = 0

        return (pointer + numberOfParameters + 1, True)

class OpCodeEquals:
    def run(self, memory, pointer):
        numberOfParameters = 3
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        AssemblyHelper.debug({"pointer": pointer, "opcode": "EQ",  "opcodeNum": memory[pointer], "parameters": parameters})

        if (parameters[0]['valueAt'] == parameters[1]['valueAt']):
            memory[parameters[2]['addr']] = 1
        else:
            memory[parameters[2]['addr']] = 0

        return (pointer + numberOfParameters + 1, True)

class OpCodeInput:
    def run(self, memory, pointer):
        numberOfParameters = 1
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters)
        print("Program input required: ")
        inp = int(input())
        #inp = 8
        memory[parameters[0]['addr']] = inp

        AssemblyHelper.debug({"pointer": pointer, "opcode": "INP",  "opcodeNum": memory[pointer], "parameters": parameters})

        return (pointer + numberOfParameters + 1, True)

class OpCodeOutput:
    def run(self, memory, pointer):
        numberOfParameters = 1
        parameters = OpCodeParameterRetriever.read(memory, pointer, numberOfParameters, noOutput=True)

        AssemblyHelper.debug({"pointer": pointer, "opcode": "OUT",  "opcodeNum": memory[pointer], "parameters": parameters})

        return (pointer + numberOfParameters + 1, True)

class OpCodeTerminate:
    def run(self, memory, pointer):
        pointer += 1

        AssemblyHelper.debug({"pointer": pointer, "opcode": "EXT",  "opcodeNum": memory[pointer], "parameters": []})

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
        5: OpCodeJumpIfTrue,
        6: OpCodeJumpIfFalse,
        7: OpCodeLessThan,
        8: OpCodeEquals,
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

x = ControlComputer()
x.loadFile("input.txt")
x.run()
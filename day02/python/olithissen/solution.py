#!/usr/bin/env python3

def solutionStar1(inputFile, noun, verb):
    with open(inputFile, "r") as input:
        memory = [int(numericString) for numericString in input.read().split(",")]
        memory[1] = noun
        memory[2] = verb
        opcode = 0
        instructionPointer = 0

        while True:
            opcode = memory[instructionPointer]
            
            if (opcode == 99):
                break

            parameter1 = memory[instructionPointer + 1]
            parameter2 = memory[instructionPointer + 2]
            parameter3 = memory[instructionPointer + 3]

            if (opcode == 1):
               memory[parameter3] = memory[parameter1] + memory[parameter2]

            if (opcode == 2):
               memory[parameter3] = memory[parameter1] * memory[parameter2]

            instructionPointer += 4

        return memory[0]

def solutionStar2(inputFile, expected):
    for noun in range (0, 99):
        for verb in range (0, 99):
            result = solutionStar1(inputFile, noun, verb)
            if (result == expected):
                return noun * 100 + verb               

sampleResultStar1 = solutionStar1("sample.txt", 9, 10)
realResultStar1 = solutionStar1("input.txt", 12, 2)
# sampleResultStar2 = solutionStar2("sample.txt", 3500)
realResultStar2 = solutionStar2("input.txt", 19690720)

print("Sample result *1: ", sampleResultStar1)
assert sampleResultStar1 == 3500

print("Real result *1: ", realResultStar1)
assert realResultStar1 == 4576384

# print("Sample result *2: ", sampleResultStar2)
# assert sampleResultStar2 == 910

print("Real result *2: ", realResultStar2)
assert realResultStar2 == 5398

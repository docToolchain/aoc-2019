#!/usr/bin/env groovy

def testInput = "1,9,10,3,2,3,11,0,99,30,40,50"

// tag::parseInput[]
def parseInput(def input) {
    return input.split(',').collect(Long.&parseLong)
}
// end::parseInput[]

assert parseInput(testInput) == [1,9,10,3,2,3,11,0,99,30,40,50]

// tag::computeOutput[]

int getIndex(def input, int parameterMode, int relativeBase, int index) {
    if (parameterMode == 0) {
        index = input[index]
    } else if (parameterMode == 2) {
        index = input[index] + relativeBase
    }
    return index
}

def getValue(def input, int parameterMode, int relativeBase, int index) {
    return input[getIndex(input, parameterMode, relativeBase, index)] ?: 0
}
def setValue(def input, int parameterMode, int relativeBase, int index, long value) {
    assert parameterMode != 1
    input[getIndex(input, parameterMode, relativeBase, index)] = value
}

def computeOutput(def input, long systemId = 1) {
    int relativeBase = 0
    def output = []
    for (int i = 0; i < input.size();) {
        long operator = input[i++]
        int firstParameterMode = (int)(operator / 100) % 10
        int secondParameterMode = (int)(operator / 1000) % 10
        int thirdParameterMode = (int)(operator / 10000) % 10
        int opcode = operator % 100
        if (opcode == 1) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            setValue(input, thirdParameterMode, relativeBase, i++, a + b)
        } else if (opcode == 2) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            setValue(input, thirdParameterMode, relativeBase, i++, a * b)
        } else if (opcode == 3) {
            setValue(input, firstParameterMode, relativeBase, i++, systemId)
        } else if (opcode == 4) {
            output << getValue(input, firstParameterMode, relativeBase, i++)
        } else if (opcode == 5) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            if (a != 0) {
                i = b
            }
        } else if (opcode == 6) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            if (a == 0) {
                i = b
            }
        } else if (opcode == 7) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            setValue(input, thirdParameterMode, relativeBase, i++, a < b ? 1 : 0)
        } else if (opcode == 8) {
            long a = getValue(input, firstParameterMode, relativeBase, i++)
            long b = getValue(input, secondParameterMode, relativeBase, i++)
            setValue(input, thirdParameterMode, relativeBase, i++, a == b ? 1 : 0)
        } else if (opcode == 9) {
            relativeBase += getValue(input, firstParameterMode, relativeBase, i++)
        }
        else if (opcode == 99) {
            break
        }
        else {
            throw new IllegalArgumentException("$i: $opcode")
        }
    }
    return output
}
// end::computeOutput[]

assert computeOutput([109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]) == [109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]
assert computeOutput([1102,34915192,34915192,7,4,7,99,0]).first().toString().length() == 16
assert computeOutput([104,1125899906842624,99]) == [1125899906842624]

// tag::starOne[]
def input = parseInput(new File('input.txt').getText('UTF-8').trim())
println computeOutput(new ArrayList(input))
// end::starOne[]

// tag::starTwo[]
println computeOutput(new ArrayList(input), 2)
// end::starTwo[]

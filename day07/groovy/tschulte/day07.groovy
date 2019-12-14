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
    return input[getIndex(input, parameterMode, relativeBase, index)] ?: 0L
}
def setValue(def input, int parameterMode, int relativeBase, int index, long value) {
    assert parameterMode != 1
    input[getIndex(input, parameterMode, relativeBase, index)] = value
}

def computeOutput(def input, def io = []) {
    int relativeBase = 0
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
            setValue(input, firstParameterMode, relativeBase, i++, io.pop())
        } else if (opcode == 4) {
            io << getValue(input, firstParameterMode, relativeBase, i++)
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
    return io
}
// end::computeOutput[]

assert computeOutput([109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]) == [109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]
assert computeOutput([1102,34915192,34915192,7,4,7,99,0]).first().toString().length() == 16
assert computeOutput([104,1125899906842624,99]) == [1125899906842624]

// tag::maxThrusterSignal[]
def maxThrusterSignal(def input) {
    return (0..4).permutations { phaseSettings ->
        phaseSettings.inject(0L) { output, phaseSetting ->
            return computeOutput(new ArrayList(input), [phaseSetting, output]).first()
        }
    }.max()
}
// end::maxThrusterSignal[]

assert maxThrusterSignal([3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0]) == 43210
assert maxThrusterSignal([3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0]) == 54321
assert maxThrusterSignal([3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0]) == 65210

def input = parseInput(new File('input.txt').getText('UTF-8').trim())
// tag::starOne[]
println maxThrusterSignal(input)
// end::starOne[]

// tag::maxThrusterSignalFeedbackLoop[]
def maxThrusterSignalFeedbackLoop(def input) {
    return (5..9).permutations { phaseSettings ->
        phaseSettings.collect { phaseSetting ->
            [phaseSetting: phaseSetting, input: new ArrayList(input)]
        }
    }.collect { permutation ->
        def totalOutput = []
        def lastOutput = [0L]
        while (!lastOutput.isEmpty()) {
            lastOutput = permutation.inject(lastOutput) { output, entry ->
                def io = [entry.phaseSetting, output].flatten().findAll { it != null }
                //entry.phaseSetting = null
                return computeOutput(entry.input, io)
            }
            if (!lastOutput.isEmpty()) {
                totalOutput = lastOutput
            }
        }
        return totalOutput.first()
    }.max()
}
// end::maxThrusterSignalFeedbackLoop[]

assert maxThrusterSignalFeedbackLoop([3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5]) == 139629729
assert maxThrusterSignalFeedbackLoop([3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10]) == 18216

// tag::starTwo[]
println maxThrusterSignalFeedbackLoop(input)
// end::starTwo[]

def testInput = "1,9,10,3,2,3,11,0,99,30,40,50"

// tag::parseInput[]
def parseInput(def input) {
    return input.split(',').collect(Integer.&parseInt)
}
// end::parseInput[]

assert parseInput(testInput) == [1,9,10,3,2,3,11,0,99,30,40,50]

// tag::computeOutput[]
def computeOutput(def input, int systemId = 1) {
    for (int i = 0; i < input.size();) {
        int operator = input[i++]
        int firstParameterMode = (int)(operator / 100) % 10
        int secondParameterMode = (int)(operator / 1000) % 10
        int thirdParameterMode = (int)(operator / 10000) % 10
        int opcode = operator % 100
        if (opcode == 99) {
            break
        }
        if (opcode == 1) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            input[input[i++]] = a + b
        } else if (opcode == 2) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            input[input[i++]] = a * b
        } else if (opcode == 3) {
            input[input[i++]] = systemId
        } else if (opcode == 4) {
            println(firstParameterMode == 0 ? input[input[i++]] : input[i++])
        } else if (opcode == 5) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            if (a != 0) {
                i = b
            }
        } else if (opcode == 6) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            if (a == 0) {
                i = b
            }
        } else if (opcode == 7) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            input[input[i++]] = a < b ? 1 : 0
        } else if (opcode == 8) {
            int a = firstParameterMode == 0 ? input[input[i++]] : input[i++]
            int b = secondParameterMode == 0 ? input[input[i++]] : input[i++]
            input[input[i++]] = a == b ? 1 : 0
        }
        else {
            throw new IllegalArgumentException("$i")
        }
    }
    return input[0]
}
// end::computeOutput[]

assert computeOutput([1,9,10,3,2,3,11,0,99,30,40,50]) == 3500

// tag::starOne[]
def input = parseInput(new File('input.txt').getText('UTF-8').trim())
computeOutput(new ArrayList(input), 1)
// end::starOne[]

// tag::starTwo[]
computeOutput(new ArrayList(input), 5)
// end::starTwo[]

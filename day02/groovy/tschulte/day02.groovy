def testInput = "1,9,10,3,2,3,11,0,99,30,40,50"

// tag::parseInput[]
def parseInput(def input) {
    return input.split(',').collect(Integer.&parseInt)
}
// end::parseInput[]

assert parseInput(testInput) == [1,9,10,3,2,3,11,0,99,30,40,50]

// tag::computeOutput[]
def computeOutput(def input) {
    for (int i = 0; i < input.size();) {
        int operator = input[i++]
        if (operator == 99) {
            break
        }
        int a = input[input[i++]]
        int b = input[input[i++]]
        if (operator == 1) {
            input[input[i++]] = a + b
        }
        else if (operator == 2) {
            input[input[i++]] = a * b
        }
        else {
            throw new IllegalArgumentException("$i")
        }
    }
    return input[0]
}
// end::computeOutput[]

assert computeOutput([1,9,10,3,2,3,11,0,99,30,40,50]) == 3500

// tag::restoreState[]
def restoreState(def input, def noun, def verb) {
    input[1] = noun
    input[2] = verb
    return input
}
// end::restoreState[]

// tag::starOne[]
def input = parseInput(new File('input.txt').getText('UTF-8').trim())
println computeOutput(restoreState(new ArrayList(input), 12, 2))
// end::starOne[]

// tag::bruteForce[]
def bruteForce(def input) {
    for (int noun = 0; noun <= 100; noun++) {
        for (int verb = 0; verb <= 100; verb++) {
            if (computeOutput(restoreState(new ArrayList(input), noun, verb)) == 19690720) {
                println (100 * noun + verb)
                return
            }
        }
    }
}
// end::bruteForce[]

bruteForce(input)

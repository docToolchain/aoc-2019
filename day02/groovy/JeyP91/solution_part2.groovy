#!/usr/bin/env groovy

// Read input
ArrayList<String> opcodeAsString = Arrays.asList(new File('input.txt').text.split("\\s*,\\s*"))

// Convert string array to text
List<Integer> opcode = new ArrayList<Integer>()
for(String s : opcodeAsString) opcode.add(Integer.valueOf(s));

// Try out every combination
// tag::tryOut[]
for (int noun = 0; noun <= 99; noun++) {
    for (int verb = 0; verb <= 99; verb++) {
        List<Integer> tempOpcode = opcode.collect()
        tempOpcode[1] = noun
        tempOpcode[2] = verb
        int result = executeOpcode(tempOpcode)
        if(result == 19690720) {
            println "$noun$verb"
        }
    }
}
// end::tryOut[]

// Iterate over complete program and execute operations
private int executeOpcode(ArrayList<Integer> opcode) {
    for(int i = 0; i < opcode.size; i = i+4) {
        switch (opcode[i]) {
            case 1:
                opcode[opcode[i+3]] = opcode[opcode[i+1]] + opcode[opcode[i+2]]
                continue
            case 2:
                opcode[opcode[i+3]] = opcode[opcode[i+1]] * opcode[opcode[i+2]]
                continue
            case 99:
                break
            default:
                continue
        }
        break
    }
    return opcode[0]
}
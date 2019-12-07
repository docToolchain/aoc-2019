#!/usr/bin/env groovy

// Read input
ArrayList<String> opcodeAsString = Arrays.asList(new File('input.txt').text.split("\\s*,\\s*"))

// Convert string array to text
List<Integer> opcode = new ArrayList<Integer>()
for(String s : opcodeAsString) opcode.add(Integer.valueOf(s));

// Iterate over complete program and execute operations
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

// Print only the interesting output at index 0
println opcode[0]
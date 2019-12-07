#!/usr/bin/env groovy

// Read input
ArrayList<String> instructionsAsString = Arrays.asList(new File('input.txt').text.split("\\s*,\\s*"))
// ArrayList<String> instructionsAsString = Arrays.asList(new File('input_test.txt').text.split("\\s*,\\s*"))

// Convert string array to text
List<Integer> instructions = new ArrayList<Integer>()
for(String s : instructionsAsString) instructions.add(Integer.valueOf(s));

// Iterate over complete program and execute operations
int i = 0
while (i < instructions.size()) {
    Instruction inst = new Instruction(instructions[i])
    switch (inst.getOpcode()) {
        // Addition
        case 1:
            // Calculation values and location based on opcode-parameters
            int value1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int value2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]
            int resultLocation = instructions[i+3]

            // Calculate addition
            instructions[resultLocation] = value1 + value2

            // Jump to next instruction
            i = i + 4
            continue

        // Multiplication
        case 2:
            // Calculation values and location based on opcode-parameters
            int value1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int value2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]
            int resultLocation = instructions[i+3]

            // Calculate multiplication
            instructions[resultLocation] = value1 * value2

            // Jump to next instruction
            i = i + 4
            continue

        // Input
        case 3:
            // Read input and write to location
            print "Input: "
            int input = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())
            instructions[instructions[i+1]] = input

            // Jump to next instruction
            i = i + 2
            continue

        // Output
        case 4:
            // Calculation output source based on opcode-parameters
            int output = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            println "Output: $output"

            // Jump to next instruction
            i = i + 2
            continue

        // jump-if-true
        case 5:
            // Calculation parameters based on opcode-parameters
            int par1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int par2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]
            
            // Evaluate jump to next instruction based on true parameter
            if(par1 != 0) {
                i = par2
            }
            else {
                i = i + 3
            }
            continue

        // jump-if-false
        case 6:
            // Calculation parameters based on opcode-parameters
            int par1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int par2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]

            // Evaluate jump to next instruction based on false parameter
            if(par1 == 0) {
                i = par2
            }
            else {
                i = i + 3
            }
            continue

        // less-then
        case 7:
            // Calculation parameters based on opcode-parameters
            // tag::lessThen1[]
            int par1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int par2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]
            int resultLocation = instructions[i+3]
            // end::lessThen1[]

            // Evaluate less-then check and write 1 or 0 to location
            // tag::lessThen2[]
            instructions[resultLocation] = par1 < par2 ? 1 : 0
            // end::lessThen2[]

            // Jump to next instruction
            // tag::lessThen3[]
            i = i + 4
            // end::lessThen3[]
            continue

        // equals
        case 8:
            // Calculation parameters and location based on opcode-parameters
            int par1 = inst.getParameter1() == 0 ? instructions[instructions[i+1]] : instructions[i+1]
            int par2 = inst.getParameter2() == 0 ? instructions[instructions[i+2]] : instructions[i+2]
            int resultLocation = instructions[i+3]
            
            // Evaluate equals check and write 1 or 0 to location
            instructions[resultLocation] = par1 == par2 ? 1 : 0

            // Jump to next instruction
            i = i + 4
            continue

        case 99:
            break

        // Error Case
        // Just print some helpful debug information.
        default:
            println "Current location: $i"
            println "Opcode: ${inst.getOpcode()}"
            println instructions[i] + "," + instructions[i+1] + "," + instructions[i+2] + "," + instructions[i+3]
            throw new Exception()
    }
    break
}

// Converts instruction number into opcode and up to 3 parameters
class Instruction {
    private int opcode
    private int parameter1
    private int parameter2
    private int parameter3

    public Instruction(int instruction) {
        // tag::opcodeParameters[]
        this.opcode = Math.floor((instruction % 100))
        this.parameter1 = Math.floor((instruction % 1000) / 100)
        this.parameter2 = Math.floor((instruction % 10000) / 1000)
        this.parameter3 = Math.floor(instruction / 10000)
        // end::opcodeParameters[]
    }

    public int getOpcode() {return this.opcode}
    public int getParameter1() {return this.parameter1}
    public int getParameter2() {return this.parameter2}
    public int getParameter3() {return this.parameter3}
}
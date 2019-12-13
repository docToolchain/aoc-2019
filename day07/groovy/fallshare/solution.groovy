class Computer {

    Integer[] code
    def  input
    Integer output

    Computer(String filePath){
        input = []
        code = readFile(filePath)
    }

    void addInput(Integer input){
        this.input += input
        println "input: " + this.input
    }

    // tag::readFile[]
    Integer[] readFile(String filePath) {
        File file = new File(filePath)
        String fileContent = file.text
        def contentArray = fileContent.tokenize(',')*.toInteger()
        return contentArray
    }
    // end::readFile[]

    Integer[] splitNumberInDigits(int number){
        def numberOfDigits = String.valueOf(number).length()

        def digits =  new Integer[numberOfDigits]
        def intermediate = number
        for(int j = (numberOfDigits - 1); j >= 0; j--){
            def curDigit = intermediate % 10
            intermediate = (intermediate / 10).trunc().intValue() 
            digits[j] = curDigit
        }
        return digits
    }

    //takes an instruciton and returns the opcde that it contains
    Integer getOpcode(int[] instruction){
        int opcode = instruction[instruction.size() - 1] 
        if(instruction.size() > 1)
        {
            opcode += (10 * instruction[instruction.size() - 2])
        }
        return opcode
    }

    Integer[] getParameterModes(int[] instruction){
        int[] parameterModes = [0,0,0]
        int parameterCounter = instruction.size() - 2

        if(parameterCounter > 3){
            throw new Exception("Invalid number of parameter modes found")
        }
        int i = 0
        while(i < parameterCounter){
            parameterModes[i] = instruction[(instruction.size() - 1 - 2) - i]
            i++        
        }
        return parameterModes
    }

    // tag::processor[]
    Integer processCode()
    {
        int ip = 0;
        //TODO turn into while loop
        while (ip < code.length) {

            def instruction = splitNumberInDigits(code[ip])

            int opcode = getOpcode(instruction)
            
            //if we reach the end of the program we stop instead of reading possible parameters
            if (opcode == 99) {
                println "End of program"
                break
            }

            int[] paraModes = getParameterModes(instruction)
            int[] operands = [0,0,0]
            //with this implemntation we always read in the next 3 values after an opcode as parameter
            //even if those values are actually not parameters
            //we could just check how many parameters the opcode has but then I'm scattering information about the operatoins in several places
            //now it becomes handy to use a obejct oriented design.
            //due to time constrains i keep the things as they are for the time being
            for(int i = 0; i <= 2; i++){
                if(paraModes[i] == 0){
                    //position mode
                    operands[i] = code[ip + (i + 1)]
                }else if(paraModes[i] == 1){
                    //immediate mode
                    operands[i] = ip + (i + 1)
                } else {
                    throw new Exception("Invalid number of parameter mode values found")
                }
            }       

            if ( opcode == 1 ) {
                // sum
                println "[${operands[2]}] = [${operands[0]}]: ${code[operands[0]]} + [${operands[1]}]: ${code[operands[1]]}"
                code[operands[2]] = code[operands[0]] + code[operands[1]]            
                ip += 4
            } else if (opcode == 2) {
                //multiply
                println "[${operands[2]}] = [${operands[0]}]: ${code[operands[0]]} * [${operands[1]}]: ${code[operands[1]]}"
                code[operands[2]] = code[operands[0]] * code[operands[1]]            
                ip += 4
            } else if (opcode == 3) {
                //read value
                code[operands[0]] = input.remove(0)
                println "[${operands[0]}] << " + code[operands[0]]
                ip += 2
            } else if (opcode == 4) {
                //print value
                output = code[operands[0]]
                println "[${operands[0]}] >> " + code[operands[0]]
                ip += 2
            } else if (opcode == 5) {
                //jump if true
                println "jump to [${code[operands[1]]}] if [${operands[0]}]:${code[operands[0]]} == 1"
                if(code[operands[0]] != 0){
                    ip = code[operands[1]]
                } else {
                    ip += 3
                }
            } else if (opcode == 6) {
                //jump if false
                println "jump to [${code[operands[1]]}] if [${operands[0]}]:${code[operands[0]]} == 0"
                if(code[operands[0]] == 0){
                    ip = code[operands[1]]
                } else {
                    ip += 3
                }
            } else if (opcode == 7) {
                // less than
                println "[${code[operands[2]]}] is 1 if [${operands[0]}]:${code[operands[0]]} < [${operands[1]}]:${code[operands[1]]} else 0"
                if(code[operands[0]] < code[operands[1]]){
                    code[operands[2]] = 1
                } else {
                    code[operands[2]] = 0
                }
                ip += 4
            } else if (opcode == 8) {    
                //equals
                println "[${code[operands[2]]}] is 1 if [${operands[0]}]:${code[operands[0]]} == [${operands[1]}]:${code[operands[1]]} else 0"
                if(code[operands[0]] == code[operands[1]]){
                    code[operands[2]] = 1
                } else {
                    code[operands[2]] = 0
                }
                ip += 4
            } else {
                println "Opcode: ${opcode} - ip: ${ip}"
                throw new Exception("Invalid opcode found!")
            }    
        }
        return output
    }
    // end::processor[]
}


// tag::start1[]

int maxOutput = 0
int[] maxOutoutPhaseSetting = []

[0,1,2,3,4].eachPermutation{ phaseSetting ->

    amp1 = new Computer("input.txt")
    //add phase setting as first input
    amp1.addInput(phaseSetting[0])
    //add input as second input
    amp1.addInput(0)
    outAmp1 = amp1.processCode()

    println "Amp1 returns: " + outAmp1

    amp2 = new Computer("input.txt")
    //add phase setting as first input
    amp2.addInput(phaseSetting[1])
    //add input as second input
    amp2.addInput(outAmp1)
    outAmp2 = amp2.processCode()

    println "Amp2 returns: " + outAmp2

    amp3 = new Computer("input.txt")
    //add phase setting as first input
    amp3.addInput(phaseSetting[2])
    //add input as second input
    amp3.addInput(outAmp2)
    outAmp3 = amp3.processCode()

    println "Amp3 returns: " + outAmp3


    amp4 = new Computer("input.txt")
    //add phase setting as first input
    amp4.addInput(phaseSetting[3])
    //add input as second input
    amp4.addInput(outAmp3)
    outAmp4 = amp4.processCode()

    println "Amp4 returns: " + outAmp4


    amp5 = new Computer("input.txt")
    //add phase setting as first input
    amp5.addInput(phaseSetting[4])
    //add input as second input
    amp5.addInput(outAmp4)
    outAmp5 = amp5.processCode()

    println "Amp5 returns: " + outAmp5

    if(maxOutput < outAmp5){
        maxOutput = outAmp5
        maxOutoutPhaseSetting = phaseSetting
    }

}

println "Max output: " + maxOutput
println "Max output phase setting: " + maxOutoutPhaseSetting

// end::start1[]

// tag::star2[]
input = 5
memory = readFile("input.txt")
processCode(memory, input)
println "Star 2 done"
// end::star2[]
*/
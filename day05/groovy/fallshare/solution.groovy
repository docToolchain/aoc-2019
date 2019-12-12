// tag::readFile[]
Integer[] readFile(String filePath) {
    File file = new File(filePath)
    String fileContent = file.text
    contentArray = fileContent.tokenize(',')*.toInteger()
    return contentArray
}
// end::readFile[]

Integer[] splitNumberInDigits(int number){
    numberOfDigits = String.valueOf(number).length()

    digits =  new Integer[numberOfDigits]
    intermediate = number
    for(int j = (numberOfDigits - 1); j >= 0; j--){
        curDigit = intermediate % 10
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
void processCode(code, input)
{
    int ip = 0;
    //TODO turn into while loop
    while (ip < code.length) {

        instruction = splitNumberInDigits(code[ip])

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
            code[operands[0]] = input
            println "[${operands[0]}] << " + code[operands[0]]
            ip += 2
        } else if (opcode == 4) {
            //print value
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
}
// end::processor[]

// tag::start1[]
memory = readFile("input.txt")
//the input could be read in using readline
// but then the script can't be executed without know the "magic" number hence I'm hardcoding it
int input = 1
//processCode(memory, input)
println "Star 1 done"
// end::start1[]

// tag::star2[]
input = 5
memory = readFile("input.txt")
processCode(memory, input)
println "Star 2 done"
// end::star2[]
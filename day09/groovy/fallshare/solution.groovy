class Computer {

    Boolean isActive
    BigInteger[] code
    def  input
    Integer output
    int ip;
    int rb;

    //this map stores how many paramter a certrain operator has
    def numberOfParameters = [
        1 : 3,
        2 : 3,
        3 : 1,
        4 : 1,
        5 : 2,
        6 : 2,
        7 : 3,
        8 : 3,
        9 : 1
    ]

    Computer(String filePath){
        isActive = true
        input = []
        code = readFile(filePath)
        def iniSize = code.size()
        code += new BigInteger[100000]
        //initalize rest of code with 0, didn't find a better way yet
        for(int i = iniSize; i < iniSize + 100000; i++){
            code[i] = 0
        }
     
        ip = 0;
        rb = 0;
    }

    Boolean isActive(){
        this.isActive
    }

    Integer processInput(Integer input){
        this.input += input
        return this.processCode()
    }

    void addInput(Integer input){
        this.input += input
    }

    // tag::readFile[]
    BigInteger[] readFile(String filePath) {
        File file = new File(filePath)
        String fileContent = file.text
        def contentArray = fileContent.tokenize(',')*.toBigInteger()
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
        output = null
        //TODO turn into while loop
        while (ip < code.length) {

            def instruction = splitNumberInDigits(code[ip])

            int opcode = getOpcode(instruction)
            println opcode
            //if we reach the end of the program we stop instead of reading possible parameters
            if (opcode == 99) {
                println "End of program"
                isActive = false
                break
            }

            int[] paraModes = getParameterModes(instruction)
            int[] operands = [0,0,0]
         
            //currently the operator and the information how many parameter it has is decoupled (defined in the beginning of this class)
            //a OO desgin would help here to keep information together.
            //due to time reason I keep things as they are right now.
            for(int i = 0; i <= (numberOfParameters[opcode] - 1); i++){
                if(paraModes[i] == 0){
                    //position mode
                    operands[i] = code[ip + (i + 1)]
                }else if(paraModes[i] == 1){
                    //immediate mode
                    operands[i] = ip + (i + 1)
                }else if(paraModes[i] == 2){
                    //relative base mode
                    operands[i] = code[ip + (i + 1)] + rb
                    println "relative mode: ${ip + (i + 1) + rb} - $ip + ($i + 1) + $rb"
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
                println "input: " + input
                if(input.isEmpty()){
                    println "Waiting for input"
                    break
                } else {
                    code[operands[0]] = input.remove(0)
                    println "[${operands[0]}] << " + code[operands[0]]
                    ip += 2
                }
            } else if (opcode == 4) {
                //output value
                println "[${operands[0]}] >> " + code[operands[0]] + "                                >> " + code[operands[0]]
                ip += 2
                output = code[operands[0]]
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
             } else if (opcode == 9) {
                //set relative base
                println "RB = ${code[operands[0]]}"
                this.rb += code[operands[0]]
                ip += 2
            } else {
                println "Opcode: ${opcode} - ip: ${ip}"
                throw new Exception("Invalid opcode found!")
            }    
        }
        return output
    }
    // end::processor[]
}

computer = new Computer("input.txt")
computer.processInput(1)

computer2 = new Computer("input.txt")
computer2.processInput(2)
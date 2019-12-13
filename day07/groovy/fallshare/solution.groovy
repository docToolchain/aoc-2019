class Computer {

    Boolean isActive
    Integer[] code
    def  input
    Integer output
    int ip;

    //this map stores how many paramter a certrain operator has
    def numberOfParameters = [
        1 : 3,
        2 : 3,
        3 : 1,
        4 : 1,
        5 : 2,
        6 : 2,
        7 : 3,
        8 : 3
    ]

    Computer(String filePath){
        isActive = true
        input = []
        code = readFile(filePath)
        ip = 0;
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
                println "[${operands[0]}] >> " + code[operands[0]]
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
            } else {
                println "Opcode: ${opcode} - ip: ${ip}"
                throw new Exception("Invalid opcode found!")
            }    
        }
        return output
    }
    // end::processor[]
}


// tag::star1[]

int maxOutput = 0
int[] maxOutoutPhaseSetting = []

[0,1,2,3,4].eachPermutation{ phaseSetting ->

    amp1 = new Computer("input.txt")
    //add phase setting as first input
    amp1.processInput(phaseSetting[0])
    //add input as second input
    outAmp1 = amp1.processInput(0)
    println "Amp1 returns: " + outAmp1

    amp2 = new Computer("input.txt")
    //add phase setting as first input
    amp2.processInput(phaseSetting[1])
    //add input as second input
    outAmp2 =  amp2.processInput(outAmp1)

    println "Amp2 returns: " + outAmp2

    amp3 = new Computer("input.txt")
    //add phase setting as first input
    amp3.processInput(phaseSetting[2])
    //add input as second input
    outAmp3 =  amp3.processInput(outAmp2)

    println "Amp3 returns: " + outAmp3


    amp4 = new Computer("input.txt")
    //add phase setting as first input
    amp4.processInput(phaseSetting[3])
    //add input as second input
    outAmp4 = amp4.processInput(outAmp3)
   

    println "Amp4 returns: " + outAmp4


    amp5 = new Computer("input.txt")
    //add phase setting as first input
    amp5.processInput(phaseSetting[4])
    //add input as second input
    outAmp5 = amp5.processInput(outAmp4)

    println "Amp5 returns: " + outAmp5

    if(maxOutput < outAmp5){
        maxOutput = outAmp5
        maxOutoutPhaseSetting = phaseSetting
    }

}
// end::star1[]

// tag::star2[]

int maxOutputStar2 = 0
int[] maxOutoutPhaseSettingStar2 = []

[5,6,7,8,9].eachPermutation{ phaseSetting ->

    amp1 = new Computer("input.txt")
    amp1.processInput(phaseSetting[0])
    
    amp2 = new Computer("input.txt")
    amp2.processInput(phaseSetting[1])

    amp3 = new Computer("input.txt")
    amp3.processInput(phaseSetting[2])

    amp4 = new Computer("input.txt")
    amp4.processInput(phaseSetting[3])

    amp5 = new Computer("input.txt")
    amp5.processInput(phaseSetting[4])

    int feedBackLoop = 0
    while(amp5.isActive())
    {   
        outAmp1 = amp1.processInput(feedBackLoop)
        outAmp2 = amp2.processInput(outAmp1)
        outAmp3 = amp3.processInput(outAmp2)
        outAmp4 = amp4.processInput(outAmp3)
        feedBackLoop = amp5.processInput(outAmp4)
    }

    if(maxOutputStar2 < feedBackLoop){
        maxOutputStar2 = feedBackLoop
        maxOutoutPhaseSettingStar2 = phaseSetting
    }

}

println "Max output Star 2: " + maxOutputStar2
println "Max output phase setting Star 2: " + maxOutoutPhaseSettingStar2
// end::star2[]


println "Max output: " + maxOutput
println "Max output phase setting: " + maxOutoutPhaseSetting

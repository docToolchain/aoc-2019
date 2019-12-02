// tag::readFile[]
Integer[] readFile(String filePath) {
    File file = new File(filePath)
    String fileContent = file.text
    contentArray = fileContent.tokenize(',')*.toInteger()
    return contentArray
}
// end::readFile[]

// tag::processor[]
Integer processCode(code)
{
    int finalOutput = 0;
    for (i = 0; i < code.length; i += 4) {

        int opcode = code[i]
        int operand1 = code[code[i + 1]]
        int operand2 = code[code[i + 2]]
        int resultAdr = code[i + 3]
        
        if ( opcode == 1 ) {
            code[resultAdr] = operand1 + operand2
        } else if (opcode == 2) {
            code[resultAdr] = operand1 * operand2
        } else if (opcode == 99) {
            finalOutput = code[0]
            break
        } else {
             throw new Exception("Invalid opcode found!")
        }    
    }
    return code[0]
}
// end::processor[]

// tag::start1[]
code = readFile("input.txt")
code[1] = 12
code[2] = 2
println "Star 1 result: " + processCode(code)
// end::start1[]

// tag::star2[]
int finalCode, noun, verb
final int requiredResult = 19690720
for(noun = 0; noun <= 99; noun++){
    for(verb = 0; verb <= 99; verb++){
        code = readFile("input.txt")
        code[1] = noun
        code[2] = verb
        finalCode = processCode(code)
        if(finalCode == requiredResult)
            break
    }
    if(finalCode == requiredResult)
        break
}
println "Star 2:"
println "\tvalue ${finalCode} found with noun: ${noun} and verb: ${verb}"
println "\tresulting key is: " + noun + verb
// end::star2[]
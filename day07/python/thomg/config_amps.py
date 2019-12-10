import intcomp

def quinaryRepAsList(integer):
    quinary = []
    if(integer >= 3125):
        print("error in quinary representation: only numbers up to 3124 are supported (you tried "+str(integer)+")")
    # 5^4
    digit = int(integer/625)
    quinary.append(digit)
    integer -= digit*625 
    # 5^3
    digit = int(integer/125)
    quinary.append(digit)
    integer -= digit*125
    # 5^2
    digit = int(integer/25)
    quinary.append(digit)
    integer -= digit*25 
    # 5^1
    digit = int(integer/5)
    quinary.append(digit)
    integer -= digit*5
    # 5^0 (should just be the rest)
    quinary.append(integer)
    return quinary

maxOutput = 0
for n in range(3125):
    quinary = quinaryRepAsList(n)
    # skip if one phase setting would be used multiple times:
    if len(quinary) > len(set(quinary)):
        continue
    inputA = [quinary[0], 0]
    outputA = intcomp.executeProgramFromFile("program", inputA, 1, 0)[0]
    inputB = [quinary[1], outputA[0]]
    outputB = intcomp.executeProgramFromFile("program", inputB, 1, 0)[0]
    inputC = [quinary[2], outputB[0]]
    outputC = intcomp.executeProgramFromFile("program", inputC, 1, 0)[0]
    inputD = [quinary[3], outputC[0]]
    outputD = intcomp.executeProgramFromFile("program", inputD, 1, 0)[0]
    inputE = [quinary[4], outputD[0]]
    outputE = intcomp.executeProgramFromFile("program", inputE, 1, 0)[0]
    if maxOutput < outputE[0]:
        maxOutput = outputE[0]
print("output for star 1: "+str(maxOutput))

maxOutput = 0
for n in range(3125):
    quinary = quinaryRepAsList(n)
    # skip if one phase setting would be used multiple times:
    if len(quinary) > len(set(quinary)):
        continue
    quinary = [d+5 for d in quinary]
    # initialize all programs with their phase settings
    inputA = [quinary[0], 0]
    (outputA, programA, pointerA) = intcomp.executeProgramFromFile("program", inputA, 0, 1)
    inputB = [quinary[1], outputA[0]]
    (outputB, programB, pointerB) = intcomp.executeProgramFromFile("program", inputB, 0, 1)
    inputC = [quinary[2], outputB[0]]
    (outputC, programC, pointerC) = intcomp.executeProgramFromFile("program", inputC, 0, 1)
    inputD = [quinary[3], outputC[0]]
    (outputD, programD, pointerD) = intcomp.executeProgramFromFile("program", inputD, 0, 1)
    inputE = [quinary[4], outputD[0]]
    (outputE, programE, pointerE) = intcomp.executeProgramFromFile("program", inputE, 0, 1)

    while len(outputE) < 2:
        inputA = [outputE[0]]
        (outputA, programA, pointerA) = intcomp.executeProgram(programA, pointerA, inputA, 0, 1)
        inputB = [outputA[0]]
        (outputB, programB, pointerB) = intcomp.executeProgram(programB, pointerB, inputB, 0, 1)
        inputC = [outputB[0]]
        (outputC, programB, pointerC) = intcomp.executeProgram(programC, pointerC, inputC, 0, 1)
        inputD = [outputC[0]]
        (outputD, programC, pointerD) = intcomp.executeProgram(programD, pointerD, inputD, 0, 1)
        inputE = [outputD[0]]
        (outputE, programD, pointerE) = intcomp.executeProgram(programE, pointerE, inputE, 0, 1)
    
    if maxOutput < outputE[0]:
        maxOutput = outputE[0]
print("output for star 2: "+str(maxOutput))

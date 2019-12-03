with open("program") as file:
    initialProgram = file.readlines()[0]
initialProgram = initialProgram.split(",")

def executeProgram(program):
    pointer = 0
    result = 0
    while 1:
        arg1 = program[pointer+1]
        arg2 = program[pointer+2]

        if program[pointer] == 1:
            result = program[arg1]+program[arg2]
        elif program[pointer] == 2:
            result = program[arg1]*program[arg2]
        elif program[pointer] == 99:
            break
        else:
            print("ERROR: bad opcode ("+str(program[pointer])+") at position: "+str(pointer))

        program[program[pointer+3]] = result
        pointer += 4
    return program[0]

for noun in range(99):
    for verb in range(99):
        program=list(map(int, initialProgram))
        program[1]=noun
        program[2]=verb
        if executeProgram(program) == 19690720:
            print(noun*100+verb)
            break
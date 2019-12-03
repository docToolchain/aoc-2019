with open("program") as file:
    program = file.readlines()[0]

program = program.split(",")
program = list(map(int, program))

initialProgram = program
program[1]=12
program[2]=2

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
            print("ERROR: bad opcode at position: "+str(pointer))

        program[program[pointer+3]] = result
        pointer += 4
    print(program[0])

executeProgram(program)



    

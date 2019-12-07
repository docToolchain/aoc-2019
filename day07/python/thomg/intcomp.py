import sys

def executeProgramFromFile(programfile, input, break_on_output, break_on_input, verbose = 0):
    with open(programfile) as file:
        program = file.readlines()[0]
    program = program.split(",")
    program = list(map(int, program))
    return executeProgram(program, 0, input, break_on_output, break_on_input)


def executeProgram(program, pointer, input, break_on_output, break_on_input, verbose = 0):

    input = list(map(int, input))
    output = []
    while 1:
        instruction = program[pointer]
        opcode = instruction % 100
        instruction -= opcode
        mode1 = (instruction % 1000)   / 100
        instruction -= mode1*100
        mode2 = (instruction % 10000)  / 1000
        instruction -= mode2*1000
        # mode for the third parameter isn't actually needed on day05
        # all opcodes so far HAVE to be in position mode for their third param, if they have one
        # mode3 = (instruction % 100000) / 10000
        
        # add
        if opcode == 1:
            if mode1:
                arg1 = program[pointer+1]
            else:
                address = program[pointer+1]
                arg1 = program[address]
            if mode2:
                arg2 = program[pointer+2]
            else:
                address = program[pointer+2]
                arg2 = program[address]
            address = program[pointer+3]
            program[address] = arg1+arg2
            pointer += 4
        # multiply
        elif opcode == 2:
            if mode1:
                arg1 = program[pointer+1]
            else:
                address = program[pointer+1]
                arg1 = program[address]
            if mode2:
                arg2 = program[pointer+2]
            else:
                address = program[pointer+2]
                arg2 = program[address]
            address = program[pointer+3]
            program[address] = arg1*arg2
            pointer += 4
        # input
        elif opcode == 3:
            if break_on_input and not len(input):
                if verbose:
                    print("break, waiting for next input on position "+str(pointer))
                break
            address = program[pointer+1]
            program[address] = input[0]
            input.pop(0)
            pointer += 2
        # output
        elif opcode == 4:
            if mode1:
                if verbose:
                    print("pointer: {}, output: {}".format(pointer, program[pointer+1]))
                output.append(program[pointer+1])
            else:
                if verbose:
                    print("pointer: {}, output: {}".format(pointer, program[program[pointer+1]]))
                output.append(program[program[pointer+1]])
            if break_on_output:
                if verbose:
                    print("program terminates at position {} on an output instruction".format(pointer))
                break
            pointer += 2
        # jump-if-true
        elif opcode == 5:
            if mode1:
                arg1 = program[pointer+1]
            else:
                arg1 = program[program[pointer+1]]
            if arg1:
                if mode2:
                    pointer = program[pointer+2]
                else:
                    pointer = program[program[pointer+2]]
            else:
                pointer += 3
        # jump-if-false
        elif opcode == 6:
            if mode1:
                arg1 = program[pointer+1]
            else:
                arg1 = program[program[pointer+1]]
            if not arg1:
                if mode2:
                    pointer = program[pointer+2]
                else:
                    pointer = program[program[pointer+2]]
            else:
                pointer += 3
        # less than
        elif opcode == 7:
            if mode1:
                arg1 = program[pointer+1]
            else:
                arg1 = program[program[pointer+1]]
            if mode2:
                arg2 = program[pointer+2]
            else:
                arg2 = program[program[pointer+2]]
            if arg1 < arg2:
                program[program[pointer+3]] = 1
            else:
                program[program[pointer+3]] = 0
            pointer +=4
        # equal
        elif opcode == 8:
            if mode1:
                arg1 = program[pointer+1]
            else:
                arg1 = program[program[pointer+1]]
            if mode2:
                arg2 = program[pointer+2]
            else:
                arg2 = program[program[pointer+2]]
            if arg1 == arg2:
                program[program[pointer+3]] = 1
            else:
                program[program[pointer+3]] = 0
            pointer +=4
        # terminate
        elif opcode == 99:
            output.append(99)
            if verbose:
                print("program terminates at position {}".format(pointer))
            break
        else:
            print("ERROR: bad opcode ("+str(program[pointer])+") at position: "+str(pointer))
    return (output, program, pointer)



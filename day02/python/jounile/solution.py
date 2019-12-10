#!/usr/bin/python

# example inputs
#[1,9,10,3,2,3,11,0,99,30,40,50]
#[1,0,0,0,99]
#[2,3,0,3,99]
#[2,4,4,5,99,0]
#[1,1,1,4,99,5,6,0,99]

# input
#[1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,19,5,23,2,6,23,27,1,6,27,31,2,31,9,35,1,35,6,39,1,10,39,43,2,9,43,47,1,5,47,51,2,51,6,55,1,5,55,59,2,13,59,63,1,63,5,67,2,67,13,71,1,71,9,75,1,75,6,79,2,79,6,83,1,83,5,87,2,87,9,91,2,9,91,95,1,5,95,99,2,99,13,103,1,103,5,107,1,2,107,111,1,111,5,0,99,2,14,0,0]

# restore the gravity assist program
#[1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,19,5,23,2,6,23,27,1,6,27,31,2,31,9,35,1,35,6,39,1,10,39,43,2,9,43,47,1,5,47,51,2,51,6,55,1,5,55,59,2,13,59,63,1,63,5,67,2,67,13,71,1,71,9,75,1,75,6,79,2,79,6,83,1,83,5,87,2,87,9,91,2,9,91,95,1,5,95,99,2,99,13,103,1,103,5,107,1,2,107,111,1,111,5,0,99,2,14,0,0]


# Part 1

def set_value_in_position(sequence, idx, value):
    sequence[idx] = value
    return sequence

def get_value_in_position(sequence, idx):
    if(len(sequence) > idx):
        return sequence[idx]

def change_output(sequence, output, new_output):
    sequence[output] = new_output
    return sequence

def add(sequence, pos1, pos2):
    return get_value_in_position(sequence, pos1) + get_value_in_position(sequence, pos2)

def multiply(sequence, pos1, pos2):
    return get_value_in_position(sequence, pos1) * get_value_in_position(sequence, pos2)

def calculate(sequence, noun, verb):    
    pointer = 0

    while 1:
        opscode = get_value_in_position(sequence, pointer)
        arg1 = get_value_in_position(sequence, pointer+1)
        arg2 = get_value_in_position(sequence, pointer+2)
        output = get_value_in_position(sequence, pointer+3)

        if(opscode == 1):
            new_output = add(sequence, arg1, arg2)
        elif(opscode == 2):
            new_output = multiply(sequence, arg1, arg2)
        elif(opscode == 99):
            #print("halt" , opscode)
            break
        else:
            print('Invalid opscode.', opscode)

        if(new_output):
            sequence = change_output(sequence, output, new_output)

        pointer += 4
    return sequence
            

for noun in range(100):
    for verb in range(100):
        #sequence = [1,1,1,4,99,5,6,0,99]
        sequence = [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,19,5,23,2,6,23,27,1,6,27,31,2,31,9,35,1,35,6,39,1,10,39,43,2,9,43,47,1,5,47,51,2,51,6,55,1,5,55,59,2,13,59,63,1,63,5,67,2,67,13,71,1,71,9,75,1,75,6,79,2,79,6,83,1,83,5,87,2,87,9,91,2,9,91,95,1,5,95,99,2,99,13,103,1,103,5,107,1,2,107,111,1,111,5,0,99,2,14,0,0]

        sequence = set_value_in_position(sequence, 1, noun)
        sequence = set_value_in_position(sequence, 2, verb)
        sequence = calculate(sequence, noun, verb)
        #print("sequence", sequence)
        if sequence[0] == 19690720:
            print("Noun", noun)
            print("Verb", verb)
            print("Result: ", noun*100+verb)
            # What value is left at position 0 after the program halts?
            print("Value of position 0 =", sequence[0])



                

# Part 1 answer 3166704
# Part 2 answer 


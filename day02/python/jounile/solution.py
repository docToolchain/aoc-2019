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

sequence = [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,19,5,23,2,6,23,27,1,6,27,31,2,31,9,35,1,35,6,39,1,10,39,43,2,9,43,47,1,5,47,51,2,51,6,55,1,5,55,59,2,13,59,63,1,63,5,67,2,67,13,71,1,71,9,75,1,75,6,79,2,79,6,83,1,83,5,87,2,87,9,91,2,9,91,95,1,5,95,99,2,99,13,103,1,103,5,107,1,2,107,111,1,111,5,0,99,2,14,0,0]

# Part 1

def value_in_position(idx):
    if(len(sequence) > idx):
        return sequence[idx]

def change_output(cur_output_val, new_output):
    sequence[cur_output_val] = new_output

def add(pos1, pos2):
    return value_in_position(pos1) + value_in_position(pos2)

def multiply(pos1, pos2):
    return value_in_position(pos1) * value_in_position(pos2)

print("sequence: ", sequence)
for idx, val in enumerate(sequence):
    if(idx % 4 == 0):
        opscode = value_in_position(idx)
        first_input_position = value_in_position(idx+1)
        second_input_position = value_in_position(idx+2)
        cur_output_val = value_in_position(idx+3)

        if(opscode == 1):
            new_output = add(first_input_position, second_input_position)
        elif(opscode == 2):
            new_output = multiply(first_input_position, second_input_position)
        elif(opscode == 99):
            # What value is left at position 0 after the program halts?
            print("Value of position 0 =", sequence[0])
            exit()
        else:
            print('Invalid opscode.')

        if(new_output):
            change_output(cur_output_val, new_output)

        #print("sequence: ", sequence)

# Part 2
        







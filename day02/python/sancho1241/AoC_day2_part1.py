import operator

# assign the puzzle input as a list
puzzle_input =  [1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,9,23,1,5,23,27,1,27,9,31,1,6,31,35,2,35,9,39,1,39,6,43,2,9,43,47,1,47,6,51,2,51,9,55,1,5,55,59,2,59,6,63,1,9,63,67,1,67,10,71,1,71,13,75,2,13,75,79,1,6,79,83,2,9,83,87,1,87,6,91,2,10,91,95,2,13,95,99,1,9,99,103,1,5,103,107,2,9,107,111,1,111,5,115,1,115,5,119,1,10,119,123,1,13,123,127,1,2,127,131,1,131,13,0,99,2,14,0,0]

# create dictionary with operator code - function mapping
dict_func = {1:operator.add,
             2:operator.mul,
             99:'break'}

# iterate through the list in 4-steps from operator to operator
for int_code_pos in range (0,len(puzzle_input),4):
        # operator found
        operator_func = dict_func[puzzle_input[int_code_pos]]
        if operator_func == 'break':
            #Program halted
            break
        elif operator_func == None:
            #unknown operating code
            print (f"Unknown operating code {puzzle_input[int_code_pos]} ")
        else:
            #execute operation
            puzzle_input[puzzle_input[int_code_pos+3]] = operator_func(puzzle_input[puzzle_input[int_code_pos+1]],puzzle_input[puzzle_input[int_code_pos+2]] )


print ("Value at pos 0: {a} ".format(a=puzzle_input[0]))
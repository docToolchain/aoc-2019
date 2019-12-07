import computer

# Part 1
with open("input.txt", "r") as fd:
    line = fd.readline()
    code_initial = line.split(",")
    code_initial = [int(x) for x in code_initial]
    
    # restore gravity assist program
    code = code_initial.copy()
    code[1] = 12
    code[2] = 2
    
    computer.compute(code)
    print("Solution to part 1 - Output: " + str(code[0]))
    
    # Part 2
    range_noun = range(0, 100)
    range_verb = range(0, 100)
    desired_output = 19690720
    
    for noun in range_noun:
        for verb in range_verb:
            code = code_initial.copy()
            code[1] = noun
            code[2] = verb
            computer.compute(code)
            if code[0] == desired_output:
                print("Solution found - Noun: " + str(noun) + ", Verb: " + str(verb) + "; Solution: " + str(100*noun+verb))
    
    
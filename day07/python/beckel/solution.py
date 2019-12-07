import amplifier_sequence
import itertools

# Part 1
with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    max_output = 0
    # permutations
    for permutation in list(itertools.permutations(range(5))):
        output = amplifier_sequence.run_standalone(code, permutation)
        max_output = max(output, max_output) 
    print("Max output:",max_output)

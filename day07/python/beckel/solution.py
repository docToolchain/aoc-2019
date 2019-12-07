import amplifier_sequence
import itertools

# Part 1
with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    max_output = 0
    # permutations
    phase_permuations = list(itertools.permutations(range(5)))
    for permutation in phase_permuations:
        output = amplifier_sequence.run(code, permutation)
        max_output = max(output, max_output) 
    print("Max output:",max_output)

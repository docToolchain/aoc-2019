import amplifier_sequence
import itertools

with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    max_output = 0
    # Part 1
    for permutation in list(itertools.permutations(range(5))):
        output = amplifier_sequence.run_standalone(code, permutation)
        max_output = max(output, max_output) 
    print("Max output (standalone):",max_output)

    max_output = 0
    # Part 2
    for permutation in list(itertools.permutations(range(5, 10))):
        output = amplifier_sequence.run_feedback(code, permutation)
        max_output = max(output, max_output) 
    print("Max output (feedback):",max_output)



from intcomp import *
import itertools

# star 1
maxOutput = 0
for permutation in list(itertools.permutations(range(5))):
    amplifiers = []
    # initialize computers
    for i in range(5):
        amplifiers.append(IntcodeComputer("program"))
    output = 0
    for i in range(5):
        amplifiers[i].input = [permutation[i], output]
        amplifiers[i].run()
        output = amplifiers[i].output
    if maxOutput < output:
        maxOutput = output
print(maxOutput)

# star 2
maxOutput = 0
for permutation in list(itertools.permutations(range(5, 10))):
    amplifiers = []

    # initialize computers
    for i in range(5):
        amplifiers.append(IntcodeComputer("/workspace/aoc-2019/intcomp/program"))
        # amplifiers[i].input.append(permutation[i]) <- why does this append to the previous list every time?
        amplifiers[i].input = [permutation[i]]
    output = 0
    i = 0
    amplifiers[0].input.append(0)
    while 1:
        amplifiers[i].run()
        output = amplifiers[i].output
        i = (i+1)%5
        if not amplifiers[i].has_terminated:
            amplifiers[i].input.append(output)
        else:
            break
    if maxOutput < output:
        maxOutput = output
print(maxOutput)

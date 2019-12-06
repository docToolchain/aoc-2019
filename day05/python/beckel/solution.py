import computer

# Part 1
with open("input.txt", "r") as fd:
    line = fd.readline()
    code_initial = line.split(",")
    code_initial = [int(x) for x in code_initial]

    computer.compute(code_initial)

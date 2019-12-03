import manhattan

# Part 1
with open("input.txt", "r") as fd:
    line = fd.readline()
    cable_1 = line.split(",")
    line = fd.readline()
    cable_2 = line.split(",")
    
    manhattan.compute(cable_1, cable_2)

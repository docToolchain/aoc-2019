from painting_robot import paint

with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    num_panels = paint(code, 0)
    print("Painted",num_panels,"panels.")
    
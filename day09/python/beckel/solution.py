import amplifier_sequence
from computer import Computer

with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    computer = Computer(code, [1])
    computer.run()
    result = computer.get_whole_output()
    print("State: " + str(computer.get_state()))
    print("Output Star 1: " + str(result))

    computer = Computer(code, [2])
    computer.run()
    result = computer.get_whole_output()
    print("State: " + str(computer.get_state()))
    print("Output Star 2: " + str(result))




    
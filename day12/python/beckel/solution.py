import math
import numpy as np

# with open("input.txt", "r") as fd:
    # for line in fd:
        # line = fd.readline()
        # code = line.split(",")
        # code = [int(x) for x in code]

# input.txt
position = np.asarray([ [ 17,-7,-11 ], [1, 4, -1], [6, -2, -6], [19, 11, 9] ], dtype=np.int64)

# example
# position = np.asarray([ [ -1, 0, 2 ], [2, -10, -7], [4, -8, 8], [3, 5, -1] ])
velocity = np.zeros([4,3])
position_change = np.zeros([4,3])

steps = 0
while(True):
    # get position change
    for i in range(0, 4):
        for j in range(0, 4):
            for dim in range(0,3):
                if position[i][dim] < position[j][dim]:
                    position_change[i][dim] += 1
                elif position[i][dim] > position[j][dim]:
                    position_change[i][dim] -= 1

    # adapt velocity and position
    for i in range(0, 4):
        for dim in range(0,3):
            velocity[i][dim] += position_change[i][dim]
            position[i][dim] += velocity[i][dim]
            position_change[i][dim] = 0

    energy = 0
    for i in range(0, 4):
        sum_kinetic = 0
        sum_potential = 0
        for dim in range(0,3):
            sum_kinetic += abs(velocity[i][dim])
            sum_potential += abs(position[i][dim])
        energy += sum_kinetic * sum_potential

    steps += 1
    print("Energy",energy,"after step",steps)
    if steps == 1000:
        break
    # print(position)



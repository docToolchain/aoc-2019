import copy
import math
import numpy as np

moons = []
# test 1: "test-1.txt" / 10
# test 2: "test-2.txt" / 100
# real: "input.txt" / 1000
max_steps = 1000
with open("input.txt") as fd:
    for line in fd:
        line = line.strip()
        line = line[1:-1]
        moons.append([int(x[2:]) for x in line.split(', ')])

num_moons = len(moons)
init_position = copy.deepcopy(moons)
velocity = np.zeros([num_moons,3], dtype=np.int64)

steps = 0
roundtrip = list([0, 0, 0])
while(True):

    steps += 1

    # get position change
    move = np.zeros([num_moons,3], dtype=np.int64)
    for i in range(0, num_moons):
        for j in range(0, num_moons):
            for dim in range(0,3):
                if moons[i][dim] < moons[j][dim]:
                    move[i][dim] += 1
                elif moons[i][dim] > moons[j][dim]:
                    move[i][dim] -= 1
    
    # adapt velocity and position
    for i in range(0, num_moons):
        for dim in range(0,3):
            velocity[i][dim] += move[i][dim]
            moons[i][dim] += velocity[i][dim]

    # check if position has been seen before
    for dim in range(0,3):
        if roundtrip[dim] == 0:
            found_repeat = 0
            for i in range(0,num_moons):
                if velocity[i][dim] == 0 and moons[i][dim] == init_position[i][dim]:
                    found_repeat += 1
            if found_repeat == num_moons:
                roundtrip[dim] = steps
                print("-----------")
                print("All mooons return to origin of dimension",dim)
                print("after",steps,"steps.")
                print("Round trip vector:",roundtrip)
                print("-----------")
                        
    # all roundtrips found?
    if steps > max_steps and 0 not in roundtrip:
        break

    # compute energy (don't do after max_steps steps)
    if steps <= max_steps:
        energy = 0
        for i in range(0, num_moons):
            sum_kinetic = 0
            sum_potential = 0
            for dim in range(0,3):
                sum_kinetic += abs(velocity[i][dim])
                sum_potential += abs(moons[i][dim])
            energy += sum_kinetic * sum_potential
    
    if steps == max_steps:
        print("Energy",energy,"after step",steps)

result = np.lcm(np.lcm(roundtrip[0], roundtrip[1]), roundtrip[2])
print("History repeats itself after",result,"iterations")
    
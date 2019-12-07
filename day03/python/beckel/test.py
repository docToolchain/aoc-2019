import manhattan

input_1 = [ ['R8','U5','L5','D3'], ['R75','D30','R83','U83','L12','D49','R71','U7','L72'], ['R98','U47','R26','D63','R33','U87','L62','D20','R33','U53','R51'] ]
input_2 = [ ['U7','R6','D4','L4'], ['U62','R66','U55','R34','D71','R55','D58','R83'], ['U98','R91','D20','R16','D67','R40','U7','R15','U6','R7'] ]

distances = [ 6, 159, 135 ]
combined_steps = [ 30, 610, 410 ]

for i in range(0, len(input_1)):
    [distance,num_steps] = manhattan.compute(input_1[i], input_2[i])
    if (distance == distances[i] and num_steps == combined_steps[i]):
        print("Test " + str(i) + " successful.")
    else:
        print("Test " + str(i) + " FAILED.")
        
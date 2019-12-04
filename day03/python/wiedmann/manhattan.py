import numpy as np
import math

debug = False

def compute(input_1, input_2):
        
    # convert steps into more computable form: [ [x1,y1], [x2,y2], ... ]
    steps_cable_1 = []
    steps_cable_2 = []
    extract_steps(input_1, steps_cable_1)
    extract_steps(input_2, steps_cable_2)
    
    # put cables on board and check for intersections
    board = np.zeros([100000, 100000], dtype=int)
    walk_over_board(board, steps_cable_1)
    intersections = walk_over_board_cable_2(board, steps_cable_2)

    # determine manhattan distance
    smallest_manhattan_distance = 100000000
    for intersection in intersections:
        distance = abs(intersection[0]) + abs(intersection[1])
        smallest_manhattan_distance = min(distance, smallest_manhattan_distance)
    print("Result: " + str(smallest_manhattan_distance))

    # Count steps to intersection
    steps_cable_1 = count_steps(steps_cable_1, intersections)
    steps_cable_2 = count_steps(steps_cable_2, intersections)
    
    # Get minimum number of steps
    min_steps = 10000000
    for i in range(0, len(steps_cable_1)):
        min_steps = min(min_steps, (steps_cable_1[i] + steps_cable_2[i]))
    
    print("Minimum steps for both cables:",min_steps)
    return [smallest_manhattan_distance, min_steps]
    
    
def extract_steps(steps_input, steps_output):
    for i in range(0, len(steps_input)):
        char_1 = steps_input[i][0]
        num_steps = int(steps_input[i][1:])
        if (char_1 == 'U'):
            [steps_output.append([0,1]) for x in range(0, num_steps)]
        if (char_1 == 'D'):
            [steps_output.append([0,-1]) for x in range(0, num_steps)]
        if (char_1 == 'L'):
            [steps_output.append([-1,0]) for x in range(0, num_steps)]
        if (char_1 == 'R'):
            [steps_output.append([1,0]) for x in range(0, num_steps)]
            
def walk_over_board(board, steps):
    x = 0
    y = 0
    for i in range(0, len(steps)):
        x += steps[i][0]
        y += steps[i][1]
        board[x,y] = 1

def walk_over_board_cable_2(board, steps):
    intersections = []
    x = 0
    y = 0
    for i in range(0, len(steps)):
        x += steps[i][0]
        y += steps[i][1]
        if board[x,y] == 1:
            intersections.append([x,y])
    if debug:
        print("Intersections:",intersections)
    return intersections
    
def count_steps(steps, intersections):
    result_num_steps = []
    for i in range(0, len(intersections)):
        x = 0
        y = 0
        num_steps = 0
        intersection = intersections[i]
        for j in range(0, len(steps)):
            x += steps[j][0]
            y += steps[j][1]
            num_steps += 1
            if (x == intersection[0] and y == intersection[1]):
                result_num_steps.append(num_steps)
                break
    return result_num_steps

import numpy as np
import math

def compute(input_1, input_2):
        
    # convert steps into more computable form: [ [x1,y1], [x2,y2], ... ]
    steps_cable_1 = []
    steps_cable_2 = []
    extract_steps(input_1, steps_cable_1)
    extract_steps(input_2, steps_cable_2)
    
    # put cables on board
    board = np.zeros([100000, 100000], dtype=int)
    [max_x_cable_1, max_y_cable_1, min_x_cable_1, min_y_cable_1] = walk_over_board(board, steps_cable_1)
    [max_x_cable_2, max_y_cable_2, min_x_cable_2, min_y_cable_2] = walk_over_board(board, steps_cable_2)

    x_min = max(min_x_cable_1, min_x_cable_2)
    y_min = max(min_y_cable_1, min_y_cable_2)
    x_max = min(max_x_cable_1, max_x_cable_2)
    y_max = min(max_y_cable_1, max_y_cable_2)
        
    # for each field except [0,0] check if field is covered by both cables
    smallest_manhattan_distance = 100000000
    print("x_min", x_min, "x_max", x_max)
    print("y_min", y_min, "y_max", y_max)
    for i in range(x_min, x_max):
        for j in range(y_min, y_max):
            if (i==0 and j==0):
                continue
            if (board[i][j] == 2):
                print(i," ",j)
                distance = abs(i)+abs(j)
                smallest_manhattan_distance = min(distance, smallest_manhattan_distance)
    print("Result: " + str(smallest_manhattan_distance))
    return smallest_manhattan_distance

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
    max_x = 0
    max_y = 0
    min_x = 0
    min_y = 0
    board[x,y] += 1
    for i in range(0, len(steps)):
        x += steps[i][0]
        y += steps[i][1]
        board[x,y] += 1
        max_x = max(x, max_x)
        max_y = max(y, max_y)
        min_x = min(x, min_x)
        min_y = min(y, min_y)
    return [max_x, max_y, min_x, min_y]
            
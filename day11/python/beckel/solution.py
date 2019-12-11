import math
from painting_robot import paint

with open("input.txt", "r") as fd:
    line = fd.readline()
    code = line.split(",")
    code = [int(x) for x in code]

    [black, white] = paint(code, 0)
    print("Star 1 - Painted panels:",len(black)+len(white))

    [black, white] = paint(code.copy(), 1)
    print("Star 2 - Painted panels:",len(black)+len(white))

    # print picture
    min_y = 10000
    max_y = -10000
    min_x = 10000
    max_x = -10000
    for pos in black:
        min_y = min(min_y, pos[1])
        max_y = max(max_y, pos[1])
        min_x = min(min_x, pos[0])
        max_x = max(max_x, pos[0])
    for pos in white:
        min_y = min(min_y, pos[1])
        max_y = max(max_y, pos[1])
        min_x = min(min_x, pos[0])
        max_x = max(max_x, pos[0])
        
    for i in reversed(range(min_y,max_y+1)):
        for j in range(min_x+1,max_x-1):
            if [j,i] in black:
                print(' ', end = '')
            else:
                print('█', end = '')
        print()
       
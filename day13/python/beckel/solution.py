import numpy as np
from computer import Computer
import time

def is_x(i):
    return i % 3 == 0

def is_y(i):
    return (i+2) % 3 == 0

def is_tile(i):
    return (i+1) % 3 == 0

def icon(tile_id):
    if tile_id == 0:
        return ' ' # empty space
    elif tile_id == 1:
        return '█' # wall
    elif tile_id == 2:
        return '▒' # block
    elif tile_id == 3:
        return '▂' # paddle
    elif tile_id == 4:
        return '•' # ball

def print_lines(n, x_dim, char):
    for _ in range(n):
        for _ in range(x_dim):
            print(char, end='')
        print()

def render(board, score):
    x_dim = board.shape[0]
    y_dim = board.shape[1]
    print_lines(8, x_dim, ' ')
    for y in range(y_dim):
        for x in range(x_dim):
            print(icon(board[x][y]), end='')
        print()
    print_lines(1, x_dim, '-')
    print("Score:",score)
    print_lines(1, x_dim, '-')
    
with open("input.txt") as fd:
    line = fd.readline().strip()
    code = line.split(',')
    code = [int(x) for x in code]
    computer = Computer(code, [])
    computer.run()

    # Star 1: block tiles
    output = computer.get_whole_output()
    num_block_tiles = 0
    for i in range(0, len(output)):
        if is_tile(i):
            if output[i] == 2:
                num_block_tiles += 1
    print("Star 1: There are",num_block_tiles,"block tiles on the board.")

    # Set program to "free" mode
    code[0] = 2

    # Set joystick to "center"
    joystick = 1

    computer = Computer(code, [])
    i = 0
    score = 0
    x_dim = 41
    y_dim = 25
    board = np.zeros([x_dim, y_dim])
    while(computer.get_state() != "TERMINATED"):
        computer.run()
        output = computer.get_whole_output()
        while (i < len(output)):
            if (output[i] == -1 and output[i+1] == 0):
                score = output[i+2]
            else:
                tile_id = output[i+2]
                board[output[i]][output[i+1]] = tile_id
                if tile_id == 4:
                    ball_x = output[i]
                elif tile_id == 3:
                    paddle_x = output[i]            
            i += 3
        render(board, score)

        # adjust joystick
        if paddle_x > ball_x:
            joystick = -1
        elif paddle_x < ball_x:
            joystick = 1
        else:
            joystick = 0
        computer.provide_input(joystick)
        time.sleep(0.1)

    print("Final score:",score)
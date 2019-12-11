import math
from computer import Computer

def paint(program, initial_color):

    computer = Computer(program, [initial_color])
    
    black = []
    white = []
    position = [0,0]
    direction = 90

    while(True):

        computer.run()
        [color, turn] = computer.get_last_output_values(2)

        # paint black (0) or white (1)
        if color == 0:
            if position not in black: black.append(position)
            if position in white: white.remove(position)
        if color == 1:
            if position not in white: white.append(position)
            if position in black: black.remove(position)

        # turn left (0) or right (1)
        if turn == 0:
            direction = (direction + 90) % 360
        elif turn == 1:
            direction = (direction - 90) % 360

        # step forward
        new_x = position[0] + int(math.cos(math.radians(direction)))
        new_y = position[1] + int(math.sin(math.radians(direction)))
        position = [new_x, new_y]

        if computer.get_state() == "TERMINATED":
            break
        elif computer.get_state() == "WAITING_FOR_INPUT":
            current_color = 1 if position in white else 0
            computer.provide_input(current_color)

    return [black, white]
            

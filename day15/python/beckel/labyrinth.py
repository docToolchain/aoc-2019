import copy
from computer import Computer
import math
from collections import deque

def opposite(i):
    return (i + 1) % 4 + 1

def start_labyrinth(code):

    directions = { 1: (0,1), 2: (0,-1), 3:(-1,0), 4:(1,0) }
    state = { (0, 0): (0, Computer(code, []), 0) } # field, computer, steps
    queue = deque([(0,0)])
    results = []

    # bfs
    while(queue):
        if not queue:
            0+1# DONE - not sure what to do
        
        current_pos = queue.popleft()
        _, computer, steps = state[current_pos]
        for direction, move in directions.items():
            new_pos = (current_pos[0] + move[0], current_pos[1] + move[1])
            if new_pos in state:
                continue
            comp = computer.copy()#copy.deepcopy(computer)
            comp.provide_input(direction)
            comp.run()
            result = comp.get_last_output()
            state[new_pos] = (result, comp, steps+1)
            if result == 1:
                queue.append(new_pos)
            if result == 2:
                results.append((new_pos, steps+1))
    min_steps = 10000
    for r in results:
        min_steps = min(min_steps, r[1])
    print("Star 1:",min_steps,"steps.")

    with_oxygen = {results[0][0]: 0}
    bfs = deque([results[0][0]])
    while bfs:
        current_pos = bfs.popleft()
        for direction, move in directions.items():
            new_pos = (current_pos[0] + move[0], current_pos[1] + move[1])
            if new_pos in with_oxygen:
                continue
            if new_pos in state and state[new_pos][0] == 1:
                with_oxygen[new_pos] = with_oxygen[current_pos] + 1
                bfs.append(new_pos)
    print(max(with_oxygen.values()))





    # return_steps = 100000000
    
    # # mark field as visited
    # visited.append(position)
    
    # # check each direction
    # direction_next_step = []

    # for i in directions:

    #     # don't move back
    #     if i == opposite(in_dir):
    #         continue

    #     # run computer (but not every time ...)
    #     computer_copy = copy.deepcopy(computer)
    #     computer_copy.provide_input(i)
    #     computer_copy.run()
    #     result = computer_copy.get_last_output()

    #     if result == 0:
    #         0+1 # do nothing

    #     elif result == 1:
    #         direction_next_step.append(i)

    #     elif result == 2:
    #         return num_steps + 1

    # for i in direction_next_step:

    #     # increase counter by one
    #     new_pos = move(position, i)
    #     if new_pos not in visited:
    #         steps = walk(copy.deepcopy(computer), num_steps+1, i, new_pos, visited)
    #         return_steps = min(steps, return_steps)
    #         assert(computer.get_last_output() == 1)

    # return return_steps    





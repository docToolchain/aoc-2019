from computer import Computer
from collections import defaultdict

def get_intersection(code):
    computer = Computer(code, [])
    computer.run()
        
    x = 0
    y = 0
    sum_alignment = 0
    items = defaultdict(list)
    for ascii_item in computer.get_whole_output():
        if ascii_item == 10:
            y += 1
            print()
        else:
            x += 1
            items[y].append(ascii_item)
            print(chr(ascii_item), end='')

    # check for intersections
    sum_alignment = 0
    num_intersections = 0
    for x in range(len(items[0])):
        for y in range(len(items.keys())):
            if x > 0 and x < len(items[0])-1:
                if y > 0 and y < len(items.keys())-1:
                    if chr(items[y][x]) == '#' and chr(items[y-1][x]) == '#' and chr(items[y+1][x]) == '#' and chr(items[y][x-1]) == '#' and chr(items[y][x+1]) == '#':
                        num_intersections += 1
                        sum_alignment += x*y
    print("Star 1: Sum of alignment:",sum_alignment)

def cleanup(code):

    code[0] = 2
    input_main_routine = "A,A,B,B,C,B,C,B,C,A"
    input_function_a = "L,10,L,10,R,6"
    input_function_b = "R,12,L,12,L,12"
    input_function_c = "L,6,L,10,R,12,R,12"

    input_vector = [ord(x) for x in list(input_main_routine)]
    input_vector.append(10)
    input_vector.extend([ord(x) for x in list(input_function_a)])
    input_vector.append(10)
    input_vector.extend([ord(x) for x in list(input_function_b)])
    input_vector.append(10)
    input_vector.extend([ord(x) for x in list(input_function_c)])
    input_vector.append(10)
    input_vector.extend([ord('n'), 10])
    
    computer = Computer(code, input_vector)
    computer.run()
    print("Star 2 result:",computer.get_last_output())

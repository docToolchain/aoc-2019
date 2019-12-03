import sys

INPUT_FILE_NAME = 'input.txt'


def parse_cable_input(line):
    result = []
    for instruction in line.rstrip('\n').split(','):
        result.append({'op': instruction[0], 'val': int(instruction[1:])})
    return result


def add_cable_position_to_map(grid_map, cable_id, cable_position, cable_steps):
    if cable_position in grid_map:
        for cable in grid_map[cable_position]:
            if cable[0] == cable_id:
                # print('Ignoring cable "%i" as it visited already the grid position' % (cable_id,))
                return
        grid_map[cable_position].append((cable_id, cable_steps))
    else:
        grid_map[cable_position] = [(cable_id, cable_steps)]


def create_cable_grid(puzzle_input):
    unique_cable_id = 0
    grid_map = {}
    for cable_instruction_list in puzzle_input:
        current_cable_id = unique_cable_id
        unique_cable_id += 1
        cable_position = (0, 0)
        cable_steps = 1
        # Warning: Ugly code ahead 🙈
        for instruction in cable_instruction_list:
            if instruction['op'] == 'L':
                for i in range(instruction['val']):
                    add_cable_position_to_map(grid_map, current_cable_id, (cable_position[0] - (i + 1), cable_position[1]), cable_steps)
                    cable_steps += 1
                cable_position = (cable_position[0] - instruction['val'], cable_position[1])
            elif instruction['op'] == 'R':
                for i in range(instruction['val']):
                    add_cable_position_to_map(grid_map, current_cable_id, (cable_position[0] + (i + 1), cable_position[1]), cable_steps)
                    cable_steps += 1
                cable_position = (cable_position[0] + instruction['val'], cable_position[1])
            elif instruction['op'] == 'U':
                for i in range(instruction['val']):
                    add_cable_position_to_map(grid_map, current_cable_id, (cable_position[0], cable_position[1] + (i + 1)), cable_steps)
                    cable_steps += 1
                cable_position = (cable_position[0], cable_position[1] + instruction['val'])
            elif instruction['op'] == 'D':
                for i in range(instruction['val']):
                    add_cable_position_to_map(grid_map, current_cable_id, (cable_position[0], cable_position[1] - (i + 1)), cable_steps)
                    cable_steps += 1
                cable_position = (cable_position[0], cable_position[1] - instruction['val'])
    return grid_map


def find_nearest_intersection(grip_map):
    min_distance_val = sys.maxsize
    for position, cables in grip_map.items():
        if len(cables) == 2:
            distance = abs(position[0]) + abs(position[1])
            if distance < min_distance_val:
                min_distance_val = distance
    return min_distance_val


def find_min_steps_intersection(grip_map):
    min_steps = sys.maxsize
    for position, cables in grip_map.items():
        if len(cables) == 2:
            steps = cables[0][1] + cables[1][1]
            if steps < min_steps:
                min_steps = steps
    return min_steps


puzzle_input = None
with open(INPUT_FILE_NAME) as input_file:
    puzzle_input = [parse_cable_input(line) for line in input_file.readlines()]
grid_map = create_cable_grid(puzzle_input)

solution_part_1 = find_nearest_intersection(grid_map)
print('Solution to part 1: %i' % (solution_part_1,))

solution_part_2 = find_min_steps_intersection(grid_map)
print('Solution to part 2: %i' % (solution_part_2,))

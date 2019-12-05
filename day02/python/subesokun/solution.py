INPUT_FILE_NAME = 'input.txt'

puzzle_input = None
with open(INPUT_FILE_NAME) as input_file:
    puzzle_input = list(map(lambda val: int(val), input_file.readline().rstrip('\n').split(',')))


def run_instruction(opcode, param_1, param_2, param_3, memory):
    if opcode == 1:
        memory[param_3] = memory[param_1] + memory[param_2]
    elif opcode == 2:
        memory[param_3] = memory[param_1] * memory[param_2]
    else:
        raise Exception('Ooooppps')


def run_program(memory):
    instruction_pointer = 0
    while memory[instruction_pointer] != 99:
        run_instruction(memory[instruction_pointer + 0], memory[instruction_pointer + 1], memory[instruction_pointer + 2], memory[instruction_pointer + 3], memory)
        instruction_pointer += 4
    return memory


memory_solution_part1 = puzzle_input.copy()
memory_solution_part1[1] = 12
memory_solution_part1[2] = 2
solution_part_1 = run_program(memory_solution_part1)
print('Solution to part 1: %i' % (solution_part_1[0],))


def find_noun_verb(output, memory):
    for noun in range(0, 100):
        for verb in range(0, 100):
            memory_copy = memory.copy()
            memory_copy[1] = noun
            memory_copy[2] = verb
            result = run_program(memory_copy)[0]
            if result == output:
                return (noun, verb)


(noun, verb) = find_noun_verb(19690720, puzzle_input)
solution_part_2 = 100 * noun + verb
print('Solution to part 2: %i' % (solution_part_2,))

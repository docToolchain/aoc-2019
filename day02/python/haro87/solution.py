import random
from enterprise import MontgomeryScott as Scotty

scotty = Scotty()

def part_one():
    with open('input.txt', "r") as commands:
        trans = scotty.translate(commands.read())
        res = scotty.execute(trans)
        print('Part one, Scotty says: {0}'.format(res[0]))


def part_two():
    with open('input.txt', "r") as commands:   
        orig = scotty.translate(commands.read())
        res = 0
        while res != 19690720:
            noun = random.randint(0, 99)
            verb = random.randint(0, 99)
            program = orig.copy()
            program[1] = noun
            program[2] = verb
            res = scotty.execute(program)[0]

    print('Part two, Scotty says: {0}'.format(100 * noun + verb))
    
part_one()

part_two()
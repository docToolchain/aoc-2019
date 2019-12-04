from enterprise import PavelChekov as Chekov

chekov = Chekov()

with open('input.txt', "r") as commands:
    commands1, commands2 = commands.read().splitlines()
    instructions1 = chekov.wire_up(commands1)
    instructions2 = chekov.wire_up(commands2)
    plan1 = chekov.wire_points(instructions1)
    plan2 = chekov.wire_points(instructions2)
    
    res1 = chekov.wire_cross(plan1, plan2)
    print('Part one, Chekov says: {0}'.format(res1))

    res2 = chekov.wire_intersect(plan1, plan2)
    print('Part two, Chekov says: {0}'.format(res2))

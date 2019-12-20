from labyrinth import start_labyrinth

fd = open("input.txt")
line = fd.readline().strip()
code = [int(x) for x in line.split(',')]

start_labyrinth(code)


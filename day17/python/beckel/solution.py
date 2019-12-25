from scaffold import get_intersection, cleanup

fd = open("input.txt")
line = fd.readline().strip()
code = [int(x) for x in line.split(',')]

get_intersection(code.copy()) 
cleanup(code.copy()) 


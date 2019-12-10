import numpy as np
import math
from collections import defaultdict

debug = False
field = []
with open("input.txt") as fd:
    # read input
    for line in fd:
        field.append(list(map(lambda x: 1 if x == '#' else 0, line.strip())))       
    x = np.asarray(field,dtype=np.int64)
    x = np.transpose(x)

    # count visible asteroids
    max_visible_asteroids = { 'x': 0, 'y': 0, 'num': 0, 'directions': defaultdict(list) }
    it = np.nditer(x, flags=['multi_index'])
    while not it.finished:
        directions = defaultdict(list)
        i = it.multi_index[0]
        j = it.multi_index[1]
        if debug:
            print("Checking field",i,",",j)

        # do not proceed if there is no astroid
        if x[i,j] == 0:
            it.iternext()
            continue
        
        # identify visible targets
        it_target = np.nditer(x, flags=['multi_index'])
        while not it_target.finished:
            i_target = it_target.multi_index[0]
            j_target = it_target.multi_index[1]
            
            # continue if target is no asteroid
            if x[i_target,j_target] == 0:
                it_target.iternext()
                continue

            # continue if target is same field
            if (i == i_target) and (j == j_target):
                it_target.iternext()
                continue

            # store direction and distance
            dir = np.arctan2(i_target-i, j_target-j)
            distance = math.sqrt((j_target-j)**2 + (i_target-i)**2)
            directions[dir].append({'x': i_target, 'y': j_target, 'distance': distance})

            it_target.iternext()

        visible_asteroids_from_here = len(directions.keys())
        if visible_asteroids_from_here > max_visible_asteroids['num']:
            max_visible_asteroids['num'] = visible_asteroids_from_here
            max_visible_asteroids['x'] = i
            max_visible_asteroids['y'] = j
            max_visible_asteroids['directions'] = directions
        
        it.iternext()
    
    print("Part 1: Max. visible asteroids:",max_visible_asteroids['num']," - from (",max_visible_asteroids['x'],",",max_visible_asteroids['y'],")")

    # Star 2: Shoot asteroids with laster
    dir_from_base = max_visible_asteroids['directions']
    hit_number = 0
    while (len(dir_from_base) > 0):
        for direction in sorted(dir_from_base.keys(), reverse=True):
            asteroids_in_line = dir_from_base[direction]
            list.sort(asteroids_in_line, key=lambda x:x['distance'])
            closest = asteroids_in_line.pop(0)
            if not asteroids_in_line:
                del dir_from_base[direction]
            hit_number += 1
            print("Hit no.",hit_number,": Asteroid at",closest['x'],closest['y'])

        
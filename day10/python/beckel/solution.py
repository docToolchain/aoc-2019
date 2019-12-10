import numpy as np

debug = False
field = []
with open("input.txt") as fd:
    # read input
    for line in fd:
        field.append(list(map(lambda x: 1 if x == '#' else 0, line.strip())))       
    x = np.asarray(field,dtype=np.int64)
    x = np.transpose(x)

    # count visible asteroids
    max_visible_asteroids = { 'x': 0, 'y': 0, 'num': 0 }
    it = np.nditer(x, flags=['multi_index'])
    while not it.finished:
        i = it.multi_index[0]
        j = it.multi_index[1]
        if debug:
            print("Checking field",i,",",j)

        directions = dict()

        # do not proceed if there is no astroid
        if x[i,j] == 0:
            it.iternext()
            continue

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

            # store direction
            dir = np.arctan2(j_target-j, i_target-i)
            directions[dir] = 1

            it_target.iternext()


            # check if space between two asteroids at [i,j] and [i_target,j_target] is free
            # x_dir = 1 if (i_target - i) >= 0 else -1
            # y_dir = 1 if (j_target - j) >= 0 else -1
            # visibility_blocked = False
            # dy = 0

            # # all directions except vertical
            # for dx in range(i + x_dir, i_target, x_dir):
            #     dy += y_dir * abs(j_target-j) / abs(i_target-i)
            #     i_check = dx
            #     j_check = j + dy
            #     if j_check.is_integer() and x[i_check,int(j_check)] == 1:
            #         visibility_blocked = True

            # # y steps if vertical direction
            # if i_target == i:
            #     for j_check in range(j + y_dir, j_target, y_dir):
            #         if x[i,j_check] == 1:
            #             visibility_blocked = True

            # if visibility_blocked == False:
            #     visible_asteroids_from_here += 1
            # it_target.iternext()
            # continue

        visible_asteroids_from_here = len(directions.keys())
        if (debug):
            print("Visible asteroids",visible_asteroids_from_here)
        if visible_asteroids_from_here > max_visible_asteroids['num']:
            max_visible_asteroids['num'] = visible_asteroids_from_here
            max_visible_asteroids['x'] = i
            max_visible_asteroids['y'] = j
        
        it.iternext()

    print("Max. visible asteroids:",max_visible_asteroids)

with open('input.txt') as f:
    paths = [ line.strip().split(',') for line in f ]

v = {'L': (-1, 0), 'R': (1, 0), 'U': (0, 1), 'D': (0, -1)}

def add(p0, p1):
    return (p0[0]+p1[0], p0[1]+p1[1])

def sample_path(path):
    p = (0, 0)
    locations = set()
    dists = {}
    length = 0
    while len(path):
        cmd = path.pop(0)
        d, n = cmd[0], int(cmd[1:])
        while n:
            length += 1
            p = add(p, v[d])
            locations.add(p)
            n -= 1
            if p not in dists:
                dists[p] = length
    return locations, dists

def manhattan(intersects):
    norm = lambda q: abs(q[0])+abs(q[1])
    return norm(min(intersects, key=norm))

def wire(intersects, dists_0, dists_1):
    norm = lambda x: dists_0[x] + dists_1[x]
    return norm(min(intersects, key=norm))

locs_0, dists_0 = sample_path(paths[0])
locs_1, dists_1 = sample_path(paths[1])
intersects = locs_0 & locs_1

print("Star1: {}".format(manhattan(intersects)))
print("Star2: {}".format(wire(intersects, dists_0, dists_1)))




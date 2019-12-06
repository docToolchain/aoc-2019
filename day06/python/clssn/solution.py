orbit_map = {}
with open('input.txt') as f:
    for line in f:
        vk = line.strip().split(')')
        orbit_map[vk[1]] = vk[0]

def orbiters(omap, x):
    return { i[0] for i in omap.items() if i[1] == x }

def count_direct_indirect_orbits(orbit_map):
    count = 0
    visit = [('COM', 1)]
    while len(visit):
        o, depth = visit.pop()
        l = list(orbiters(orbit_map, o))
        count += len(l) * depth
        visit += [ (x, depth+1) for x in l ]
    return count

def count_hops_to_santa(orbit_map):
    def route_to_com(start):
        route = [start]
        while route[-1] != 'COM':
            route.append(orbit_map[route[-1]])
        return route
    you_to_com = route_to_com('YOU')
    san_to_com = route_to_com('SAN')
    for i, x in enumerate(you_to_com):
        if x in san_to_com:
            return san_to_com.index(x) + i - 2

print('Star1 count: {}'.format(count_direct_indirect_orbits(orbit_map)))
print('Star2 count: {}'.format(count_hops_to_santa(orbit_map)))    
    
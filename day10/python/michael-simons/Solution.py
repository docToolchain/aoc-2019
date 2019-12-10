#!/usr/bin/env python3

import math
import sys

with open('input.txt', 'r') as file:
    input = file.read().rstrip()

# tag::basicGeometry[]
def direction(v1, v2):
    return (v2[0]-v1[0], v2[1]-v1[1])

def length(v):
    return math.sqrt(v[0]**2 + v[1]**2)

def norm(t):
    l = length(t)
    return (round(t[0]/l,4), round(t[1]/l,4))
# end::basicGeometry[]

with open('input.txt' if len(sys.argv) == 1 else sys.argv[1], 'r') as file:

    input = file.read().rstrip()
    
    asteroids = [(x,y) for y, l in enumerate(input.split("\n")) for x, s in enumerate(l) if s == '#']

    # tag::solutionPart1[]
    all = {}
    for candidate in asteroids:  # <.>
        targets = {}    

        for other in asteroids:
            if(candidate == other):
                continue
            d = direction(candidate, other) # <.>
            targets.setdefault(norm(d), []).append((other, length(d))) # <.>

        for val in targets.values(): val.sort(key=lambda i:i[1]) # <.>
        all[candidate] = targets

    base = max(all.items(), key=lambda i:len(i[1])) # <.>
    print(f"Star one {len(base[1])}")
    # end::solutionPart1[]

    # tag::solutionPart2[]
    hit = []
    while(len(hit) < 200 and len(base[1]) > 0):
        for val in sorted(base[1].keys(), key=lambda i:(math.atan2(i[0], i[1])), reverse=True): # <.> 
            targeted = base[1].get(val)
            hit.append(targeted.pop()[0]) # <.>
            if(not targeted):
                del base[1][val]
            if(len(hit) == 200):
                break

    print(f"Star two {hit[-1][0]*100+hit[-1][1]}")
    # end::solutionPart2[]

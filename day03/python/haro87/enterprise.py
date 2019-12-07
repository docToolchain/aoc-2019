class PavelChekov:
    
    DX = {'L': -1, 'R': 1, 'U': 0, 'D': 0}
    DY = {'L': 0, 'R': 0, 'U': 1, 'D': -1}

    def __init__(self):
        pass

    def wire_up(self, schematic):
        return schematic.split(",")

    def wire_points(self, plan):
        x = 0
        y = 0
        length = 0
        wiremap = {}
        for move in plan:
            direction = move[:1]
            steps = int(move[1:])
            for _ in range(steps):
                x += self.DX[direction]
                y += self.DY[direction]
                length += 1
                if (x,y) not in wiremap:
                    wiremap[(x,y)] = length
        return wiremap
    
    def wire_cross(self, wire1, wire2):
        both = set(wire1.keys())&set(wire2.keys())
        return min([abs(x)+abs(y) for (x,y) in both])

    def wire_intersect(self, wire1, wire2):
        both = set(wire1.keys())&set(wire2.keys())
        return min([wire1[p]+wire2[p] for p in both])

from typing import List, Dict


class Vector:
    def __init__(self, x: int, y: int):
        super().__init__()
        self.x = x
        self.y = y

    def __add__(self, other: 'Vector') -> 'Vector':
        x_add = self.x + other.x
        y_add = self.y + other.y
        return Vector(x_add, y_add)

    def __sub__(self, other: 'Vector') -> 'Vecotr':
        x_sub = self.x - other.x
        y_sub = self.y - other.y
        return Vector(x_sub, y_sub)

    def __eq__(self, other: 'Vector') -> bool:
        return self.x == other.x and self.y == other.y
    
    def __hash__(self):
        return hash((self.x, self.y))

    def __str__(self):
        return str({'x': self.x, 'y': self.y})

    def manhatten_distance_to(self, other: 'Vector') -> int:
        return abs(self.x) + abs(other.x) + abs(self.y) + abs(other.y)


class Operation:
    delta_x = {'L': -1, 'R': 1, 'U': 0, 'D': 0}
    delta_y = {'L': 0, 'R': 0, 'U': 1, 'D': -1}

    def __init__(self, operation: str, distance: int):
        super().__init__()
        self.type = operation
        self.distance = distance

    def to_points(self, start: Vector, start_steps: int):
        points = {}
        additonal_lengt = 0
        for i in range(self.distance+1):
            new_point = Vector(
                start.x+i*self.delta_x[self.type], start.y+i*self.delta_y[self.type])
            points[new_point] = i + start_steps
            additonal_lengt = i
        return additonal_lengt+start_steps, new_point, points


class Wire:
    def __init__(self, operations: List[Operation]):
        super().__init__()
        self.operations = operations
        self.points = {}
        self.compute_path()
    
    def compute_path(self):
        segments = {}
        start_steps = 0
        start_vector = Vector(0,0)
        for operation in self.operations:
            start_steps, start_vector, points = operation.to_points(start_vector, start_steps)
            segments.update(points)
        self.points = segments

    @classmethod
    def find_intersections(cls, wires: List['Wire']):
        wire_1 = set(wires[0].points.keys())
        wire_1.discard(Vector(0, 0))
        wire_2 = set(wires[1].points.keys())
        intersection_set = wire_1.intersection(wire_2)
        intersections = {}
        for point in intersection_set:
            intersections[point] = wires[0].points[point] + \
                wires[1].points[point]
        return intersections

    @staticmethod
    def get_closest_intersection_distance(intersections) -> int:
        distances = [intersection.manhatten_distance_to(Vector(0,0)) for intersection in intersections]
        return min(distances)

    @classmethod
    def find_intersections_time_sensitive(cls, wires: List['Wire']):
        intersections = cls.find_intersections(wires)
        distances = [wires[0].points[intersection] + wires[1].points[intersection]
                     for intersection in intersections]
        return min(distances)


def parse_file(path: str) -> List[Wire]:
    with open(path) as _file:
        content = _file.readlines()
        wires = []
        for unparsed_wire in content:
            wire = [Operation(op[0], int(op[1:])) for op in unparsed_wire.split(',')]
            wires.append(Wire(wire))
        return wires


if __name__ == '__main__':
    wires = parse_file('input.txt')
    intersections = Wire.find_intersections(wires)
    distance = Wire.get_closest_intersection_distance(intersections)
    print(distance)
    distance_2 = Wire.find_intersections_time_sensitive(wires)
    print(distance_2)

from unittest import TestCase
import unittest
from solution import Operation, Vector, Wire


class TestVector(TestCase):
    def test_instantiation(self):
        vector = Vector(10, 20)
        self.assertIsInstance(vector, Vector)


class TestOperation(TestCase):
    def test_instantiation(self):
        operation = Operation('U', 10)
        self.assertIsInstance(operation, Operation)

    def test_to_points(self):
        expected_points = {
            Vector(10, 10): 10,
            Vector(10, 11): 11,
            Vector(10, 12): 12,
            Vector(10, 13): 13,
        }
        operation = Operation('U', 3)

        new_length, new_start_point, result = operation.to_points(Vector(10, 10), 10)

        self.assertEqual(expected_points, result)
        self.assertEqual(new_start_point, Vector(10, 13))
        self.assertEqual(new_length, 13)


class TestWire(TestCase):
    def test_instantiation(self):
        wire = Wire([])
        self.assertIsInstance(wire, Wire)

    def test_compute_path(self):
        operations = [
            Operation('U', 2),
            Operation('L', 3),
            Operation('U', 1)
        ]
        expected_points = {
            Vector(0, 0): 0,
            Vector(0, 1): 1,
            Vector(0, 2): 2,
            Vector(-1, 2): 3,
            Vector(-2, 2): 4,
            Vector(-3, 2): 5,
            Vector(-3, 3): 6
        }
        wire = Wire(operations)
        
        wire.compute_path()

        self.assertEqual(7, len(wire.points))
        self.assertEqual(expected_points, wire.points)

    def test_get_closest_intersection_distance(self):
        intersections = [
            Vector(3,3),
            Vector(6,5)
        ]
        wire = Wire([])

        result = wire.get_closest_intersection_distance(intersections)

        self.assertEqual(result, 6)

    def test_find_intersections(self):
        wire_1 = [
            Operation('R', 8),
            Operation('U', 5),
            Operation('L', 5),
            Operation('D', 3),
        ]
        wire_2 = [
            Operation('U', 7),
            Operation('R', 6),
            Operation('D', 4),
            Operation('L', 4),
        ]
        expected_intersections = {
            Vector(3,3): 40,
            Vector(6,5): 30
        }
        wires = [Wire(wire_1), Wire(wire_2)]

        intersections = Wire.find_intersections(wires)

        self.assertEqual(expected_intersections, intersections)



    def test_time_sensitive(self):
        wire_1 = [
            Operation('R', 8),
            Operation('U', 5),
            Operation('L', 5),
            Operation('D', 3),
        ]
        wire_2 = [
            Operation('U', 7),
            Operation('R', 6),
            Operation('D', 4),
            Operation('L', 4),
        ]
        wires = [Wire(wire_1), Wire(wire_2)]
        distance = Wire.find_intersections_time_sensitive(wires)

        self.assertEqual(30, distance)

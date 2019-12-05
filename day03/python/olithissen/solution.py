#!/usr/bin/env python3

import re

def solutionStar1(inputFile):
    with open(inputFile, "r") as input:
        lines = input.readlines()

    touched = []
    for line in lines:
        line = line.replace("\n", "")
        touched.append(walk(line))

    intersections = set(touched[0]).intersection(touched[1])

    distances = []
    for intersection in intersections:
        distance = abs(intersection[0]) + abs(intersection[1])
        if (distance > 0):
            distances.append(distance)

    return min(distances)

def solutionStar2(inputFile):
    with open(inputFile, "r") as input:
        lines = input.readlines()

    touched = []
    for line in lines:
        line = line.replace("\n", "")
        touched.append(walk(line))

    intersections = set(touched[0]).intersection(touched[1])

    distances = []
    for intersection in intersections:
        distance = countStepsToCoordinate(lines[0].replace("\n", ""), intersection) + countStepsToCoordinate(lines[1].replace("\n", ""), intersection)
        if (distance > 0):
            distances.append(distance)

    return min(distances)

def walk(line):
    directions = line.split(",")
    touched = []
    x = 0
    y = 0
    touched.append((x, y))
    for direction in directions:
        pos = re.split("(\\D+)(\\d+)", direction)
        for _ in range(int(pos[2])):
            dir = getDirection(pos[1])
            touched.append(dir(touched[-1]))

    return touched
    
def getDirection(dir):
    switcher = {
        "U": up,
        "D": down,
        "L": left,
        "R": right,
    }

    return switcher.get(dir)

def up(pos):
    return (pos[0], pos[1] + 1)

def down(pos):
    return (pos[0], pos[1] - 1)

def left(pos):
    return (pos[0] - 1, pos[1])

def right(pos):
    return (pos[0] + 1, pos[1])


def countStepsToCoordinate(line, coordinates):
    directions = line.split(",")
    touched = []
    x = 0
    y = 0
    touched.append((x, y))
    stepCounter = 0
    for direction in directions:
        pos = re.split("(\\D+)(\\d+)", direction)
        for _ in range(int(pos[2])):
            stepCounter += 1
            dir = getDirection(pos[1])
            touched.append(dir(touched[-1]))
            if (touched[-1] == coordinates):
                return stepCounter

    return 0

sampleResultStar1 = solutionStar1("sample.txt")
realResultStar1 = solutionStar1("input.txt")
sampleResultStar2 = solutionStar2("sample.txt")
realResultStar2 = solutionStar2("input.txt")

print("Sample result *1: ", sampleResultStar1)
assert sampleResultStar1 == 6

print("Real result *1: ", realResultStar1)
assert realResultStar1 == 1225

print("Sample result *2: ", sampleResultStar2)
assert sampleResultStar2 == 30

print("Real result *2: ", realResultStar2)
assert realResultStar2 == 107036

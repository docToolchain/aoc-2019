#!/usr/bin/env python3

def split(line):
    return line.rstrip().split(")")

def solutionStar1(inputFile):
    with open(inputFile, "r") as input:
        lines = input.readlines()

    relations = {}

    for line in lines:
        relation = split(line)
        parent = relation[0]
        child = relation[1]
        if not parent in relations:
            relations[parent] = []
        relations[parent].append(child)

    return walkDepthFirst("COM", 0, relations)

def walkDepthFirst(root, depth, dictionary):
    if root in dictionary:
        count = 0
        depth += 1
        for child in dictionary[root]:
            count += depth + walkDepthFirst(child, depth, dictionary)
        return count
    
    return 0

def solutionStar2(inputFile):
    with open(inputFile, "r") as input:
        lines = input.readlines()

    relations = {}
    cRelations = {}

    for line in lines:
        relation = split(line)
        parent = relation[0]
        child = relation[1]
        if not parent in relations:
            relations[parent] = []
        relations[parent].append(child)

        if not child in cRelations:
            cRelations[child] = []
        cRelations[child] = parent

    pathToMe = findUpstreamFrom("YOU", cRelations)
    pathToSanta = findUpstreamFrom("SAN", cRelations)
    intersect = list((set(pathToMe) | set(pathToSanta)) - (set(pathToMe) & set(pathToSanta)))

    print(pathToMe, pathToSanta, intersect)

    return len(intersect)

def findUpstreamFrom(leaf, relations):
    upstream = []
    while True:
        if leaf in relations:
            parent = relations[leaf]
            upstream.append(parent)
            leaf = parent
            if (parent == "COM"):
                break
        else:
            break

    return upstream

sampleResultStar1 = solutionStar1("sample.txt")
realResultStar1 = solutionStar1("input.txt")
sampleResultStar2 = solutionStar2("sample3.txt")
realResultStar2 = solutionStar2("input.txt")

print("Sample result *1: ", sampleResultStar1)
assert sampleResultStar1 == 42

print("Real result *1: ", realResultStar1)
assert realResultStar1 == 227612

print("Sample result *2: ", sampleResultStar2)
assert sampleResultStar2 == 4

print("Real result *2: ", realResultStar2)
# assert realResultStar2 == 107036    
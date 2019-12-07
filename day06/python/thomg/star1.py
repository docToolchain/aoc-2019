import sys

class Node:
    def __init__(self, name):
        self.name = name
        self.children = []

class Tree:
    def __init__(self, node):
        self.root = node
    
    def getTreeSize(self, nodeDict):
        count = 0
        for name in self.root.children:
            count += 1
            subTree = Tree(nodeDict[name])
            count += subTree.getTreeSize(nodeDict)
        return count

with open(sys.argv[1]) as file:
    orbitsFile = file.readlines()
COM = Node("COM")
orbitTree = Tree(COM)
nodeDict = {"COM": COM}

for line in orbitsFile:
    line = line.strip("\n")
    (orbitee, orbiter) = line.split(")")
    if orbitee in nodeDict:
        if orbiter not in nodeDict:
            orbiterNode = Node(orbiter)
            nodeDict[orbiter] = orbiterNode
        nodeDict[orbitee].children.append(orbiter)
    else:
        print("Don't know {} yet, skipping relation {}({} and moving it to the end of the queue".format(orbitee, orbitee, orbiter))
        orbitsFile.append(orbitee+")"+orbiter)

orbitCount = 0
for key in nodeDict:
    tree = Tree(nodeDict[key])
    orbitCount += tree.getTreeSize(nodeDict)
print(orbitCount)
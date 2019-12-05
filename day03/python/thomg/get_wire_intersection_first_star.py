def extractEdges(wireAsStringList):
    x = 0
    y = 0
    pointList = []
    for item in wireAsStringList:
        direction = item[0]
        length = int(item[1:])
        if direction == "R":
            for i in range(x, x+length+1):
                pointList.append((i,y))
            x += length
        elif direction == "U":
            for i in range(y, y+length+1):
                pointList.append((x,i))
            y += length
        elif direction == "L":
            for i in range(x, x-length-1, -1):
                pointList.append((i,y))
            x -= length
        elif direction == "D":
            for i in range(y, y-length-1, -1):
                pointList.append((x,i))
            y -= length
        else:
            print("ERROR: read unknown direction ("+direction+")")
    print(len(pointList))
    return pointList

def distOfClosestIntersection(wire1,wire2):
    intersectionList = set(wire1).intersection(wire2)
    print(intersectionList)
    min_dist = 99999
    for point in intersectionList:
        dist = abs(point[0])+abs(point[1])
        if dist != 0 and dist < min_dist:
         min_dist = dist
    return min_dist

with open("test_wire_1") as file:
    testWire1 = file.readlines()[0]
with open("test_wire_2") as file:
    testWire2 = file.readlines()[0]
testWire1 = testWire1.split(",")
testWire2 = testWire2.split(",")
#print(testWire1)
testWire1 = extractEdges(testWire1)
#print(testWire2)
testWire2 = extractEdges(testWire2)
print("dist of first two test wires: "+str(distOfClosestIntersection(testWire1,testWire2)))

with open("wire_1") as file:
    wire1 = file.readlines()[0]
with open("wire_2") as file:
    wire2 = file.readlines()[0]
wire1 = wire1.split(",")
wire2 = wire2.split(",")
wire1 = extractEdges(wire1)
wire2 = extractEdges(wire2)
print("solution for star 1: "+str(distOfClosestIntersection(wire1,wire2)))
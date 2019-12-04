def extractEdges(wireAsStringList):
    x = 0
    y = 0
    steps = 0
    pointDict = {}
    print(len(wireAsStringList))
    for item in wireAsStringList:
        direction = item[0]
        length = int(item[1:])
        if direction == "R":
            for i in range(x+1, x+length+1):
                steps += 1
                pointDict[steps] = (i,y)
            x += length
        elif direction == "U":
            for i in range(y+1, y+length+1):
                steps += 1
                pointDict[steps] = (x,i)
            y += length
        elif direction == "L":
            for i in range(x-1, x-length-1, -1):
                steps += 1
                pointDict[steps] = (i,y)
            x -= length
        elif direction == "D":
            for i in range(y-1, y-length-1, -1):
                steps += 1
                pointDict[steps] = (x,i)
            y -= length
        else:
            print("ERROR: read unknown direction ("+direction+")")
    #print(pointList)
    return pointDict

def distOfClosestIntersection(wire1,wire2):
    wire1PointList=[]
    for key, value in wire1.items():
        wire1PointList.append(value)
    
    wire2PointList=[]
    for key, value in wire2.items():
        wire2PointList.append(value)
    print("{}, {}".format(len(wire1PointList), len(wire2PointList)))
    intersectionList = set(wire1PointList).intersection(wire2PointList)
    print(intersectionList)
    
    min_dist = 99999
    for point in intersectionList:
        if point[0] != point[1]:
            dist = 0
            # I know I should have defined key/values of the dicts the other way around
            # but its past 10 P.M. so I'll just iterate over the dicts again
            for key, value in wire1.items():
                if value[0] == point[0] and value[1] == point[1]:
                    dist += key
                    break
            for key, value in wire2.items():
                if value[0] == point[0] and value[1] == point[1]:
                    dist += key
                    break
        if dist < min_dist: 
            min_dist = dist
    return min_dist

with open("wire_1") as file:
    wire1 = file.readlines()[0]
with open("wire_2") as file:
    wire2 = file.readlines()[0]
wire1 = wire1.split(",")
wire2 = wire2.split(",")
wire1 = extractEdges(wire1)
wire2 = extractEdges(wire2)
print("solution for star 2: "+str(distOfClosestIntersection(wire1,wire2)))
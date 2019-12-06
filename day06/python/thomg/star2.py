import sys

with open(sys.argv[1]) as file:
    orbitsFile = file.readlines()

nodeDict = {}
for line in orbitsFile:
    line = line.strip("\n")
    (orbitee, orbiter) = line.split(")")
    nodeDict[orbiter] = orbitee

youObj = "YOU"
sanObj = "SAN"
YOUToCOMPath = []
SANToCOMPath = []
while(youObj != "COM"):
     youObj = nodeDict[youObj]
     YOUToCOMPath.append(youObj)
while(sanObj != "COM"):
     sanObj = nodeDict[sanObj]
     SANToCOMPath.append(sanObj)

# The shortest path from YOU to SAN should be attainable by conjoining the paths from 
# either end to COM at the node where they first meet. So the number of jumps should 
# be the number of nodes from the superset of both paths minus the number of nodes that
# belong to both paths, which is the number of nodes in the inverse of the intersection
# of both paths as node sets
print(len(set(YOUToCOMPath)^set(SANToCOMPath)))
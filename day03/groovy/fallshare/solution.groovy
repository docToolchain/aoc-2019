import  groovy.lang.Tuple

// tag::readFile[]
 String[][] readFile(String filePath) {
    File file = new File(filePath)
    def lines = file.readLines()

    def wire_instructions = [][]
    wire_instructions[0] = lines[0].split(",")
    wire_instructions[1] = lines[1].split(",")
    return wire_instructions
}
// end::readFile

// tag::matrixDimensions[]
Integer[][] getMatrixDimensions(String[] intstructions)
{
    int x = 0
    int y = 0
    x_list = []
    y_list = []
    intstructions.each{ instruction ->
        operator = instruction[0]
        value = instruction.substring(1).toInteger()
        switch (operator) {
            case "L":
                x -= value
                break
            case "R":
                x += value
                break
            case "U":
                y += value
                break
            case "D":
                y -= value
                break    
        }
        x_list << x
        y_list << y
    }
    x_min = x_list.min()
    x_max = x_list.max()
    y_min = y_list.min()
    y_max = y_list.max()

    def dimensions = [][]
    dimensions << [x_min, x_max]
    dimensions << [y_min, y_max]

    //i don't like to return an list of lists but it gets the job done for the time being
    return dimensions
}
// end::matrixDimensions[]

// tag::MinMaxDimensions[]
Integer[][] getMatrixMinMax(first, second){
    minMaxMatrix = [[0,0],[0,0]]
    for(i = 0; i < first[0].size(); i++){
          minMaxMatrix[i][0] = (first[i][0] < second[i][0]) ? first[i][0]  : second[i][0] 
          minMaxMatrix[i][1] = (first[i][1] > second[i][1]) ? first[i][1] : second[i][1] 
    }
    return minMaxMatrix
}
// end::MinMaxDimensions[]

Integer[][] createWirePath(intstructions, centralPort, matrixDimensions)
{
    matrix = new Integer[matrixDimensions[0] + 1][matrixDimensions[1] + 1] 

    int x = centralPort[0]
    int y = centralPort[1]
    intstructions.each{ instruction ->
        operator = instruction[0]
        value = instruction.substring(1).toInteger()
        switch (operator) {
            case "L":
                matrix = moveLeft(matrix, value, x, y)
                x -= value              
                break
            case "R":                
                matrix = moveRight(matrix, value, x, y)
                x += value
                break
            case "U":
                matrix = moveUp(matrix, value, x, y)
                y += value
                break
            case "D":
                matrix = moveDown(matrix, value, x, y)
                y -= value
                break    
        }
    }
    return matrix
}

Integer[][] moveRight(matrix, steps, x, y){
    for(int i = 1; i <= steps; i++)    {
        matrix[x + i][y] = 1
    }
    return matrix
}

Integer[][] moveLeft(matrix, steps, x, y){
    for(int i = 1; i <= steps; i++)    {
        matrix[x - i][y] = 1
    }
    return matrix
}

Integer[][] moveUp(matrix, steps, x, y){
    for(int i = 1; i <= steps; i++)    {
        matrix[x][y + i] = 1
    }
    return matrix
}

Integer[][] moveDown(matrix, steps, x, y){
    for(int i = 1; i <= steps; i++)    {
        matrix[x][y - i] = 1
    }
    return matrix
}

// tag::getCrossings[]
def getCrossings(matrix1, matrix2, matrixDimensions)
{
    def intersections = []
    for(int i = 0; i < matrixDimensions[0]; i++){
        for(int j = 0; j < matrixDimensions[1]; j++){
            if((matrix1[i][j] == 1 ) && (matrix1[i][j] == matrix2[i][j] ))
                intersections << [i,j] 
        }
    }

    return intersections
}
// end::getCrossings[]

Integer getSmallestDistance(crossings, centralPort){
    min = 100000
    minI = 0
    for(int i = 0; i < crossings.size(); i++){
        dist = calcManhattanDistance(crossings[i], centralPort)
        if(dist < min){
            min = dist
            minI = i
        }            
    }

    println "Closest intersection is: ${crossings[i][0]} | ${crossings[i][1]}"
    return min
}


Integer calcManhattanDistance(intersection, centralPort){
    xDistance = Math.abs(intersection[0] - centralPort[0])
    yDistance = Math.abs(intersection[1] - centralPort[1])
    return xDistance + yDistance
}

wire_instructions = readFile("input.txt")

firstWireDimensions = getMatrixDimensions(wire_instructions[0])
secondWireDimensions = getMatrixDimensions(wire_instructions[1])

minMaxMatrix = getMatrixMinMax(firstWireDimensions, secondWireDimensions)

//By executin until here I know now that both on x and y axis the min values are negative
centralPort = [(-1 * minMaxMatrix[0][0]), (-1 * minMaxMatrix [1][0])]
matrixDimensions = [(-1 * minMaxMatrix[0][0]) + minMaxMatrix[0][1], (-1 * minMaxMatrix [1][0]) + minMaxMatrix[1][1]]
println "Matrix dimensions: ${matrixDimensions}"
println "location of central port: ${centralPort}"

firstWirePath = createWirePath(wire_instructions[0],centralPort, matrixDimensions)
secondWirePath = createWirePath(wire_instructions[1],centralPort, matrixDimensions)

crossings = getCrossings(firstWirePath, secondWirePath, matrixDimensions)
println "Smalles distance is: " + getSmallestDistance(crossings, centralPort)
println "End Star 1"

println "Star 2:"
println "Short total distance to intersection: " + getPointWithShortestTotalDistance(wire_instructions, crossings, centralPort)

def getPointWithShortestTotalDistance(wire_instructions, crossings, centralPort){
    min = 10000000
    minI = 0
    for(int i = 0; i < crossings.size(); i++){
        distWire1 = getDistanceToPoint(wire_instructions[0], crossings[i][0], crossings[i][1], centralPort)
        distWire2 = getDistanceToPoint(wire_instructions[1], crossings[i][0], crossings[i][1], centralPort)
        dist = distWire1 + distWire2

        if(dist < min){
            min = dist
            minI = i
        }            
    }

    println "Closest intersection with the smalles distance: ${crossings[i][0]} | ${crossings[i][1]}"
    return min
}

Integer getDistanceToPoint(instructions, xInter, yInter, centralPort)
{
    int x = centralPort[0] 
    int y = centralPort[1] 
    int totalDist = 0

    for(int i = 0; i < instructions.size(); i++){
        instruction = instructions[i]
        operator = instruction[0]
  
        value = instruction.substring(1).toInteger()
        switch (operator) {
            case "L":
                for(int j = 1; j <= value; j++){
                    x--
                    if((xInter == x)&&(yInter == y)){
                        return totalDist + j                  
                    }
                }
                break
            case "R":
                for(int j = 1; j <= value; j++){
                    x++
                    if((xInter == x)&&(yInter == y)){
                        return totalDist + j                
                    }
                }
                break
            case "U":
                for(int j = 1; j <= value; j++){
                    y++
                    if((xInter == x)&&(yInter == y)){
                        return totalDist + j       
                    }
                }
                break
            case "D":
                for(int j = 1; j <= value; j++){
                    y--
                    if((xInter == x)&&(yInter == y)){
                        return totalDist + j                   
                    }
                }
                break    
        }
        totalDist += value
    }
    return totalDist
}

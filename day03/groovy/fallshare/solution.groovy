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
    println "x min: ${x_min}; x max: ${x_max};"
    println "y min: ${y_min}; y max: ${y_max};"

    // this is not a nice solution but gets the job done for the time being
    def dimensions = [][]
    dimensions << [x_min, x_max]
    dimensions << [y_min, y_max]

    return dimensions
}

Integer[][] getMatrixMinMax(first, second){
    minMaxMatrix = [[0,0],[0,0]]
    for(i = 0; i < first[0].size(); i++){
         println "Test: ${second[i][0]} : ${second[i][1]} "
          minMaxMatrix[i][0] = (first[i][0] < second[i][0]) ? first[i][0]  : second[i][0] 
          minMaxMatrix[i][1] = (first[i][1] > second[i][1]) ? first[i][1] : second[i][1] 
    }
    return minMaxMatrix
}


Integer[][] createWirePath(intstructions, centralPort, matrixDimensions)
{
    //TODO there is a of by one error somewhere which creates an array out of bound exception
    matrix = new Integer[matrixDimensions[0]+1][matrixDimensions[1]+1] 

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
        println "matrix first point is: " + matrix[4226][5000]
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

wire_instructions = readFile("input.txt")
println wire_instructions[0]
println "--------------"
println wire_instructions[1]

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
println "End"

Integer getSmallestDistance(crossings, centralPort){
    min = 100000
    minI = 0
    for(int i = 0; i < crossings.size(); i++){
        println "crossing ${i} is: " + crossings[i]
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
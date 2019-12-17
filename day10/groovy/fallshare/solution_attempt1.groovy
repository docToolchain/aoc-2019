    // tag::readFile[]
    char[][] readFile(String filePath) {
      /*  File file = new File(filePath)
        String fileContent = file.text
        def contentArray = fileContent.tokenize('')*
        return contentArray*/
        spaceMap = [][]

        File file = new File(filePath)
        def lines = file.readLines()
        for(int i = 0; i < lines.size; i++){
            spaceMap[i] = lines[i].split('')
        }
        return spaceMap
    }
    // end::readFile[]

spaceMap = readFile("input.txt")

xMax = spaceMap[0].size()
yMax = spaceMap.size()
println "xMax: $xMax"
println "yMax: $yMax"

maxAstroidCount = 0
xBestFit = 0
yBestFit = 0

for(int x = 0; x < xMax; x++ ){
    for(int y = 0; y < yMax; y++ ){
        if(spaceMap[y][x] == "#"){
            astroidCount = spaceRadar(spaceMap, x, y)
            println "$x:$y = $astroidCount"
            if(astroidCount > maxAstroidCount){
                maxAstroidCount = astroidCount
                xBestFit = x
                yBestFit = y
            }
        }
    }
} 

println "Astroid $xBestFit|$yBestFit is able to see: $maxAstroidCount"
println "Star 2:"
println spaceRadar(spaceMap, 20, 18)



def printMap(map){
    for(int i = 0; i < map.size(); i++){
        println map[i]
    }
    println "----------------------"
}


Integer spaceRadar(spaceMap, x, y){
    xMax = spaceMap[0].size()
    yMax = spaceMap.size()

    def coveredMap = new Integer[yMax][xMax]

    //init mit 0
    for(int i = 0; i < coveredMap.size(); i++){
         for(int j = 0; j < coveredMap[0].size(); j++){
            coveredMap[i][j] = 0
         }
    }

    spaceToLeft = (-1 * x)
    spaceToRight = xMax - x
    spaceToTop = -y
    spaceToBottom =  yMax - y

    asteroidCnt = 0

    //needs rework
    //quadrant 1
    asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, 0, -1)

    for(int dx = 0; dx < spaceToRight; dx++){
        for(int dy = 0; dy >= spaceToTop; dy--){
            if(isBeamValid(coveredMap, x, y, dx, dy)){
                asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt,  x, y, dx, dy)
            }
        }
    }

    asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, 1, 0)

     for(int dx = 0; dx < spaceToRight; dx++){
        for(int dy = 0; dy < spaceToBottom; dy++){
            if(isBeamValid(coveredMap, x, y, dx, dy)){
                asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, dx, dy)
            }
        }
    }

    asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, 0, 1)

    for(int dx = 0; dx >= spaceToLeft; dx--){
        for(int dy = 0; dy < spaceToBottom; dy++){
            if(isBeamValid(coveredMap, x, y, dx, dy)){
                asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, dx, dy)
            }
        }
    }

    asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, -1, 0)

    for(int dx = 0; dx >= spaceToLeft; dx--){
        for(int dy = 0; dy >= spaceToTop; dy--){
            if(isBeamValid(coveredMap, x, y, dx, dy)){
                asteroidCnt += spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, dx, dy)
            }
        }
    }
    return asteroidCnt

}

Boolean isBeamValid(coveredMap, x, y, dx, dy){
    //coveredMap

    //ignore horizontal and vertical beams since they are a special case
    if((dx.abs() == 1) && (dy.abs() == 1)){ 
        return true
    }

    //ignore horizontal and vertical beams since they are a special case
    if((dx == 0) || dy == 0){ 
        return false
    }
    //ignore 45° beams since they are a special case
    if(dx.abs() == dy.abs()){
        return false
    }

    if(dx.abs() < dy.abs()){
        s = dx
        b = dy
    } else {
        s = dy
        b = dx
    }
    
    if(coveredMap[y+dy][x+dx] != 1){
        return true
    }
    else{
      //  println "$dx:$dy - already scanned"
    }

    return false
}

Integer spaceBeam(spaceMap, coveredMap, asteroidCnt, x, y, dx, dy){
    xMax = spaceMap[0].size()
    yMax = spaceMap.size()
    int sX = (x + dx);
    int sY = (y + dy);

    int astroidFound = 0
    while((sX < xMax) && (sX >= 0) && (sY < yMax) && (sY >= 0)){
        if(spaceMap[sY][sX] == "#"){
            if((astroidFound == 0) && (asteroidCnt == 199)){
                println "200 destroyed astroid is: $sX:$sY keyword: ${(sX * 100 + sY)}"
            }
            astroidFound = 1
        }
        coveredMap[sY][sX] = 1
        sX += dx
        sY += dy
    }    

    return astroidFound
}
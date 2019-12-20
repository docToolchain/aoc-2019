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
println "\n\n"
println "Star 2:"
println spaceRadar(spaceMap, 20, 18)



Integer scanHalfSpace(spaceMap, asteroidCnt, x, y, xMin, xMax){
    astroids = [:]
    for(int xCur = xMin; xCur < xMax; xCur++ ){
        for(int yCur = 0; yCur < yMax; yCur++ ){
            if(spaceMap[yCur][xCur] == "#"){
                def astroid = new Tuple(xCur , yCur)
                BigDecimal gradient 
                def distance = (yCur - y) * (yCur - y) + (xCur - x) * (xCur - x)
                if(!((xCur == x) && (yCur == y))){
                    if((xCur - x) != 0){
                        gradient = (yCur - y) / (xCur - x)                        
                    } else {
                        if((yCur - y) < 0){
                            gradient = 1000
                        } else {
                            gradient = -1000
                        }
                    }
                    astroids[astroid] = ["gradient" : gradient, "distance" : distance]
                }   
               
            }
        }
    }
    
    //group map by gradient as key and sort keys
    def gradMap = astroids.groupBy{it.value.gradient}.sort()
    gradMap.each{ key, value->
        asteroidCnt++
               
        if(asteroidCnt == 200){
            //sort all astroids with same gradient by distance
            distanceSorted = value.groupBy{it.value.distance}.sort()
            //get the first astroid which is the closes one
            closestAstroid = distanceSorted.get(distanceSorted.keySet().toArray()[0])  
            println "[200] Astroid to be vaporized: ${closestAstroid.keySet().toArray()[0]}"
        }            
    }
    return asteroidCnt
}

Integer spaceRadar(spaceMap, x, y){
    xMax = spaceMap[0].size()
    yMax = spaceMap.size()
    asteroidCnt = 0

    asteroidCnt = scanHalfSpace(spaceMap, asteroidCnt, x, y, x, xMax)
    asteroidCnt = scanHalfSpace(spaceMap, asteroidCnt, x, y, 0, x)
    
    return asteroidCnt
}

def readFile(String filePath) {
    File file = new File(filePath)
    String fileContent = file.text
    contentArray = fileContent.split('') as List
    return contentArray
}

	
def rawData = readFile("input.txt")

def width = 25
def heigth = 6

def pixelPerLayer = width * heigth

def numberOfLayers = rawData.size() / pixelPerLayer

println "number of layers: " + numberOfLayers

//save rawdata in layers
def imageLayers = [][]
for(int i = 0; i < rawData.size(); i += pixelPerLayer)
{
    currentLayer = i / pixelPerLayer
    imageLayers[currentLayer] = rawData.subList(i, i + pixelPerLayer)
}

//iterate through all layers and found smalles occurence of 0
minZero= 150
minZeroLayer = 1000
for(int j = 0; j < imageLayers.size(); j++)
{
    zeroCount = imageLayers[j].count('0')
    if(zeroCount < minZero)
    {
        minZero = zeroCount
        minZeroLayer = j
    }
}
println "Layer $minZeroLayer has with $minZero the smalles number of 0s!"
println "Star 1 solution is: " + (imageLayers[minZeroLayer].count("1") * imageLayers[minZeroLayer].count("2"))

def finalImage = new Integer[heigth][width]

for(int h = 0; h < heigth; h++){
    for(int w = 0; w < width; w++){
        for(int l = 0; l < numberOfLayers; l++)
        {
            def cursor = (width * h) + w
            if(imageLayers[l][cursor] == "0"){
                finalImage[h][w] = 0
                break
            } else if(imageLayers[l][cursor] == "1"){
                finalImage[h][w] = 1
                break
            } else {
                finalImage[h][w] = 2
            }
        }
    }
}

finalImage.each{ line ->
    line.each{ character ->
        if(character == 0){
            print " "
        } else if (character == 1) {
            print "#"
        }
    }
    print "\n"
}


class SpaceObject {
    String name;
    //the spaceObject this object is orbiting around
    SpaceObject centerOfOrbit = null;
    //a list of all objects that orbit arund this object
    def orbitingObjects = []

    SpaceObject(name) { 
        this.name = name
        println "${this.name} created!"
    }

    String getName(){
        return name
    }

    void setCenterOfOrbit(SpaceObject coO) {
      centerOfOrbit = coO;
    }

    void addOrbitingObject(SpaceObject orbitingObject) {
      orbitingObjects.add(orbitingObject);
    }

    //the number of indirect orbits is actually the distance to the COM object
    // -1 for the direct orbit to the closes parent
    Integer getIndirectOrbitCount(){
        return getDistanceToCOM() - 1
    }

    Integer getDistanceToCOM(){
        if(centerOfOrbit){
            return centerOfOrbit.getDistanceToCOM() + 1
        } else {
            return 0
        }
    }

    String[] getPathToCom(){
        def path = []

        if(centerOfOrbit){            
            path = centerOfOrbit.getPathToCom()
            path += name
        } else {
            path = [name]
        }
        return path
    }

}

planet = new SpaceObject("Mars")

class SpaceCreator{

    private objectMap = [:]
    
    SpaceCreator(String filePath){
        File file = new File(filePath)

        file.eachLine { line ->
            def objects = line.split("\\)")
            SpaceObject orbitCenter = getSpaceObject(objects[0])
            SpaceObject orbitingObject = getSpaceObject(objects[1])

            orbitingObject.setCenterOfOrbit(orbitCenter)
            orbitCenter.addOrbitingObject(orbitingObject)
        }
    }

    SpaceObject getSpaceObject(String name){
        if(objectMap.containsKey(name)) {
            return objectMap[name]
        } else {
            SpaceObject spaceObject = new SpaceObject(name)
            objectMap.put(name, spaceObject)
            return spaceObject
        }
    }

    Integer getSpaceObjectCount(){
        return objectMap.keySet().size()
    }

    Integer getIndirectOrbitCount(){
        int indirectOrbitCount = 0
        objectMap.each{ objectName, spaceObject -> 
            indirectOrbitCount += spaceObject.getIndirectOrbitCount()
        }
        return indirectOrbitCount
    }
}



universe  = new SpaceCreator("input.txt")

println "number of space objects: " + universe.getSpaceObjectCount()
println "number of indirect orbits: " + universe.getIndirectOrbitCount()
println "total number of orbits: " + (universe.getSpaceObjectCount() + universe.getIndirectOrbitCount())

SpaceObject me = universe.getSpaceObject("YOU")
String[] myPath = me.getPathToCom()
SpaceObject santa = universe.getSpaceObject("SAN")
String[] santaPath = santa.getPathToCom()
myPath2CommonOrbit = (myPath - santaPath)
santaPath2CommonOrbit = (santaPath - myPath)
println "Star 2:"
println "Travel distance to santa: " + (myPath2CommonOrbit.size() + santaPath2CommonOrbit.size() - 2)
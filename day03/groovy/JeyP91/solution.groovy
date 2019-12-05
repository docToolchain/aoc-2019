#!/usr/bin/env groovy

// Read input
ArrayList<String> pathString = Arrays.asList(new File('input.txt').text.split("\\r?\\n"))
// ArrayList<String> pathString = Arrays.asList(new File('input_test1.txt').text.split("\\r?\\n")) // 610
// ArrayList<String> pathString = Arrays.asList(new File('input_test2.txt').text.split("\\r?\\n")) // 410

// Split up path strings
ArrayList<String> path1 = Arrays.asList(pathString[0].split("\\s*,\\s*"))
ArrayList<String> path2 = Arrays.asList(pathString[1].split("\\s*,\\s*"))

// Convert path (R1, U2, ...) to sections with absolute start and end coordinates
List<Section> sectionsPath1 = convertToSections(path1)
List<Section> sectionsPath2 = convertToSections(path2)

List<Coordinate> intersections = new ArrayList<Coordinate>()

// Iterate over all sections and check if they cross each other
sectionsPath1.each { Section sec1 ->
    sectionsPath2.each { Section sec2 ->

        // Check the two cases where either sec1 is vertical and sec2 horizontal and vice versa.
        // The case where both are either vertical or horizontal does not need to be taken care
        if(sec1.isVertical() && sec2.isHorizontal()) {
            if(
                isInBetween(sec1.getStartX(), sec2.getStartX(), sec2.getEndX())
                && isInBetween(sec2.getStartY(), sec1.getStartY(), sec1.getEndY())
            ) {
                intersections.add(new Coordinate(sec1.getStartX(), sec2.getStartY()))
            }
        }
        else if (sec1.isHorizontal() && sec2.isVertical()) {
            if(
                isInBetween(sec1.getStartY(), sec2.getStartY(), sec2.getEndY())
                && isInBetween(sec2.getStartX(), sec1.getStartX(), sec1.getEndX())
            ) {
                intersections.add(new Coordinate(sec2.getStartX(), sec1.getStartY()))
            }
        }
    }
}


// Calculate and print Manhatten distance for part 1
Integer shortestDistance = null
// tag::distancePart1[]
intersections.each { Coordinate coor ->
    int distance = Math.abs(coor.getX()) + Math.abs(coor.getY())
    if(shortestDistance == null || distance < shortestDistance) {
        shortestDistance = distance
    }
}
// end::distancePart1[]
println "Solution Part 1: Manhattan distance: " + shortestDistance

// Calculate and print fewest combined steps for part 1
shortestDistance = null
intersections.each { Coordinate coor ->
    int distance = getStepsToPointOnSection(coor, sectionsPath1) + getStepsToPointOnSection(coor, sectionsPath2)
    if(shortestDistance == null|| distance < shortestDistance) {
        shortestDistance = distance
    }
}
println "Solution Part 2: Fewest combined steps: " + shortestDistance

// Convert path (R1, U2, ...) to sections with absolute start and end coordinates
List<Section> convertToSections (List<String> path) {
    List<Section> sections = new ArrayList<Section>()
    
    // add temporary start section as reference for loop
    sections.add(new Section(0,0,0,0,0))
    for(int i = 0; i < path.size(); i++) {

        // get direction from first character of string
        String direction = path[i].substring(0, 1)

        // get length of section from the number 
        int length = Integer.parseInt(path[i].substring(1, path[i].length()))
        
        int lastX = sections.get(sections.size() - 1).getEndX()
        int lastY = sections.get(sections.size() - 1).getEndY()
        switch(direction) {
            case "U":
                sections.add(new Section(lastX, lastX, lastY, lastY + length, length))
                break
            case "R":
                sections.add(new Section(lastX, lastX + length, lastY, lastY, length))
                break
            case "D":
                sections.add(new Section(lastX, lastX, lastY, lastY - length, length))
                break
            case "L":
                sections.add(new Section(lastX, lastX - length, lastY, lastY, length))
                break
        }
    }

    // remove temporary start section
    sections.remove(0)

    return sections
}

// Follow wire until intersection is found
int getStepsToPointOnSection(Coordinate coor, List<Section> sections) {
    // tag::distancePart2[]
    int length = 0
    for(int i = 0; i < sections.size(); i++) {
        if(sections[i].isCoordinateOnSection(coor)) {
            if(coor.getX() == sections[i].getStartX() && coor.getX() == sections[i].getEndX()) {
                length += Math.abs(sections[i].getStartY() - coor.getY())
            }
            else if (coor.getY() == sections[i].getStartY() && coor.getY() == sections[i].getEndY()) {
                length += Math.abs(sections[i].getStartX() - coor.getX())
            }
            return length
        }
        else {
            length += sections[i].getLength()
        }
    }
    // end::distancePart2[]
}

// Helper function to check if a value "a" is in between two other values "b", "c" independently of sign
boolean isInBetween(int a, int b, int c) {
    if(a < b && a > c) {
        return true
    }
    if(a > b && a < c) {
        return true
    }
    return false
}

// Class to just hold X and Y
class Coordinate {
    private int x
    private int y
    public Coordinate(int x, int y){
        this.x = x
        this.y = y
    }
    public int getX() {return this.x}
    public int getY() {return this.y}
}

// Class to hold section information (start coordinate, end coordinate, length)
class Section {
    private int startX
    private int endX
    private int startY
    private int endY
    private int length
    public Section(int startX, int endX, int startY, int endY, length){
        this.startX = startX
        this.endX = endX
        this.startY = startY
        this.endY = endY
        this.length = length
    }
    public int getStartX() {return startX}
    public int getStartY() {return startY}
    public int getEndX() {return endX}
    public int getEndY() {return endY}
    public int getLength() {return length}

    public void switchX() {
        int temp = this.startX
        this.startX = this.endX
        this.endX = temp
    }

    public void switchY() {
        int temp = this.startY
        this.startY = this.endY
        this.endY = temp
    }

    public boolean isCoordinateOnSection(Coordinate coor) {
        if(
            (coor.getX() == this.getStartX() && coor.getX() == this.getEndX())
            && (
                (coor.getY() > this.getStartY() && coor.getY() < this.getEndY())
                || (coor.getY() < this.getStartY() && coor.getY() > this.getEndY())
            )
        ){
            return true
        }
        else if(
            coor.getY() == this.getStartY() && coor.getY() == this.getEndY()
            && (
                (coor.getX() >= this.getStartX() && coor.getX() <= this.getEndX())
                || (coor.getX() <= this.getStartX() && coor.getX() >= this.getEndX())
            )
        ) {
            return true
        }
        else {
            return false
        }
    }

    public boolean isVertical() {
        return this.getStartX() == this.getEndX()
    }
    
    public boolean isHorizontal() {
        return this.getStartY() == this.getEndY()
    }
}
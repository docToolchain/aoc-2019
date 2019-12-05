#!/usr/bin/env groovy

// Read input
ArrayList<String> pathString = Arrays.asList(new File('input.txt').text.split("\\r?\\n"))
// ArrayList<String> pathString = Arrays.asList(new File('input_test1.txt').text.split("\\r?\\n")) // 610
// ArrayList<String> pathString = Arrays.asList(new File('input_test2.txt').text.split("\\r?\\n")) // 410

ArrayList<String> path1 = Arrays.asList(pathString[0].split("\\s*,\\s*"))
ArrayList<String> path2 = Arrays.asList(pathString[1].split("\\s*,\\s*"))

List<Section> sectionsPath1 = convertToSections(path1)
List<Section> sectionsPath2 = convertToSections(path2)

List<Coordinate> intersections = new ArrayList<Coordinate>()

sectionsPath1.each { Section sec1 ->
    sectionsPath2.each { Section sec2 ->
        // sec1 -> vertical
        // sec2 -> horizontal
        if(sec1.getStartX() == sec1.getEndX() && sec2.getStartY() == sec2.getEndY()) {
            if(
                isInBetween(sec1.getStartX(), sec2.getStartX(), sec2.getEndX())
                && isInBetween(sec2.getStartY(), sec1.getStartY(), sec1.getEndY())
            ) {
                intersections.add(new Coordinate(sec1.getStartX(), sec2.getStartY()))
            }
        }
        // sec1 -> horizontal
        // sec2 -> vertical
        else if (sec1.getStartY() == sec1.getEndY() && sec2.getStartX() == sec2.getEndX()) {
            if(
                isInBetween(sec1.getStartY(), sec2.getStartY(), sec2.getEndY())
                && isInBetween(sec2.getStartX(), sec1.getStartX(), sec1.getEndX())
            ) {
                intersections.add(new Coordinate(sec2.getStartX(), sec1.getStartY()))
            }
        }
    }
}

Integer shortestDistance = null
intersections.each { Coordinate coor ->

    int distance = getDistanceToPoint(coor, sectionsPath1) + getDistanceToPoint(coor, sectionsPath2)
    if(shortestDistance == null|| distance < shortestDistance) {
        shortestDistance = distance
    }
}

println shortestDistance

List<Section> convertToSections (List<String> path) {
    List<Section> sections = new ArrayList<Section>()
    sections.add(new Section(0,0,0,0))
    for(int i = 0; i < path.size(); i++) {
        String direction = path[i].substring(0, 1)
        int length = Integer.parseInt(path[i].substring(1, path[i].length()))
        
        int lastX = sections.get(sections.size() - 1).getEndX()
        int lastY = sections.get(sections.size() - 1).getEndY()
        switch(direction) {
            case "U":
                sections.add(new Section(lastX, lastX, lastY, lastY + length))
                break
            case "R":
                sections.add(new Section(lastX, lastX + length, lastY, lastY))
                break
            case "D":
                sections.add(new Section(lastX, lastX, lastY, lastY - length))
                break
            case "L":
                sections.add(new Section(lastX, lastX - length, lastY, lastY))
                break
        }
    }
    sections.remove(0)
    return sections
}

int getDistanceToPoint(Coordinate coor, List<Section> sections) {
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
}

boolean isInBetween(int a, int b, int c) {
    if(a < b && a > c) {
        return true
    }
    if(a > b && a < c) {
        return true
    }
    return false
}

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

class Section {
    private int startX
    private int endX
    private int startY
    private int endY
    private int length
    public Section(int startX, int endX, int startY, int endY){
        this.startX = startX
        this.endX = endX
        this.startY = startY
        this.endY = endY
        if(startX == endX) {
            length = Math.abs(startY - endY)
        }
        if(startY == endY) {
            length = Math.abs(startX - endX)
        }
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
}
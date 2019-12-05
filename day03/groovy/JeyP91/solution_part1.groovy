#!/usr/bin/env groovy

// Read input

ArrayList<String> pathString = Arrays.asList(new File('input.txt').text.split("\\r?\\n"))
// ArrayList<String> pathString = Arrays.asList(new File('input_test1.txt').text.split("\\r?\\n")) // 159
// ArrayList<String> pathString = Arrays.asList(new File('input_test2.txt').text.split("\\r?\\n")) // 135
ArrayList<String> path1 = Arrays.asList(pathString[0].split("\\s*,\\s*"))
ArrayList<String> path2 = Arrays.asList(pathString[1].split("\\s*,\\s*"))

List<Section> sectionsPath1 = convertToSections(path1)
List<Section> sectionsPath2 = convertToSections(path2)

List<Coordinate> intersections = new ArrayList<Coordinate>()

sectionsPath1.each { Section sec1 ->
    sectionsPath2.each { Section sec2 ->
        // sec1 -> vertical
        // sec2 -> horizontal
        Section sec1Ordered = orderSection(sec1)
        Section sec2Ordered = orderSection(sec2)
        if(sec1Ordered.getStartX() == sec1Ordered.getEndX() && sec2Ordered.getStartY() == sec2Ordered.getEndY()) {
            if(
                sec1Ordered.getStartX() > sec2Ordered.getStartX()
                && sec1Ordered.getStartX() < sec2Ordered.getEndX()
                && sec2Ordered.getStartY() > sec1Ordered.getStartY()
                && sec2Ordered.getStartY() < sec1Ordered.getEndY()
            ) {
                intersections.add(new Coordinate(sec1Ordered.getStartX(), sec2Ordered.getStartY()))
            }
        }
        // sec1 -> horizontal
        // sec2 -> vertical
        else if (sec1Ordered.getStartY() == sec1Ordered.getEndY() && sec2Ordered.getStartX() == sec2Ordered.getEndX()) {
            if(
                sec1Ordered.getStartY() > sec2Ordered.getStartY()
                && sec1Ordered.getStartY() < sec2Ordered.getEndY()
                && sec2Ordered.getStartX() > sec1Ordered.getStartX()
                && sec2Ordered.getStartX() < sec1Ordered.getEndX()
            ) {
                intersections.add(new Coordinate(sec2Ordered.getStartX(), sec1Ordered.getStartY()))
            }
        }
    }
}

Integer shortestDistance = null
intersections.each { Coordinate coor ->
    int distance = Math.abs(coor.getX()) + Math.abs(coor.getY())
    if(shortestDistance == null || distance < shortestDistance) {
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

Section orderSection(Section sec) {
    if(sec.getEndX() < sec.getStartX()) {
        sec.switchX()
    }
    if(sec.getEndY() < sec.getStartY()) {
        sec.switchY()
    }
    return sec
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
    public Section(int startX, int endX, int startY, int endY){
        this.startX = startX
        this.endX = endX
        this.startY = startY
        this.endY = endY
    }
    public int getStartX() {return startX}
    public int getStartY() {return startY}
    public int getEndX() {return endX}
    public int getEndY() {return endY}
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
}
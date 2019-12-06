import groovy.transform.*

@EqualsAndHashCode
@ToString
class Coordinate {
    final int x
    final int y

    Coordinate(int x, int y) {
        this.x = x
        this.y = y
    }

    Coordinate next(int x, int y) {
        return new Coordinate(this.x + x, this.y + y)
    }

    int manhattanDistance(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y)
    }
}

def coordinate(int x, int y) {
    return new Coordinate(x, y)
}

assert coordinate(0, 0).manhattanDistance(coordinate(3, -3)) == 6

def wire(String path) {
    def wire = [:]
    def currentPosition = coordinate(0, 0)
    def step = 0
    path.split(',').each { pathStep ->
        int amount = Integer.parseInt(pathStep.substring(1))
        if (pathStep.startsWith('R')) {
            amount.times {
                currentPosition = currentPosition.next(1, 0)
                wire.putIfAbsent(currentPosition, ++step)
            }
        }
        else if (pathStep.startsWith('L')) {
            amount.times {
                currentPosition = currentPosition.next(-1, 0)
                wire.putIfAbsent(currentPosition, ++step)
            }
        }
        else if (pathStep.startsWith('D')) {
            amount.times {
                currentPosition = currentPosition.next(0, 1)
                wire.putIfAbsent(currentPosition, ++step)
            }
        }
        else if (pathStep.startsWith('U')) {
            amount.times {
                currentPosition = currentPosition.next(0, -1)
                wire.putIfAbsent(currentPosition, ++step)
            }
        }
    }
    return wire
}

def combine(def wires) {
    def combined = [:].withDefault { 0 }
    def centralPort = coordinate(0, 0)
    wires.flatten().findAll { it != centralPort }.each { combined[it] = combined[it] + 1 }
    return combined
}

assert wire("R2,D2,L2,U1") == [
    (coordinate(1, 0)): 1,
    (coordinate(2, 0)): 2,
    (coordinate(2, 1)): 3,
    (coordinate(2, 2)): 4,
    (coordinate(1, 2)): 5,
    (coordinate(0, 2)): 6,
    (coordinate(0, 1)): 7,
]
assert wire("R2,D2,L2,U2") == [
    (coordinate(1, 0)): 1,
    (coordinate(2, 0)): 2,
    (coordinate(2, 1)): 3,
    (coordinate(2, 2)): 4,
    (coordinate(1, 2)): 5,
    (coordinate(0, 2)): 6,
    (coordinate(0, 1)): 7,
    (coordinate(0, 0)): 8,
]

def smallestManhattanDistance(List<String> lines) {
    def centralPort = coordinate(0, 0)
    return combine(lines.collect { line -> wire(line) }*.keySet())
        .findAll { entry -> entry.value > 1 }
        .collect { entry -> centralPort.manhattanDistance(entry.key) }
        .sort()
        .first()
}

assert smallestManhattanDistance(["R8,U5,L5,D3", "U7,R6,D4,L4"]) == 6
assert smallestManhattanDistance(["R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"]) == 159
assert smallestManhattanDistance(["R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"]) == 135

println smallestManhattanDistance(new File('input.txt').readLines('UTF-8'))

def smallestSumOfSteps(List<String> lines) {
    def centralPort = coordinate(0, 0)
    def wires = lines.collect { line -> wire(line) }
    return combine(wires*.keySet())
        .findAll { entry -> entry.value > 1 }.keySet()
        .collect { intersection ->
            wires.collect { wire -> wire[intersection] }.sum()
        }.sort().first()
}

assert smallestSumOfSteps(["R8,U5,L5,D3", "U7,R6,D4,L4"]) == 30
assert smallestSumOfSteps(["R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"]) == 610
assert smallestSumOfSteps(["R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"]) == 410

println smallestSumOfSteps(new File('input.txt').readLines('UTF-8'))


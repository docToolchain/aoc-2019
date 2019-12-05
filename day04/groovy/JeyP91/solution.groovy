List<Integer> validNumbersPart1 = new ArrayList<Integer>()
List<Integer> validNumbersPart2 = new ArrayList<Integer>()

for (int number = 235741; number <= 706948; number++) {
// for (int number = 123666; number <= 123666 ; number++) { 
    List<Integer> positions = new ArrayList<Integer>()
    positions[5] = (number - (number % 100000)) / 100000
    positions[4] = (number - positions[5] * 100000 - (number % 10000)) / 10000
    positions[3] = (number - positions[4] * 10000 - positions[5] * 100000 - (number % 1000)) / 1000
    positions[2] = (number - positions[3] * 1000 - positions[4] * 10000 - positions[5] * 100000 - (number % 100)) / 100
    positions[1] = (number - positions[2] * 100 - positions[3] * 1000 - positions[4] * 10000 - positions[5] * 100000 - (number % 10)) / 10
    positions[0] = (number - positions[1] * 10 - positions[2] * 100 - positions[3] * 1000 - positions[4] * 10000 - positions[5] * 100000)
    if(checkDoubleNumber(positions) && checkNeverDecrease(positions)) {
        validNumbersPart1.add(number)
        if(checkIsolatedDoubleNumber(positions)) {
            validNumbersPart2.add(number)
        }
    }
}

println "Solution Part 1: " + validNumbersPart1.size()
println "Solution Part 2: " + validNumbersPart2.size()

boolean checkDoubleNumber(List<Integer> positions) {
    for(int i = 0; i < 5; i++) {
        if(positions[i] == positions[i+1]) {
            return true
        }
    }
    return false
}

boolean checkNeverDecrease(List<Integer> positions) {
    for(int i = 0; i < 5; i++) {
        if(positions[i] < positions [i+1]) {
            return false
        }
    }
    return true
}

boolean checkIsolatedDoubleNumber(List<Integer> positions) {
    for(int i = 1; i < 4; i++) {
        if(positions[i] == positions[i+1] &&
        positions[i] != positions[i-1] &&
        positions[i+1] != positions[i+2]) {
            return true
        }
    }
    if(positions[0] == positions[1] &&
    positions[1] != positions[2]) {
        return true
    }
    if(positions[4] == positions[5] &&
    positions[4] != positions[3]) {
        return true
    }
    return false
}
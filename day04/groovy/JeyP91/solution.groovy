List<Integer> validNumbersPart1 = new ArrayList<Integer>()
List<Integer> validNumbersPart2 = new ArrayList<Integer>()

for (int number = 235741; number <= 706948; number++) {
// for (int number = 123666; number <= 123666 ; number++) { 
    List<Integer> positions = new ArrayList<Integer>()

    // Split up number into single digits
    // tag::splitUpNumber[]
    positions[5] = Math.floor(number / 100000)
    positions[4] = Math.floor(number % 100000 / 10000)
    positions[3] = Math.floor(number % 10000 / 1000)
    positions[2] = Math.floor(number % 1000 / 100)
    positions[1] = Math.floor(number % 100 / 10)
    positions[0] = number % 10
    // end::splitUpNumber[]

    // Add number to list of successfully checked numbers if rules are followed
    // tag::iterateAllNumbers[]
    if(checkDoubleNumber(positions) && checkNeverDecrease(positions)) {
        validNumbersPart1.add(number)
        if(checkIsolatedDoubleNumber(positions)) {
            validNumbersPart2.add(number)
        }
    }
    // end::iterateAllNumbers[]
}

println "Solution Part 1: " + validNumbersPart1.size()
println "Solution Part 2: " + validNumbersPart2.size()

// Check if at least two adjacent digits are the same
boolean checkDoubleNumber(List<Integer> positions) {
    for(int i = 0; i < 5; i++) {
        if(positions[i] == positions[i+1]) {
            return true
        }
    }
    return false
}

// Check if going from left to right, the digits never decrease
boolean checkNeverDecrease(List<Integer> positions) {
    
    // tag::checkNeverDecrease[]
    for(int i = 0; i < 5; i++) {
        if(positions[i] < positions [i+1]) {
            return false
        }
    }
    return true
    // end::checkNeverDecrease[]
}

// Check if the two adjacent matching digits are not part of a larger group of matching digits
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
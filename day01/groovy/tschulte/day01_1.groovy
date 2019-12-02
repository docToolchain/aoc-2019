def calculateFuel(int mass) {
    return (int)(mass / 3) - 2
}

def fuel = 0
new File('input.txt').eachLine('UTF-8') { line ->
    fuel += calculateFuel(Integer.parseInt(line))
}
println fuel

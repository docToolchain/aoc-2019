// tag::calculateFuelRecursive[]
def calculateFuel(int mass) {
    int fuel = (int)(mass / 3) - 2
    return fuel > 0 ? fuel + calculateFuel(fuel) : 0
}
// end::calculateFuelRecursive[]

def fuel = 0
new File('input.txt').eachLine('UTF-8') { line ->
    fuel += calculateFuel(Integer.parseInt(line))
}
println fuel


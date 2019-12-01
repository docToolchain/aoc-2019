
int calculateFuelForMass(int mass){
    (mass / 3).trunc() - 2
}    


int calculateTotalRequiredFuel(){
    File file = new File("input.txt")
    def totalFuel = 0;
    file.withReader { reader ->
        while ((moduleWeight = reader.readLine()) != null) {
            totalFuel += calculateFuelForMass(moduleWeight.toInteger())
        }
    }
    return totalFuel
}

int calculateRequiredFuelConsideringWeightOfFuel(int mass){   
    int requiredFuel = calculateFuelForMass(mass) 
    // masses / fuel smaller then (1 + 2) * 3 = 9 result in negative values. Which we consider as 0
    if( requiredFuel < 9)    {
        return requiredFuel
    }
    else    {
        return calculateRequiredFuelConsideringWeightOfFuel(requiredFuel) + requiredFuel
    }
}

int calculateTotalRequiredFuelConsideringFuelMass(){
    File file = new File("input.txt")
    def totalFuel = 0;
    file.withReader { reader ->
        while ((moduleWeight = reader.readLine()) != null) {
            totalFuel += calculateRequiredFuelConsideringWeightOfFuel(moduleWeight.toInteger())
        }
    }
    return totalFuel
}

println "Start 1: Total required fuel is: ${calculateTotalRequiredFuel()}"
println "Start 2: Total required fuel taking fuel weight into account is is: ${calculateTotalRequiredFuelConsideringFuelMass()}"
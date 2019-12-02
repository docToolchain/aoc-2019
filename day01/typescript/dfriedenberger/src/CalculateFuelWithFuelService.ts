import { Spacecraft } from "./Spacecraft";
import { Modul } from "./Modul";

class CalculateFuelWithFuelService {
   
    calculateRequiredFuelForSpacecraft(spacecraft : Spacecraft) : number
    {
        let fuel = 0;
        for (var modul of spacecraft.getModules())
            fuel += this.calculateRequiredFuelForModul(modul);
        return fuel;
    }

    calculateRequiredFuelForModul(modul : Modul) : number
    {
        var complete = 0;
        var currentMass = modul.getMass();
        while(true)
        {
            let currentFuel =  Math.floor(currentMass / 3) - 2;
            if(currentFuel <= 0) break;
            complete += currentFuel;
            currentMass = currentFuel;
        }
        return complete;
    }
}

export { CalculateFuelWithFuelService };
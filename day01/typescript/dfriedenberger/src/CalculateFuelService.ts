import { Spacecraft } from "./Spacecraft";
import { Modul } from "./Modul";

class CalculateFuelService {
   
    calculateRequiredFuelForSpacecraft(spacecraft : Spacecraft) : number
    {
        let fuel = 0;
        for (var modul of spacecraft.getModules())
            fuel += this.calculateRequiredFuelForModul(modul);
        return fuel;
    }

    calculateRequiredFuelForModul(modul : Modul) : number
    {
        return Math.floor(modul.getMass() / 3) - 2;
    }
}

export { CalculateFuelService };
import { Spacecraft } from "./Spacecraft";
import { CalculateFuelService } from "./CalculateFuelService";

let calculateFuelService = new CalculateFuelService();
let spacecraft = new Spacecraft();

spacecraft.loadModules("input1.txt");


console.log("My puzzle answer is " + calculateFuelService.calculateRequiredFuelForSpacecraft(spacecraft));

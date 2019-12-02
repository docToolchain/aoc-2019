import { Spacecraft } from "./Spacecraft";
import { CalculateFuelWithFuelService } from "./CalculateFuelWithFuelService";

let calculateFuelService = new CalculateFuelWithFuelService();
let spacecraft = new Spacecraft();

spacecraft.loadModules("input1.txt");


console.log("My puzzle answer is " + calculateFuelService.calculateRequiredFuelForSpacecraft(spacecraft));

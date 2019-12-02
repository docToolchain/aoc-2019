import { expect } from 'chai';
import { Modul } from '../src/Modul';
import { CalculateFuelService } from "../src/CalculateFuelService";

describe("Modul", () => {
    describe("calculateRequiredFuel", () => {
        it("Should return 2 when mass = 12", () => {
            
            let modul = new Modul(12);
            let calculateFuelService = new CalculateFuelService();
            let fuel = calculateFuelService.calculateRequiredFuelForModul(modul);

            expect(fuel).to.equal(2);
        });

        it("Should return 2 when mass = 14", () => {
            
            let modul = new Modul(14);
            let calculateFuelService = new CalculateFuelService();
            let fuel = calculateFuelService.calculateRequiredFuelForModul(modul);

            expect(fuel).to.equal(2);
        });

        it("Should return 654 when mass = 1969", () => {
            
            let modul = new Modul(1969);
            let calculateFuelService = new CalculateFuelService();
            let fuel = calculateFuelService.calculateRequiredFuelForModul(modul);

            expect(fuel).to.equal(654);
        });

        it("Should return 33583 when mass = 100756", () => {
            
            let modul = new Modul(100756);
            let calculateFuelService = new CalculateFuelService();
            let fuel = calculateFuelService.calculateRequiredFuelForModul(modul);

            expect(fuel).to.equal(33583);
        });

    })
});
import { expect } from 'chai';
import { Wire } from "../src/Wire";
import { ShortestDistance } from "../src/ShortestDistance";
import { SignalDelayDistance } from "../src/SignalDelayDistance";


describe("ShortestManhattanDistance", () => {
    
   
    describe("cross and distance", () => {
       
        it("should be 30", () => {
            

            let wire1 = new Wire("R8,U5,L5,D3");
            let wire2 = new Wire("U7,R6,D4,L4");

            let crosses = wire1.cross(wire2);

            let distance = new ShortestDistance(new SignalDelayDistance([wire1.distances,wire2.distances]));
            expect(distance.min(crosses)).to.be.equal(30);
          
        });


        it("should be 610", () => {
            

            let wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
            let wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");

            let crosses = wire1.cross(wire2);

            let distance = new ShortestDistance(new SignalDelayDistance([wire1.distances,wire2.distances]));
            expect(distance.min(crosses)).to.be.equal(610);
          
        });


        it("should be 410", () => {
            
            let wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
            let wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

            let crosses = wire1.cross(wire2);

            let distance = new ShortestDistance(new SignalDelayDistance([wire1.distances,wire2.distances]));
            expect(distance.min(crosses)).to.be.equal(410);
          
        });

    })


});
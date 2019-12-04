import { expect } from 'chai';
import { Wire } from "../src/Wire";
import { Coordinate } from "../src/Coordinate";
import { CoordinateSet } from "../src/CoordinateSet";
import { ShortestDistance } from "../src/ShortestDistance";
import { ManhattanDistance } from "../src/ManhattanDistance";

describe("Wire", () => {
    
    describe("constructor", () => {
        it("should contains some coordinates", () => {
            
            let wire = new Wire("R8,U5,L5,D3");

            expect(wire.contains(0,0)).to.be.false; //Startpoint
            expect(wire.contains(1,0)).to.be.true;

            expect(wire.contains(0,1)).to.be.false;
            expect(wire.contains(1,1)).to.be.false;
        });
    })

    describe("cross", () => {

        it("should have two crosses", () => {
            
            let wire1 = new Wire("R8,U5,L5,D3");
            let wire2 = new Wire("U7,R6,D4,L4");

            let crosses = wire1.cross(wire2);

            expect(crosses.size()).to.equal(2);
           
            expect(crosses.has(new Coordinate(3,3))).to.be.true;
            expect(crosses.has(new Coordinate(6,5))).to.be.true;

        });

    })
});

describe("ShortestManhattanDistance", () => {
    
    describe("distance", () => {
       
        it("should find 3,3", () => {
            
            let distance = new ShortestDistance(new ManhattanDistance());

            let crosses = new CoordinateSet();

            crosses.add(new Coordinate(3,3));
            crosses.add(new Coordinate(6,3));

            expect(distance.min(crosses)).to.be.equal(6);

          
        });
    })

    describe("cross and distance", () => {
       
        it("should be 6", () => {
            
            let distance = new ShortestDistance(new ManhattanDistance());

            let wire1 = new Wire("R8,U5,L5,D3");
            let wire2 = new Wire("U7,R6,D4,L4");

            let crosses = wire1.cross(wire2);
            expect(distance.min(crosses)).to.be.equal(6);
          
        });


        it("should be 159", () => {
            
            let distance = new ShortestDistance(new ManhattanDistance());

            let wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
            let wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");

            let crosses = wire1.cross(wire2);
            expect(distance.min(crosses)).to.be.equal(159);
          
        });


        it("should be 135", () => {
            
            let distance = new ShortestDistance(new ManhattanDistance());

            let wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
            let wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

            let crosses = wire1.cross(wire2);
            expect(distance.min(crosses)).to.be.equal(135);
          
        });

    })


});
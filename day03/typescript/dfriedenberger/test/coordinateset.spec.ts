import { expect } from 'chai';
import { Coordinate } from "../src/Coordinate";
import { CoordinateSet } from "../src/CoordinateSet";

describe("CoordinateSet", () => {
    
    describe("iterator", () => {
        it("should iterate over the set", () => {
            
            let coordinates = new CoordinateSet();

            coordinates.add(new Coordinate(3,4));
            coordinates.add(new Coordinate(7,2));

            expect(coordinates.size()).to.be.equal(2);

            var result = 0;
            for (let coordinate of coordinates) {
                result += coordinate.x + coordinate.y;
            }

            expect(result).to.be.equal(3 + 4 + 7 + 2);


        });
    })
   
});

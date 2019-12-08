import { expect } from 'chai';
import { Orbits } from "../src/Orbits";

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            

            var input = [
                "COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L",
                "L)YOU",
                "I)SAN"
            ];

            let orbits = new Orbits();

            var arrayLength = input.length;
            for (var i = 0; i < arrayLength; i++) { 
                var k = input[i];
                let x = k.indexOf(')');
                let p1 = k.substr(0,x);
                let p2 = k.substr(x+1);
                console.log(x+" => " +p1 + " = " +p2);
                orbits.add(p1,p2);
            }
          
            console.log(orbits.map);

            expect(orbits.find("COM",["YOU","SAN"])).to.equal(4);

        });

        

    })
});
import { expect } from 'chai';
import { Eris } from '../src/Eris';


describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
         
            let eris : Eris = new Eris();
            eris.load([
                "....#",
                "#..#.",
                "#..##",
                "..#..",
                "#...."]);

            eris.dump();

            eris = eris.next();
            
            eris.dump();


            expect(eris.isBug(0,0)).to.be.true;

        });

        it("run simple program1", () => {
            
         
            let eris : Eris = new Eris();
            eris.load([
                ".....",
                ".....",
                ".....",
                "#....",
                ".#..."
            ]);


            let b : number = eris.biodiversity();
            


            expect(b).to.equal(2129920);

        });

    })
});
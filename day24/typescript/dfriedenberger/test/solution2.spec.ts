import { expect } from 'chai';
import { RecursiveEris } from '../src/RecursiveEris';


describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
         
            let eris : RecursiveEris = new RecursiveEris();

            console.log(JSON.stringify(eris.borders));

            eris.load([
                "....#",
                "#..#.",
                "#.?##",
                "..#..",
                "#...."]);

            eris.dumpAll();

            for(let i = 0;i < 10;i++)
                eris = eris.next();
            
            eris.dumpAll();

            expect(eris.countAllBugs()).to.be.equal(99);

        });

    })
});
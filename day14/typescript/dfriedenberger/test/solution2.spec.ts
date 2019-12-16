import { expect } from 'chai';
import { NanoFactory } from '../src/NanoFactory';
import { ChemicalSet } from '../src/ChemicalSet';

describe("Computer", () => {
    describe("run", () => {
      

       

           it("run simple program3", (done) => {
            

                 
                let factory : NanoFactory = NanoFactory.load("testcase1.txt");

                expect(factory.calculateOre(82892753,"FUEL",new ChemicalSet())).to.equal(999999999076);

                done();
           }).timeout(30000);


        
    })
});
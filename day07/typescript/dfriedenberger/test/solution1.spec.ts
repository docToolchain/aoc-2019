import { expect } from 'chai';


import { AmplifierSeries } from "../src/AmplifierSeries";
import { Permutation } from '../src/Permutation';

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            

            let series = new AmplifierSeries([3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0]);
            let output = series.run([4,3,2,1,0]);
       
            
              
            expect(output).to.equal(43210);

        });
        

        it("run simple program2", () => {
            

            let series = new AmplifierSeries([3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0]);
            let max = 0;

            let permutations : Permutation = new Permutation([0,1,2,3,4]);


            for(let permutation of permutations)
            {
                let output = series.run(permutation);
                if(output > max) max = output;
            }
                               
               
       
            
              
            expect(max).to.equal(54321);

        });
        
        
    })
});
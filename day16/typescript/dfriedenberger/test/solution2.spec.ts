import { expect } from 'chai';
import { Pattern } from '../src/Pattern';
import { Calculator } from '../src/Calculator';
import { exists } from 'fs';

describe("Computer", () => {
    describe("run", () => {
        
        it("run Calulator ", () => {
            

            var sequence1 = "12345678".split("").map(i => parseInt(i));
            var sequence2 = "12345678".split("").map(i => parseInt(i));


            let calculator : Calculator = new Calculator();


            for(let i = 0;i < 100;i++)
                sequence1 = calculator.next(sequence1);
            for(let i = 0;i < 100;i++)
                sequence2 = calculator.nextOptimized(sequence2,5);
            console.log(JSON.stringify(sequence1));
            console.log(JSON.stringify(sequence2));

           
            expect(sequence1[5]).to.be.equal(sequence2[5]);
            expect(sequence1[6]).to.be.equal(sequence2[6]);
            expect(sequence1[7]).to.be.equal(sequence2[7]);



        });

        

        it("run Calulator large Example", (done) => {
            
       
            var presequence = "03036732577212944063491565474664".split("").map(i => parseInt(i));

            let l = presequence.length;
            
            var sequence = new Array<number>(l * 10000);
            
            
            for(let i = 0;i < l * 10000;i++)
                sequence[i] = presequence[i % l];

            let calculator : Calculator = new Calculator();

            for(let i = 0;i < 100;i++)
                sequence = calculator.nextOptimized(sequence,303673);
           
            console.log(JSON.stringify(sequence.slice(303672,303682)));
            expect(sequence.slice(303673).join('').substr(0,8)).to.equal("84462026");
            done();
        }).timeout(120000);
        
      

    })
});
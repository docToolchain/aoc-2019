import { expect } from 'chai';
import { Pattern } from '../src/Pattern';
import { Calculator } from '../src/Calculator';
import { exists } from 'fs';

describe("Computer", () => {
    describe("run", () => {
        it("run Pattern with 1 round", () => {
            
            let pattern : Pattern = new Pattern(1);

            var cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(1);
            expect(cmd.factor).to.equal(1);
         
            cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(1);
            expect(cmd.factor).to.equal(0);

          
        });

        it("run Pattern with 1 round", () => {
            let pattern : Pattern = new Pattern(2);

            var cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(1);
            expect(cmd.factor).to.equal(0);
         
            cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(2);
            expect(cmd.factor).to.equal(1);
        });

        it("run Pattern with 8 round", () => {
            let pattern : Pattern = new Pattern(8);

            var cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(7);
            expect(cmd.factor).to.equal(0);
         
            cmd = pattern.next();
            console.log(JSON.stringify(cmd));
            expect(cmd.length).to.equal(8);
            expect(cmd.factor).to.equal(1);
         

          
        });


        it("run Calulator ", () => {
            

            var sequence = "12345678".split("").map(i => parseInt(i));


            let calculator : Calculator = new Calculator();

            var a = calculator.next(sequence);
           
            console.log(JSON.stringify(a));
            expect(a.join('')).to.equal("48226158");

          
        });

        it("run Calulator phase 4", () => {
            

            var sequence = "12345678".split("").map(i => parseInt(i));


            let calculator : Calculator = new Calculator();

            for(let i = 0;i < 4;i++)
                sequence = calculator.next(sequence);
           
            console.log(JSON.stringify(sequence));
            expect(sequence.join('')).to.equal("01029498");
          
        });
        
        it("run Calulator large Example", () => {
            

            var sequence = "80871224585914546619083218645595".split("").map(i => parseInt(i));

            console.log("calculator");

            let calculator : Calculator = new Calculator();

            console.log("next");
            for(let i = 0;i < 100;i++)
                sequence = calculator.next(sequence);
           
            console.log(JSON.stringify(sequence));
            expect(sequence.join('').substr(0,8)).to.equal("24176176");
          
        });
        


    })
});
import { expect } from 'chai';
import { Computer } from "../src/Computer";
import { Program } from "../src/Program";

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
            let computer = new Computer();
            let program = new Program([1002,4,3,4,33]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(4);
            expect(program.get(4)).to.equal(99);

        });
        
        it("run simple program2", () => {
            
            let computer = new Computer();
            let program = new Program([1101,100,-1,4,0]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(4);
            expect(program.get(4)).to.equal(99);

        });
    })
});
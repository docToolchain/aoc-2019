import { expect } from 'chai';
import { Computer } from "../src/Computer";
import { Program } from "../src/Program";

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
            let computer = new Computer();
            let program = new Program([1,0,0,0,99]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(4);
            expect(program.get(0)).to.equal(2);

        });

        it("run simple program2", () => {
            
            let computer = new Computer();
            let program = new Program([2,3,0,3,99]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(4);
            expect(program.get(3)).to.equal(6);

        });

        it("run simple program3", () => {
            
            let computer = new Computer();
            let program = new Program([2,4,4,5,99,0]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(4);
            expect(program.get(5)).to.equal(9801);

        });

        it("run simple program4", () => {
            
            let computer = new Computer();
            let program = new Program([1,1,1,4,99,5,6,0,99]);
            
            while(computer.run(program));
              
            expect(program.getPosition()).to.equal(8);
            expect(program.get(0)).to.equal(30);

        });

    })
});
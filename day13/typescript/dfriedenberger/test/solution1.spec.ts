import { expect } from 'chai';


import { Program } from "../src/Program";
import { Computer } from '../src/Computer';

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            

            let program = new Program([109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]);
            let computer = new Computer(program);
       
            while(computer.run());
            let output = computer.getOutput();
            expect(JSON.stringify(output)).to.equal(JSON.stringify([109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]));

        });
        
        it("run simple program2", () => {
            

            let program = new Program([1102,34915192,34915192,7,4,7,99,0]);
            let computer = new Computer(program);
       
            while(computer.run());

            let output = computer.getLastOutput();

              
            expect(output).to.equal(1219070632396864);

        });

        it("run simple program3", () => {
            

            let program = new Program([104,1125899906842624,99]);
            let computer = new Computer(program);
       
            while(computer.run());

            let output = computer.getLastOutput();

              
            expect(output).to.equal(1125899906842624);

        });
        
        
    })
});
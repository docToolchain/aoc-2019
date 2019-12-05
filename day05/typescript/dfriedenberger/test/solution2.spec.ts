import { expect } from 'chai';
import { Computer } from "../src/Computer";
import { Program } from "../src/Program";

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
            let computer = new Computer();
            let program = new Program([3,9,8,9,10,9,4,9,99,-1,8]);
            
            computer.setInput(8);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });
        
        it("run simple program2", () => {
            
            let computer = new Computer();
            let program = new Program([3,9,8,9,10,9,4,9,99,-1,8]);
            
            computer.setInput(77);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);


        });

        it("run simple program3", () => {
            
            let computer = new Computer();
            let program = new Program([3,9,7,9,10,9,4,9,99,-1,8]);
            
            computer.setInput(7);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });
        
        it("run simple program4", () => {
            
            let computer = new Computer();
            let program = new Program([3,9,7,9,10,9,4,9,99,-1,8]);
            
            computer.setInput(8);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);


        });

        it("run simple program5", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1108,-1,8,3,4,3,99]);
            
            computer.setInput(8);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });
        
        it("run simple program6", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1108,-1,8,3,4,3,99]);
            
            computer.setInput(77);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);


        });

        it("run simple program7", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1107,-1,8,3,4,3,99]);
            
            computer.setInput(7);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });
        
        it("run simple program8", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1107,-1,8,3,4,3,99]);
            
            computer.setInput(8);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);


        });


        it("run simple program9", () => {
            
            let computer = new Computer();
            let program = new Program([3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99]);
            
            computer.setInput(7);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(999);

        });

        it("run simple program10", () => {
            
            let computer = new Computer();
            let program = new Program([3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99]);
            
            computer.setInput(8);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1000);

        });
        it("run simple program11", () => {
            
            let computer = new Computer();
            let program = new Program([3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99]);
            
            computer.setInput(9);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1001);

        });


        it("run simple jump1", () => {
            
            let computer = new Computer();
            let program = new Program([3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9]);
            
            computer.setInput(0);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);

        });

        it("run simple jump2", () => {
            
            let computer = new Computer();
            let program = new Program([3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9]);
            
            computer.setInput(1);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });

        it("run simple jump3", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1105,-1,9,1101,0,0,12,4,12,99,1]);
            
            computer.setInput(0);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(0);

        });

        it("run simple jump4", () => {
            
            let computer = new Computer();
            let program = new Program([3,3,1105,-1,9,1101,0,0,12,4,12,99,1]);
            
            computer.setInput(1);
            while(computer.run(program));
              
            let result = computer.getOutput();
            
            expect(result.length).to.equal(1);
            expect(result[0]).to.equal(1);

        });
    })
});
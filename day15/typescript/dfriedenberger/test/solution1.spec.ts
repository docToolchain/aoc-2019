import { expect } from 'chai';


import { Panel } from "../src/Panel";

describe("Computer", () => {
    describe("run", () => {

        it("run simple program1", () => {
            
            let panel = new Panel();
            panel.set(1);
            
          
            let directionArray = panel.getDirectionForNearestUnknonwField();
            expect(directionArray.length).to.equal(1);


        });
        
        it("run simple program2", () => {
            
            let panel = new Panel();
            panel.set(1);
            
            panel.moveForward(1);
            panel.moveForward(2);
            panel.moveForward(2);
            panel.moveForward(1);

            panel.moveForward(3);
            panel.moveForward(4);
            panel.moveForward(4);
            panel.moveForward(3);

            let directionArray = panel.getDirectionForNearestUnknonwField();
            expect(directionArray.length).to.equal(2);

            panel.moveForward(2);
            panel.moveForward(2);

            expect(panel.x).to.be.equal(0);
            expect(panel.y).to.be.equal(2);

            console.log("-----------------");
            
            expect(panel.getDistance(0,2)).to.equal(2);
        });
        
        
    })
});
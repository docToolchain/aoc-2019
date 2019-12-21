import { expect } from 'chai';
import { TunnelMap } from '../src/TunnelMap';

describe("Computer", () => {
    describe("run", () => {
        
  
        it("run simple program", (done) => {
            
         
            let  map : TunnelMap = new TunnelMap();


            map.load(["###############",
                      "#d.ABC.#.....a#",
                      "######@#@######",
                      "###############",
                      "######@#@######",
                      "#b.....#.....c#",
                      "###############"]);

             let s : number = map.findShortestPath();

             expect(s).to.equal(24);
             done();
        });
           
  
    })
});
import { expect } from 'chai';
import { TunnelMap } from '../src/TunnelMap';

describe("Computer", () => {
    describe("run", () => {
        
      
        it("run simple program1", () => {
            
         
            let  map : TunnelMap = new TunnelMap();

            map.load(["#########","#b.A.@.a#","#########"]);

            let s : number = map.findShortestPath();

            expect(s).to.equal(8);

        });
       
         
        it("run simple program1.1", () => {
            
         
            let  map : TunnelMap = new TunnelMap();

            map.load(["#########",
                      "#b...@.a#",
                      "#########"]);

            let s : number = map.findShortestPath();

            expect(s).to.equal(8);

        });
 

        it("run simple program1.2", () => {
            
         
            let  map : TunnelMap = new TunnelMap();

            map.load(["#########",
                      "#bf.@.ca#",
                      "###d#e###",
                      "#########"]);

            let s : number = map.findShortestPath();

            expect(s).to.equal(13);

        });



        it("run simple program2", (done) => {
            
         
            let  map : TunnelMap = new TunnelMap();


            map.load(["########################",
                        "#f.D.E.e.C.b.A.@.a.B.c.#",
                        "######################.#",
                        "#d.....................#",
                        "########################"]);

             let s : number = map.findShortestPath();

             expect(s).to.equal(86);
             done();
        });
      
     it("run simple program1", () => {
            
         
            let  map : TunnelMap = new TunnelMap();

            map.load(["#########","#b.A.@.a#","#########"]);

            let s : number = map.findShortestPath();

            expect(s).to.equal(8);

        });
        
      
        it("run simple program3", (done) => {
            
         
            let  map : TunnelMap = new TunnelMap();


            map.load(["#################",
                      "#i.G..c...e..H.p#",
                      "########.########",
                      "#j.A..b...f..D.o#",
                      "########@########",
                      "#k.E..a...g..B.n#",
                      "########.########",
                      "#l.F..d...h..C.m#",
                      "#################"]);

                


            let s : number = map.findShortestPath();

            expect(s).to.equal(136);
            done();
        }).timeout(10000);
  
    })
});
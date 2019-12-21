var fs = require('fs');
import { expect } from 'chai';
import { RecursiveMaze } from '../src/RecursiveMaze';

describe("Computer", () => {
    describe("run", () => {
        
      
       
       
      
        
        it("run simple program3", () => {
            
         
            let  map : RecursiveMaze = new RecursiveMaze();



            map.load([  "         A           ",
                        "         A           ",
                        "  #######.#########  ",
                        "  #######.........#  ",
                        "  #######.#######.#  ",
                        "  #######.#######.#  ",
                        "  #######.#######.#  ",
                        "  #####  B    ###.#  ",
                        "BC...##  C    ###.#  ",
                        "  ##.##       ###.#  ",
                        "  ##...DE  F  ###.#  ",
                        "  #####    G  ###.#  ",
                        "  #########.#####.#  ",
                        "DE..#######...###.#  ",
                        "  #.#########.###.#  ",
                        "FG..#########.....#  ",
                        "  ###########.#####  ",
                        "             Z       ",
                        "             Z       "]);

                        console.log("Next = "+JSON.stringify(map.getNext({ x: 9, y: 1, l: 0}))); //=> Erstes Feld
                        console.log("Next = "+JSON.stringify(map.getNext({ x: 9, y: 6, l: 0}))); //=> BC neues Partner protal

                        let s : number = map.findPath("AA","ZZ");


                	    expect(s).to.equal(26);

        });
  

        it("run simple program4", () => {
            
         
            let  map : RecursiveMaze = new RecursiveMaze();



            map.load([  "         A           ",
                        "         A           ",
                        "  #######.#########  ",
                        "  #######.........#  ",
                        "  #######.#######.#  ",
                        "  #######.#######.#  ",
                        "  #######.#######.#  ",
                        "  #####  B    ###.#  ",
                        "BC...##  C    ###.#  ",
                        "  ##.##       ###.#  ",
                        "DE...##    D  ###.#  ",
                        "  #####    E  ###.#  ",
                        "  #########.#######  ",
                        "           Z         ",
                        "           Z         "]);
                 
                        console.log("======> Next (find level 0) : "+JSON.stringify(map.getNext({ x: 2, y: 10, l: 1}))); //2,10,1 => find level 0

                        
                        let s : number = map.findPath("AA","ZZ");


                	    expect(s).to.equal(12);

        });

        it("run simple program5", () => {
            
         
            let  map : RecursiveMaze = new RecursiveMaze();

            let data : string = fs.readFileSync("input_testcase.txt").toString('utf-8');

            map.load(data.split("\r\n"));
                
                        
            let s : number = map.findPath("AA","ZZ");


            expect(s).to.equal(396);

        });


    
    })
});
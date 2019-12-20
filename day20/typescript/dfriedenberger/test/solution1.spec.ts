import { expect } from 'chai';
import { Maze } from '../src/Maze';

describe("Computer", () => {
    describe("run", () => {
        
      
    
       
      
        
        it("run simple program3", () => {
            
         
            let  map : Maze = new Maze();

            map.load([  "            A           ",
                        "            A           ",
                        "     #######.#########  ",
                        "     #######.........#  ",
                        "     #######.#######.#  ",
                        "     #######.#######.#  ",
                        "     #######.#######.#  ",
                        "     #####  B    ###.#  ",
                        "   BC...##  C    ###.#  ",
                        "     ##.##       ###.#  ",
                        "     ##...DE  F  ###.#  ",
                        "     #####    G  ###.#  ",
                        "     #########.#####.#  ",
                        "   DE..#######...###.#  ",
                        "     #.#########.###.#  ",
                        "   FG..#########.....#  ",
                        "     ###########.#####  ",
                        "                Z       ",
                        "                Z       "]);


                        console.log("Next = "+JSON.stringify(map.getNext({ x: 12, y: 1}))); //=> Erstes Feld
                        console.log("Next = "+JSON.stringify(map.getNext({ x: 12, y: 6}))); //=> BC neues Partner protal

                      
                        let s : number = map.findPath("AA","ZZ");


                	    expect(s).to.equal(23);

        });
  
    })
});
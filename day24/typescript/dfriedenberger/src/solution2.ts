import { RecursiveEris } from './RecursiveEris';



let eris : RecursiveEris = new RecursiveEris();


eris.load([
    "#.###",
    ".....",
    "#..#.",
    "##.##",
    "..#.#"]);

for(let i = 0;i < 200;i++)
    eris = eris.next();


console.log("My puzzle answer is " + eris.countAllBugs());
   




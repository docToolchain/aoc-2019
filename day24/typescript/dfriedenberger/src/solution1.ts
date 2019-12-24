import { Eris } from './Eris';



let eris : Eris = new Eris();

eris.load([
    "#.###",
    ".....",
    "#..#.",
    "##.##",
    "..#.#"]);

let cache : any = {};
while(true)
{
    let b : number = eris.biodiversity();
    if(b in cache)
    {
        eris.dump();
        console.log("My puzzle answer is " + b);
        break;
    }
    cache[b] = true;
    eris = eris.next();
}





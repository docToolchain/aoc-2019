import { Computer } from "./Computer";
import { Panel } from "./Panel";
import { SIGUSR1 } from "constants";

let panel = new Panel();


function scan(x : number,y : number) : number {

    let computer = Computer.load("input.txt");

    computer.setInput(x);
    computer.setInput(y);
    while(computer.run());
  
    return computer.getLastOutput();

}

function scanWidth(x : number,y : number) : any {

    let x0 : number = x;
    let x1 : number = x;
    while(scan(x0 -1,y) == 1) x0--;
    while(scan(x1 +1,y) == 1) x1++;
     
    //..xxx..
    //0123456  x0 = 2 , x1 = 4 
  
    return {
        x0 : x0,
        x1 : x1,
        w: x1 - x0 + 1,
    }

}

let y = 1700;
while(true)
{
    y+= 1;
    
    let x = Math.round(y/100 * 61);

    let o = scan(x,y);
    if(o != 1)
        {
            throw new Error("out of tractor");
        }
        let s = scanWidth(x,y);
        let x2 = Math.round((y+99)/100 * 61);

        let s2 = scanWidth(x2,y+99);
        let w = s.x1 - s2.x0 + 1;
        console.log(x+","+y+" "+x2+","+(y+99)+" w:"+w+" h:"+(y +99 -y +1)+" "+JSON.stringify(s)+"/"+JSON.stringify(s2));

        if(w >= 100)
        {
            console.log(s2.x0+","+y);
            console.log("My puzzle answer is "+(s2.x0 * 10000 + y));
            break;
        }
       
}









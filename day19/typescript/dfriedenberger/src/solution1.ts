import { Computer } from "./Computer";
import { Panel } from "./Panel";


let panel = new Panel();


let one = 0;
let zeros = 0;
for(let y = 0;y < 50;y++)
    for(let x = 0;x < 50;x++)
    {
        let computer = Computer.load("input.txt");

        computer.setInput(x);
        computer.setInput(y);
        while(computer.run());
      
        let output = computer.getLastOutput();
        if(output == 1)
            one++;
        panel.setXY(x,y,output)
    }

   
   panel.dump();

console.log("My puzzle answer is "+one);





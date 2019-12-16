import { Computer } from "./Computer";
import { Panel } from "./Panel";





let computer = Computer.load("input.txt");

computer.setMemory(0,2);

let panel = new Panel();

while(computer.run());

let output = computer.getOutput();
computer.clearOutput();
panel.load(output);

panel.dump();
console.log("blocks "+panel.count(2));

for(let x = 0;x < 10000;x++)
{

    let b = panel.getBallPosition();
    let j = panel.getPadlePosition();

    let d = 0;
    if(b.x < j.x)
        d = -1;
    if(b.x > j.x)
        d = 1;


    computer.setInput(d);

    while(computer.run());

    let output2 = computer.getOutput();
    computer.clearOutput();
    panel.load(output2);

    
    panel.dump()

    console.log("blocks "+panel.count(2));
    console.log("Score "+panel.score);

    if(panel.count(2) == 0)
        break;

}








console.log("My puzzle answer is "+panel.score);











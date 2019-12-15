import { Computer } from "./Computer";
import { Panel } from "./Panel";





let computer = Computer.load("input.txt");


let panel = new Panel();

while(computer.run());
let output = computer.getOutput();
panel.load(output);

panel.dump();
panel.createImage("images/test",1,"png");
console.log("My puzzle answer is "+panel.count(2));





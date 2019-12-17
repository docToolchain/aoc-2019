import { Computer } from "./Computer";
import { Panel } from "./Panel";





let computer = Computer.load("input.txt");


let panel = new Panel();

while(computer.run());


panel.load(computer.getOutput());

panel.dump();

console.log("My puzzle answer is "+panel.getIntersectionAlignmentSum());





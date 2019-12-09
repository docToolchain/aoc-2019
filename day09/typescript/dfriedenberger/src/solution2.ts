import { Computer } from "./Computer";

let computer = Computer.load("input.txt");

computer.setInput(2);

while(computer.run());

let output = computer.getOutput();

console.log("My puzzle answer is "+output);







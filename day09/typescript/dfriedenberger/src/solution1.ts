import { Computer } from "./Computer";

let computer = Computer.load("input.txt");

computer.setInput(1);

while(computer.run());

let output = computer.getLastOutput();

console.log("My puzzle answer is "+output);





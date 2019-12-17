import { Computer } from "./Computer";
import { Panel } from "./Panel";



/*
L,8,R,10,L,8,R,8,  => A
L,12,R,8,R,8, => B
L,8,R,10,L,8,R,8,  => A
L,8,R,6,R,6,R,10,L,8, => C
L,8,R,6,R,6,R,10,L,8, => C
L,8,R,10,L,8,R,8, => A
L,12,R,8,R,8, => B
L,8,R,6,R,6,R,10,L,8, => C
L,12,R,8,R,8, => B
L,12,R,8,R,8 => B
*/

// tag::run[]
let computer = Computer.load("input.txt");

computer.program.set(0,2);

let command = "A,B,A,C,C,A,B,C,B,B\n";
command += "L,8,R,10,L,8,R,8\n";
command += "L,12,R,8,R,8\n";
command += "L,8,R,6,R,6,R,10,L,8\n";
command += "n\n"; //y/n

for(let i = 0;i < command.length;i++)
    computer.setInput(command.charCodeAt(i));

while(computer.run());

// tag::dump[]
let panel = new Panel();
panel.load(computer.getOutput());
panel.dump();
// end::dump[]

console.log("My puzzle answer is "+computer.getLastOutput());
// end::run[]















import { Computer } from "./Computer";
import { ASCII } from "./ASCII";


let computer = Computer.load("input.txt");

let ascii = new ASCII(computer);

/*

  T, the temporary value register 
  J, the jump register

  one tile away (A)
  two tiles away (B) 
  three tiles away (C)
  four tiles away (D)

  @ABCD
  #xxx# (jump if D == 1 and A or B or C == 0)
  
  (!A || !B || !C) && D

*/

// !A || !B
ascii.sendCommand("NOT A J");
ascii.sendCommand("NOT B T");
ascii.sendCommand("OR T J");
// !C || J 
ascii.sendCommand("NOT C T");
ascii.sendCommand("OR T J");
// !!D && J
ascii.sendCommand("NOT D T");
ascii.sendCommand("NOT T T");
ascii.sendCommand("AND T J");

ascii.sendCommand("WALK");

ascii.step();
console.log("My puzzle answer is the big number");


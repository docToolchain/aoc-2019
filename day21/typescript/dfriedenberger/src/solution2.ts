import { Computer } from "./Computer";
import { ASCII } from "./ASCII";


let computer = Computer.load("input.txt");

let ascii = new ASCII(computer);



/*

  T, the temporary value register 
  J, the jump register

  one tile to nine tiles away (A) -> (I)
  0123456789
  @ABCDEFGHI  
  #xxx######
  #xxx#xxx##
  #xxx##xxx#
  #xxx###xxx

  (!A || !B || !C) && D && (H || (E && I) || (E && F)) => To long
  (!A || !B || !C) && D && (H || (E && (I || F))

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
//(I || F)
ascii.sendCommand("NOT I T");
ascii.sendCommand("NOT T T");
ascii.sendCommand("OR F T");
// && E
ascii.sendCommand("AND E T");
// || H
ascii.sendCommand("OR H T");
ascii.sendCommand("AND T J");
ascii.sendCommand("RUN");

ascii.step();
console.log("My puzzle answer is the big number");


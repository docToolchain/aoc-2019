import { Solution1 } from "./solution1";
import { strict as assert } from 'assert';

// test simple inputs first without repair
const s1 = new Solution1();
const testInput = "1,9,10,3,2,3,11,0,99,30,40,50"
let testResult = s1.solve(testInput,(v:Array<number>) => v)
assert(testResult[3] === 70)
assert(testResult[0] === 3500)

// execute test with the desired input and copy result
let repair = function(values:Array<number>){
    values[1] = 12
    values[2] = 2
}
const input = s1.load("input1.txt")
s1.solve(input,repair); 
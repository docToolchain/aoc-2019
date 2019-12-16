var fs = require('fs');
import { Calculator } from './Calculator';

let data : string = fs.readFileSync("input.txt").toString('utf-8');
var sequence = data.split("").map(i => parseInt(i));

let calculator : Calculator = new Calculator();

for(let i = 0;i < 100;i++)
    sequence = calculator.next(sequence);

console.log("My puzzle answer is " + sequence.join('').substr(0,8));

var fs = require('fs');
import { Pattern } from './Pattern';
import { Calculator } from './Calculator';



//6500000;
//5977269;


let data : string = fs.readFileSync("input.txt").toString('utf-8');
var presequence = data.split("").map(i => parseInt(i));

let l = presequence.length;

var sequence = new Array<number>(l * 10000);


for(let i = 0;i < l * 10000;i++)
    sequence[i] = presequence[i % l];

let calculator : Calculator = new Calculator();


for(let i = 0;i < 100;i++)
    sequence = calculator.nextOptimized(sequence,5977269);


console.log("My puzzle answer is " + sequence.slice(5977269,5977300).join('').substr(0,8));

var fs = require('fs');

import { Orbits } from "./Orbits";

let data = fs.readFileSync("input.txt").toString('utf-8');
let input = data.split("\r\n");


let orbits = new Orbits();

var arrayLength = input.length;
for (var i = 0; i < arrayLength; i++) { 
    var k = input[i];
    let p = k.split(')');
    orbits.add(p[0],p[1]);
}

console.log(orbits.map);


console.log("My puzzle answer is " + orbits.count("COM",0));

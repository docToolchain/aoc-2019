var fs = require('fs');

import { Orbits } from "./Orbits";

let data = fs.readFileSync("input.txt").toString('utf-8');
let input = data.split("\n");


let orbits = new Orbits();

var arrayLength = input.length;
for (var i = 0; i < arrayLength; i++) { 
    var k = input[i];
    let x = k.indexOf(')');
    let p1 = k.substr(0,x);
    let p2 = k.substr(x+1).replace(/(\r\n|\n|\r)/gm, "");
    console.log(x+" => " +p1 + " = " +p2);
    orbits.add(p1,p2);
}

//console.log(orbits.map);

orbits.find("COM",["YOU","SAN"]);

console.log("My puzzle answer is last output");

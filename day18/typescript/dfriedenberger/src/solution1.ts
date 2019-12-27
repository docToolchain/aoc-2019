var fs = require('fs');
import { TunnelMap } from './TunnelMap';



let  map : TunnelMap = new TunnelMap();

let data : string = fs.readFileSync("input.txt").toString('utf-8');

map.load(data.split("\r\n"));


let s : number = map.findShortestPath();



console.log("My puzzle answer is " + s);


var fs = require('fs');
import { Maze } from './Maze';



let  map : Maze = new Maze();

let data : string = fs.readFileSync("input.txt").toString('utf-8');

map.load(data.split("\r\n"));

let s : number = map.findPath("AA","ZZ");



console.log("My puzzle answer is " + s);


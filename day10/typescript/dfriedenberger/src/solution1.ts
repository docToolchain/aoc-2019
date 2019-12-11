var fs = require('fs');

import { AsteroidMap } from './AsteroidMap';


let data : string = fs.readFileSync("input.txt").toString('utf-8');


   let map = new AsteroidMap();

   for(let line of data.split("\r\n"))
   {
       console.log(line);
       map.addRow(line);
   }
   
   // tag::find[]
   var find : any = map.findmaxcountxy();
   console.log("My puzzle answer is " + find.max);
   // end::find[]

var fs = require('fs');

import { AsteroidMap } from './AsteroidMap';


let data : string = fs.readFileSync("input.txt").toString('utf-8');


   let map = new AsteroidMap();

   for(let line of data.split("\r\n"))
   {
       console.log(line);
       map.addRow(line);
   }
   // tag::vaporize[]
   var find : any = map.findmaxcountxy();
   let a = map.vaporizationOrder(find.x,find.y);
   console.log("My puzzle answer is " + a[199].k);
   // end::vaporize[]

   

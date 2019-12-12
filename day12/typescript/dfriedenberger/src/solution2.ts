import { Space } from './Space';
import { Moon } from './Moon';



  let space : Space = new Space();
           
  space.add(new Moon({ x: 3, y: 3, z: 0 }));
  space.add(new Moon({ x: 4, y: -16, z: 2 }));
  space.add(new Moon({ x: -10, y: -6, z: 5 }));
  space.add(new Moon({ x: -3, y: 0, z: -13 }));


// tag::cycle[]

  let ds0 : any = [];
  for(let x = 0;x < 3;x++)
  {
       ds0[x] = space.getDataSet(x);
  }
 
  let i = 0;
  let f = 0;
  let cycle : Array<number> = [];

  while(f < 3)
  {
    space.timeStep();
    i++;
    for(let x = 0;x < 3;x++)
    {
         let ds = space.getDataSet(x);

         //compare Arrays in JS simply with JSON.stringify
         if(JSON.stringify(ds) == JSON.stringify(ds0[x]))
         {
           f++;
           console.log("found cycle for "+x+" = "+i);
           cycle[x] = i;
           ds0[x] = []; //clear
         }
    }
  }
// end::cycle[]

// tag::ggt[]
/* calculate ggt */
function ggt(a : number,b : number) : number
{
  var r : number;
  do
      {
        r = a % b;
        a=b;
        b=r;
  }
  while (r>0);
  return a;
}
console.log("ggt("+cycle[0]+","+cycle[1]+") = "+ggt(cycle[0],cycle[1]));
console.log("ggt("+cycle[0]+","+cycle[2]+") = "+ggt(cycle[0],cycle[2]));
console.log("ggt("+cycle[2]+","+cycle[1]+") = "+ggt(cycle[2],cycle[1]));
// end::ggt[]

//(22958 / 2) * (231614 / 2) * (286332 / 2) * 2

console.log("My puzzle answer is (calculate with calctool.de)");

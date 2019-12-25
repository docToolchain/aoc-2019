import { Computer } from "./Computer";
import { ASCII } from "./ASCII";
import { Map } from "./Map";


let computer = Computer.load("input.txt");

let ascii = new ASCII(computer);
let map = new Map();

// tag::explore[]
for(let xx = 0;xx < 100;xx++)
{
  let explore : any[] = ascii.step();
  map.explore(explore);


  for(let item of map.getItems())
  {
    if(item == "infinite loop") continue;
    if(item == "giant electromagnet") continue;
    if(item == "escape pod") continue;
    if(item == "photons") continue;
    if(item == "molten lava") continue;
    ascii.sendCommand("take "+item);
    let explore : any[] = ascii.step();
    console.log("take "+item+" "+JSON.stringify(explore));
  }

  //Move
  let direction = map.nextUnexplored();
  console.log("go to "+direction);
  if(direction == undefined)
  {
    break;
  }
  ascii.sendCommand(direction);
}
// end::explore[]

// tag::gotocheckpoint[]
let path = map.getPathTo("Security Checkpoint");
for(let dir of path)
{
  ascii.sendCommand(dir);
  let explore : any[] = ascii.step();
  map.explore(explore);
}
// end::gotocheckpoint[]

map.dump();
ascii.sendCommand("inv");
ascii.step();

// tag::testitems[]
let items = ["pointer" ,"hypercube","cake","tambourine","monolith","mouse","coin","mug" ];

//drop all items
for(let i = 0;i < items.length;i++)
{
  ascii.sendCommand("drop "+items[i]);
  ascii.step();
}

let l = Math.pow(2,items.length);
for(let c = 0;c < l;c++)
{
  let testitems = [];
  for (var j=0;j<items.length;j++) {
    if ((c & Math.pow(2,j))){ 
      testitems.push(items[j]);
    }
  }

  console.log(c+" test with "+testitems);

  //take
  for(let i = 0;i < testitems.length;i++)
  {
    ascii.sendCommand("take "+testitems[i]);
    ascii.step();
  }
  ascii.sendCommand("north");
  let explore : any[] = ascii.step();
  map.explore(explore);

  if(map.room != "Security Checkpoint") break;

  //drop
  for(let i = 0;i < testitems.length;i++)
  {
    ascii.sendCommand("drop "+testitems[i]);
    ascii.step();
  }
}
// end::testitems[]

console.log("My puzzle answer you can see.");


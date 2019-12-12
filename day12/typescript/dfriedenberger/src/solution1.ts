import { Space } from './Space';
import { Moon } from './Moon';

// tag::timesteps[]
let space : Space = new Space();
           
space.add(new Moon({ x: 3, y: 3, z: 0 }));
space.add(new Moon({ x: 4, y: -16, z: 2 }));
space.add(new Moon({ x: -10, y: -6, z: 5 }));
space.add(new Moon({ x: -3, y: 0, z: -13 }));

for(let i = 0;i < 1000;i++)
    space.timeStep();

console.log("My puzzle answer is " + space.getEnergy());
// end::timesteps[]

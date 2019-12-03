import { CoordinateSet } from "./CoordinateSet";
import { Coordinate } from "./Coordinate";

class Wire {
    
   coordinates : CoordinateSet = new CoordinateSet();
   distances : any = {};

   // tag::constructor[]
   constructor(pathDefinition : string)
   {
       let pathes : Array<string> = pathDefinition.split(",");
       let x = 0;
       let y = 0;
       let l = 0;
       for (let path of pathes) {
            let direction = path.substr(0,1);
            let steps = parseInt(path.substr(1));
            let dy = 0;
            let dx = 0;
            switch(direction)
            {
                case "R": dx = 1; break;
                case "U": dy = 1; break;
                case "L": dx = -1; break;
                case "D": dy = -1; break;
            }

            for(let i = 0;i < steps;i++)
            {
                x = x + dx;
                y = y + dy;
                l = l + 1;
                let coordinate = new Coordinate(x,y)
                this.coordinates.add(new Coordinate(x,y));
                this.distances[coordinate.key()] = l;
            }
       }
   }
   // end::constructor[]

   contains(x : number, y : number) : boolean
   {
        return this.coordinates.has(new Coordinate(x,y));
   }

   // tag::cross[]
   cross(wire : Wire) : CoordinateSet 
   {
       let crosses = new CoordinateSet();

       for(let coordinate of this.coordinates)
       {
           if(wire.contains(coordinate.x,coordinate.y))
           {
                crosses.add(coordinate);
           }
       }
       return crosses;
   }
   // end::cross[]

}

export { Wire };
import { Coordinate } from "./Coordinate";
export class CoordinateSet implements Iterable<Coordinate> {
   
    map: any = {};

    add(coordinate: Coordinate) {
        let k = coordinate.key();
        this.map[k] = coordinate;
    }
    has(coordinate: Coordinate): boolean {
        let k = coordinate.key();
        return k in this.map;
    }
    size() : number
    {
        return Object.keys(this.map).length
    }

    [Symbol.iterator](): Iterator<Coordinate, any, undefined> {
        let ix = 0;
        let components : any = Object.values(this.map);
        return {
            next(): IteratorResult<Coordinate> {
              if (ix < components.length) {
                return {
                  done: false,
                  value: components[ix++]
                }
              } else {
                return {
                  done: true,
                  value: null
                }
              }
            }
          }
    }

}

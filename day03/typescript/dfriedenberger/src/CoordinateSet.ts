import { Coordinate } from "./Coordinate";
export class CoordinateSet  {
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

}

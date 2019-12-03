import { Coordinate } from "./Coordinate";
import { Distance } from "./Distance";
import { Wire } from "./Wire";

class SignalDelayDistance implements Distance {

    wires : any;

    constructor(pWires : any)
    {
        this.wires = pWires;
    }
    distance(coordinate: Coordinate): number {

        let d = 0;
        for(let map of this.wires)
        {
            let k = coordinate.key();
            if(k in map)
            {
                d += map[''+k];
            }
        }
        return d;
    }

}

export { SignalDelayDistance };
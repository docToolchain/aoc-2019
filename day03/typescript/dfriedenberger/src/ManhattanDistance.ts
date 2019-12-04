import { Coordinate } from "./Coordinate";
import { Distance } from "./Distance";

class ManhattanDistance implements Distance {

    // tag::distance[]
    distance(coordinate: Coordinate): number {
        return Math.abs(coordinate.x) + Math.abs(coordinate.y);
    }
    // end::distance[]

}

export { ManhattanDistance };
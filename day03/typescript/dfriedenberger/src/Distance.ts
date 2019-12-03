import { Coordinate } from "./Coordinate";

interface Distance {

    distance(coordinate : Coordinate) : number;
}

export { Distance };
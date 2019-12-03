import { CoordinateSet } from "./CoordinateSet";
import { Distance } from "./Distance";

class ShortestDistance {

    distance : Distance;

    constructor(pDistance : Distance)
    {
        this.distance = pDistance;
    }
    min(coordinates : CoordinateSet) : number
    {
        let minDistance = -1;
        for(let k in coordinates.map)
        {
            let coordinate = coordinates.map[k];

            let distance = this.distance.distance(coordinate);
            if(minDistance == -1 || distance < minDistance)
            {
                minDistance = distance;
            }

        }
        return minDistance;
    }

}
export { ShortestDistance };
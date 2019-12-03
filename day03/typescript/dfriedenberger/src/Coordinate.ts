export class Coordinate {
    x: number;
    y: number;
    constructor(px: number, py: number) {
        this.x = px;
        this.y = py;
    }
    key() : string{
        return JSON.stringify(this);
    }
}

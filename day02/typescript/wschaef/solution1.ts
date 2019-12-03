import fs from 'fs';

export class Line{
    constructor(
        public method: number,
        public p1: number,
        public p2: number,
        public p3: number){}

    public execute(values: Array<number>){
        const v1 = values[this.p1]
        const v2 = values[this.p2]
        switch (this.method) {
            case 1:
                values[this.p3] = v1 + v2
                break;
            case 2:
                values[this.p3] = v1 * v2
                break;
            case 99:
                values[this.p3] = v1 * v2
                break;
        }
    }
}

export class Solution1{
    public load(filename:string):string{
        return fs.readFileSync(filename,'utf8');
    }
    public solve(input:string, repair:Function){
        const stringValues = input.split(',');
        
        let values = stringValues
                .map(value => parseInt(value))
        repair(values)

        let lines = new Array<Line>()
        values.forEach((v,i) => i%4 == 0 ? lines.push(new Line(values[i],values[i+1],values[i+2],values[i+3])) : "")
        
        lines.forEach(line => line.execute(values));
        
        console.log("RESULT", values[0])
        return values
    }
}


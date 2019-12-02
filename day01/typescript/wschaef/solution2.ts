import fs from 'fs';
const input = fs.readFileSync('input1.txt','utf8');

const lines = input.split('\n');

let result = lines
        .map(line => parseInt(line))
        .map(v => calcFuel(v,0))
        .reduce((r,v) => r = r +v , 0)

console.log(result)


function calcFuel(value: number, total:number):number{
    if(value > 0){
        let fuel = Math.floor(value / 3) - 2 
        fuel = fuel >= 0 ? fuel : 0
        return calcFuel(fuel, total + fuel)
    }
    return total
}
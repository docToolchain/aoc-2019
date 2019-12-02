import fs from 'fs';
const input = fs.readFileSync('input1.txt','utf8');

const lines = input.split('\n');

let result = lines
        .map(line => parseInt(line))
        .map(v => Math.floor(v / 3) - 2 )
        .reduce((r,v) => r = r +v , 0)

console.log(result)

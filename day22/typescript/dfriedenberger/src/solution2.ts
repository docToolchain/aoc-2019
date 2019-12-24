var fs = require('fs');


function gcdExtended(a : number, b : number) {
  let x = 0, y = 1, u = 1, v = 0;
  while (a !== 0) {
    let q = Math.floor(b / a);
    [x, y, u, v] = [u, v, x - u * q, y - v * q];
    [a, b] = [b % a, a];
  }
  return [b, x, y];
}

function modInverse(a : number, m : number) {
  const [g, x] = gcdExtended(a, m);
  if (g !== 1) throw('Bad mod inverse')
  return (x + m) % m;
}

function modDiv(a : number, b: number, m : number) {
  return Number(BigInt(a) * BigInt(modInverse(b, m)) % BigInt(m));
}

function mulMod(a : number,b : number,m : number) {
  return Number(BigInt(a) * BigInt(b) % BigInt(m))
}


let data = fs.readFileSync("input.txt").toString('utf-8');
let commands = data.split('\r\n');


function solve(input : string[], N : number, x : number, rep = 1) : number {

  // tag::composition[]
  let a : number  = 1;
  let b : number  = 0;

  for(let i = input.length - 1;i >= 0;i--)
  {
    let p : string[] = input[i].split(" ");

    if(p[3] == "stack")
    {
      // pos = N - pos 
      a = (N - a) % N;
      b = (N + N - b - 1) % N;
    }
    if(p[0] == "cut")
    {
        let cut = parseInt(p[1]);
        b = ((b + cut) %N + N )% N;
    }
    if(p[2] == "increment")
    {
        let incr = parseInt(p[3]);
        a = modDiv(a, incr, N);
        b = modDiv(b, incr, N);
    }
    
  };
  // end::composition[]

  // tag::fast[]
  while (rep) {
    
    if (rep % 2) x = (mulMod(x,a,N) + b) % N;

    let a1 = mulMod(a,a,N);
    b = (mulMod(a,b,N) + b) % N;
    a = a1;
    rep = Math.floor(rep / 2);

  }
  return x;
  // end::fast[]

}
console.log("My puzzle answer is " + solve(commands, 119315717514047, 2020, 101741582076661));





class Permutation implements Iterable<Array<number>> {

    candidates : Array<Array<number>> = [];
    constructor(input : Array<number>)
    {
       //Must have length of 5
       if(input.length != 5) 
          throw new Error("not implemented for != 5");
          for(let a = 0;a < 5;a++)
          {
              for(let b = 0;b < 5;b++)
              {
                  if(b == a) continue;
                  for(let c = 0;c < 5;c++)
                  {
                      if(c == a) continue;
                      if(c == b) continue;
                      for(let d = 0;d < 5;d++)
                      {
                          if(d == a) continue;
                          if(d == b) continue;
                          if(d == c) continue;
                          for(let e = 0;e < 5;e++)
                          {
                              if(e == a) continue;
                              if(e == b) continue;
                              if(e == c) continue;
                              if(e == d) continue;     
          
                              this.candidates.push([input[a],input[b],input[c],input[d],input[e]]);
                              //console.log([a,b,c,d,e] + " > " + output);
                          }
                      }
                  }
              }
          }
          

    }

    [Symbol.iterator](): Iterator<Array<number>, any, undefined> {
        let ix = 0;
        let components : any = this.candidates;
        return {
            next(): IteratorResult<Array<number>> {
              if (ix < components.length) {
                return {
                  done: false,
                  value: components[ix++]
                }
              } else {
                return {
                  done: true,
                  value: null
                }
              }
            }
          }
    }
}

export { Permutation };
import { Pattern } from './Pattern';
class Calculator
{
    

    //tag::next[]
    next(sequence: number[]) {
      
        let l = sequence.length;
    
        let next: number[] = new Array<number>(sequence.length);
        for(let i = 0;i < l;i++)
        {
            let pattern: Pattern = new Pattern(i+1);
            let sum = 0;
            let x = 0;
            while(x < l)
            {
                var cmd = pattern.next();

                switch(cmd.factor)
                {
                    case 0:
                        x += cmd.length;
                        if(x >= l) x = l;
                        break;
                    case 1:
                        for(let j = 0;j < cmd.length && x < l;j++)
                            sum += sequence[x++];
                        break;
                    case -1:
                        for(let j = 0;j < cmd.length && x < l;j++)
                            sum -= sequence[x++];
                        break;
                }

            }
            let n = Math.abs(sum) % 10;
            next[i] = n;
        }

        return next;
    }
    //end::next[]

    //tag::nextOptimized[]
    nextOptimized(sequence: number[],offset : number) {
        let l = sequence.length;
    
        let next: number[] = new Array<number>(sequence.length);
        let sum = 0;
        for(let i = l - 1;i >= offset;i--)
        {
            sum += sequence[i];
            let n = Math.abs(sum) % 10;
            next[i] = n;
        }
        return next;
    }
    //end::nextOptimized[]


}
export { Calculator }
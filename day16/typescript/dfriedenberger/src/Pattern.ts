class Pattern
{
    repeat: number;
    ix : number = 0;
    numbers : number[] = [0,1,0,-1];
    constructor(repeat : number)
    {
        if(repeat == 0) 
            throw new Error("0 is not possible");
        this.repeat = repeat;
    }


    
    next() : any
    {

        let l = this.ix == 0?this.repeat - 1:this.repeat;
        let f = this.numbers[this.ix % 4];

        this.ix++;

        if(l == 0)
            return this.next();

        return {
            length : l,
            factor : f
        };
        
    }   

} 
export { Pattern }
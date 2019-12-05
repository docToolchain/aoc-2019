class Program {
    
    code: Array<number>;
    ix : number = 0;

    constructor(public pCode: Array<number>) {
        this.code = pCode;
    }

    getPosition() : number
    {
        return this.ix;
    }

    setPosition(i : number) : void
    {
       this.ix = i;
    }

    get(pix : number) : number
    {
        return this.code[pix];
    }
    set(pix : number, value : number) : void
    {
        this.code[pix] = value;
    }
    getOpCode() : number
    {
        return this.code[this.ix];
    }

    getInput1(mode : number) : number
    {
        let i = mode == 0? this.code[this.ix+1] : this.ix+1;
        return this.code[i];
    }

    getInput2(mode : number) : number
    {
        let i = mode == 0? this.code[this.ix+2] : this.ix+2;
        return this.code[i];
    }

    setInput(mode : number,result : number) : void
    {
        let i = mode == 0? this.code[this.ix+1] : this.ix+1;
        this.code[i] = result;
    }
    setResult(mode : number,result : number) : void
    {
        let i = mode == 0? this.code[this.ix+3] : this.ix+3;
        this.code[i] = result;
    }

    next(steps : number) : void
    {
        this.ix += steps;
    }
}

export { Program };

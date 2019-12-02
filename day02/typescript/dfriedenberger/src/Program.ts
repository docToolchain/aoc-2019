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

    getInput1() : number
    {
        let i = this.code[this.ix+1];
        return this.code[i];
    }

    getInput2() : number
    {
        let i = this.code[this.ix+2];
        return this.code[i];
    }

    setResult(result : number) : void
    {
        let i = this.code[this.ix+3];
        this.code[i] = result;
    }

    next() : void
    {
        this.ix += 4;
    }
}

export { Program };

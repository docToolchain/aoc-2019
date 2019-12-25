class Program {
    
    code: Array<number>;
    ix : number = 0;
    relativeBase :number = 0;
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

    // tag::getIndex[]
    getIndex(mode : number, parameter : number)
    {

        switch(mode)
        {
            case 0:  //position mode
                return this.code[this.ix+parameter];
            case 1:  //immediate mode
                return this.ix+parameter;
            case 2: //relative mode
                return this.code[this.ix+parameter] + this.relativeBase;
        }

        throw new Error("Mode "+mode+" is not supported yet");
    }
    // end::getIndex[]

    // tag::getInput1[]
    getInput1(mode : number) : number
    {
        let i = this.getIndex(mode,1);

        //check if index in code exists
        if ( this.code[i] !== void 0 )
            return this.code[i];
        return 0;
    }
    // end::getInput1[]

    getInput2(mode : number) : number
    {
        let i = this.getIndex(mode,2);

        //check if index in code exists
        if ( this.code[i] !== void 0 )
            return this.code[i];
        return 0;
    }

    setInput(mode : number,result : number) : void
    {
        let i = this.getIndex(mode,1);
        this.code[i] = result;
    }
    setResult(mode : number,result : number) : void
    {
        let i = this.getIndex(mode,3);
        this.code[i] = result;
    }

    incrRelativeBase(value : number) : void
    {
        this.relativeBase += value;
    }

    next(steps : number) : void
    {
        this.ix += steps;
    }
}

export { Program };

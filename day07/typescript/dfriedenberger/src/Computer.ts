import { Program } from "./Program";
import { ComputerState } from "./ComputerState";

class Computer {
    
    output : Array<number> = [];
    input : Array<number> = [];
    program : Program;
    ixInput : number = 0;
    state : ComputerState = ComputerState.Init;
    constructor(program : Program)
    {
        this.program = program;
    }

    setInput(val : number) : void
    {
        this.input.push(val);
    }
    getOutput() : Array<number>
    {
        return this.output;
    }

    getLastOutput() : number
    {
        return this.output[this.output.length - 1];
    }

    isDone()
    {
        return this.state == ComputerState.Done;
    }
    run() : boolean
    {

        let abcde = '00000' + this.program.getOpCode();
        let l = abcde.length;


        let de = parseInt(abcde.substring(l-2,l));
        let c = parseInt(abcde.substring(l-3,l-2));
        let b = parseInt(abcde.substring(l-4,l-3));
        let a = parseInt(abcde.substring(l-5,l-4));


        switch(de)
        {
            case 1:
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                this.program.setResult(a,input1 + input2);
                this.program.next(4);
                break;
            case 2:
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                this.program.setResult(a,input1 * input2);
                this.program.next(4);
                break;
            case 3:
                //Todo
                if(this.ixInput < this.input.length)
                {
                    let inputVal = this.input[this.ixInput++];
                    //console.log("Input "+inputVal);
                    this.program.setInput(c,inputVal);
                    this.program.next(2);
                    break;
                }
                this.state = ComputerState.WaitingForInput;
                return false;
            case 4:
                var input1 = this.program.getInput1(c);
                //console.log("Output "+input1);
                this.output.push(input1);
                this.program.next(2);
                break;
            case 5: //jump-if-true
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                if(input1 != 0)
                {
                    this.program.setPosition(input2);
                }
                else
                {
                    this.program.next(3);
                }
                break;
            case 6: //jump-if-false
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                if(input1 == 0)
                {
                    this.program.setPosition(input2);
                }
                else{
                    this.program.next(3);
                }
                break;    
            case 7: //less
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                if(input1 < input2)
                {
                    this.program.setResult(a,1);
                }
                else
                {
                    this.program.setResult(a,0);
                }
                this.program.next(4);
                break;        
            case 8: //equals
                var input1 = this.program.getInput1(c);
                var input2 = this.program.getInput2(b);
                if(input1 == input2)
                {
                    this.program.setResult(a,1);
                }
                else
                {
                    this.program.setResult(a,0);
                }
                this.program.next(4);
                break;        
            case 99:
                this.state = ComputerState.Done;
                return false;
            default:
                throw new Error();
        }
        return true;
    }

}

export { Computer };
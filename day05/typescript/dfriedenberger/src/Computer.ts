import { Program } from "./Program";

class Computer {
    
    output : Array<number> = [];
    input : number;
    setInput(val : number) : void
    {
        this.input = val;
    }
    getOutput() : Array<number>
    {
        return this.output;
    }
    run(program : Program) : boolean
    {

        let abcde = '00000' + program.getOpCode();
        let l = abcde.length;


        let de = parseInt(abcde.substring(l-2,l));
        let c = parseInt(abcde.substring(l-3,l-2));
        let b = parseInt(abcde.substring(l-4,l-3));
        let a = parseInt(abcde.substring(l-5,l-4));


        switch(de)
        {
            case 1:
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                program.setResult(a,input1 + input2);
                program.next(4);
                break;
            case 2:
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                program.setResult(a,input1 * input2);
                program.next(4);
                break;
            case 3:
                //Todo
                program.setInput(c,this.input);
                program.next(2);
                break;
            case 4:
                var input1 = program.getInput1(c);
                console.log("Output "+input1);
                this.output.push(input1);
                program.next(2);
                break;
            case 5: //jump-if-true
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                if(input1 != 0)
                {
                    program.setPosition(input2);
                }
                else
                {
                    program.next(3);
                }
                break;
            case 6: //jump-if-false
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                if(input1 == 0)
                {
                    program.setPosition(input2);
                }
                else{
                    program.next(3);
                }
                break;    
            case 7: //less
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                if(input1 < input2)
                {
                    program.setResult(a,1);
                }
                else
                {
                    program.setResult(a,0);
                }
                program.next(4);
                break;        
            case 8: //equals
                var input1 = program.getInput1(c);
                var input2 = program.getInput2(b);
                if(input1 == input2)
                {
                    program.setResult(a,1);
                }
                else
                {
                    program.setResult(a,0);
                }
                program.next(4);
                break;        
            case 99:
                return false;
            default:
                throw new Error();
        }
        return true;
    }

}

export { Computer };
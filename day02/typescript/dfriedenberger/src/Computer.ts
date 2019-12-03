import { Program } from "./Program";

class Computer {
    
   
    run(program : Program) : boolean
    {
        switch(program.getOpCode())
        {
            case 1:
                var input1 = program.getInput1();
                var input2 = program.getInput2();
                program.setResult(input1 + input2);
                break;
            case 2:
                var input1 = program.getInput1();
                var input2 = program.getInput2();
                program.setResult(input1 * input2);
                break;
            case 99:
                return false;
            default:
                throw new Error();
        }
        program.next();
        return true;
    }

}

export { Computer };
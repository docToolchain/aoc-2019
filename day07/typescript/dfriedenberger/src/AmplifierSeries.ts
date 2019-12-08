import { Computer } from "./Computer";
import { Program } from "./Program";



class AmplifierSeries {

    code: Array<number>;
    inputs : Array<number>;
    constructor(public pCode: Array<number>) {
        this.code = pCode;
    }

  
    // tag::run[]
    run(inputs: Array<number>) : number {

        let lastOutput : number= 0
        let computers : Array<Computer> = []; 
        for(let inst of inputs)
        {
            let program = new Program([...this.code]);
            let computer = new Computer(program);
            computer.setInput(inst);
            computers.push(computer);
        }

        while(!computers[4].isDone())
        {
            for(let i = 0;i < 5;i++)
            {
                computers[i].setInput(lastOutput);
                while(computers[i].run());

                lastOutput = computers[i].getLastOutput();
            }

        }
        return lastOutput;
        
    }
    // end::run[]

}

export { AmplifierSeries };
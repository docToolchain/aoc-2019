import { Computer } from "./Computer";

class ASCII
{
   
    computer: Computer;
   
    constructor(computer : Computer)
    {
        this.computer = computer;
    }

    sendCommand(command: string) {
        
        for(let i = 0;i < command.length;i++) {
            this.computer.setInput(command.charCodeAt(i));
        }

        this.computer.setInput(10); // \n
    }

    step() {

        while(this.computer.run());

        let output = this.computer.getOutput();

        //Print
        let line : string = "";
        for(let c of output)
        {
            if(c > 256)
            {
                console.log("Big number "+c);
            }
           if(c == 10)
           {
                console.log(line);
                line = "";
                continue;
           }
           line += String.fromCharCode(c);
        }

    }
   

}
export { ASCII }


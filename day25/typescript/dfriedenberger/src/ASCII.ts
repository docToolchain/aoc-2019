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
      
        let explore : any[] = [];
        let current : any = undefined;

        let t = 0;
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
                if(line == "Doors here lead:")
                    t = 1;
                else if(line == "Items here:")
                    t = 2;
                else if(line.startsWith("=="))
                {
                    let ix : number = line.indexOf("==",2);
                    //new Room
                    current = {
                        type : "room",
                        name : line.substring(3,ix-1),
                        directions: [],
                        items: []
                    };
                    t = 0;
                    explore.push(current);
                }
                else if(line.startsWith('-'))
                {
                    if(t == 1)
                        current.directions.push(line.substr(2));
                    if(t == 2)
                        current.items.push(line.substr(2));
                }

                //A loud, robotic voice says "Alert! Droids on this ship are heavier than the detected value!" and you are ejected back to the checkpoint.
                else if(line.indexOf("Droids on this ship are heavier than the detected value") > 0)
                {
                    current["alert"] = "robot to heavy";
                }
                else if(line.trim().length > 0)
                {
                    //console.log(line);
                }                
                if(line == "You don't see that item here." || line== "You don't have that item.")
                {
                    throw new Error(line);
                }
                if(line.indexOf("Alert") > 0)
                {
                    console.log(current.name+" "+line);
                }
                line = "";
                continue;
           }
           


           line += String.fromCharCode(c);
        }

        this.computer.clearOutput();
        return explore;
    }
   

}
export { ASCII }


import { Computer } from "./Computer";


//init computers and queues
let queues : any = {};
let computers = [];
for(let c = 0;c < 50;c++)
{
    computers[c] = Computer.load("input.txt");
    computers[c].setInput(c); //Set network adresee
    queues[c] = [[c, -1,-1]];
}
queues[255] = [];


while(true)
{
    for(let c = 0;c < 50;c++)
    {
        let computer = computers[c];

        //Send packets or -1
        if(queues[c].length > 0)
        {
            let p = queues[c].shift();
            computer.setInput(p[1]);
            computer.setInput(p[2]);
        }
        else
        {
            computer.setInput(-1);
        }

        while(computer.run());

        //Receive packets
        let output = computer.getOutput();
        for(let x = 0;x < output.length;x+=3)
        {
            let p = [ output[x] , output[x+1],output[x+2]];
            let a = p[0];
            queues[a].push(p);
        }
        computer.clearOutput();

    }
    
    if(queues[255].length > 0) 
    {
        console.log("My puzzle answer is "+queues[255][0][2]);
        break;
    }
}


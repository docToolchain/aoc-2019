import { Computer } from "./Computer";



let queues : any = {};
let computers = [];
for(let c = 0;c < 50;c++)
{
    computers[c] = Computer.load("input.txt");
    computers[c].setInput(c); //Set network adresee
    queues[c] = [[c, -1,-1]];
}
queues[255] = [];

let lastNaty = -1;
while(true)
{
    for(let c = 0;c < 50;c++)
    {
        let computer = computers[c];
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

        let output = computer.getOutput();
        for(let x = 0;x < output.length;x+=3)
        {
            let p = [ output[x] , output[x+1],output[x+2]];
            let a = p[0];
            queues[a].push(p);
        }
        computer.clearOutput();
    }

    let packets = 0;
    for(let k of Object.keys(queues))
    {
        if(k == '255') continue;
        packets += queues[k].length;
    }
    if(packets == 0)
    {
        let i = queues[255].length - 1;
        if(queues[255][i][2] == lastNaty)
        {
            console.log("My puzzle answer is "+lastNaty);
            break;
        }

        queues[0].push([ 0 , queues[255][i][1],queues[255][i][2]]);
        lastNaty = queues[255][i][2];
    }

}


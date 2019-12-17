import { Computer } from "./Computer";
import { Panel } from "./Panel";





let computer = Computer.load("input.txt");


let panel = new Panel();
panel.set(1);

while(true)
{

    //north (1), south (2), west (3), and east (4)

    let directionArray = panel.getDirectionForNearestUnknonwField();
    console.log(directionArray);
    if(directionArray.length == 0) break;

    let code = -1;
    for(let direction of directionArray)
    {
        computer.setInput(direction);
        
        while(computer.run());

        code = computer.getLastOutput();
    
        //console.log("in :"+direction+" out:" + code);
        switch(code)
        {
            case 0:
                //0: The repair droid hit a wall. Its position has not changed.
                panel.setWall(direction);
            break;
            case 1:
            case 2:
                //1: The repair droid has moved one step in the requested direction.
                //2: The repair droid has moved one step in the requested direction; its 
                //new position is the location of the oxygen system.
                panel.moveForward(direction);
            break;
            default:
            console.log("Unknown "+code);
            break;
        }

        
        panel.dump();

    }

}


panel.dump();

panel.x = 14;
panel.y = -14;
panel.getDirectionForNearestUnknonwField();


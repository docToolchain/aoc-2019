import { S_IFDIR } from "constants";
import { FORMERR } from "dns";

class Map
{
  
    getItems() : string[] {
        return this.rooms[this.room].items;
    }


    rooms: any = {}
    room: string = undefined;
    UNKNOWN: string = "unknown";
    lastdirection: string = undefined;
    mirror: any = {
        north : "south",
        south : "north",
        east : "west",
        west : "east"
    };

    explore(data : any[]): void {

        let name = undefined;

        for(let d of data)
        {
            name = d.name;

            let room : any = undefined;
            if(name in this.rooms)
            {
                room  = this.rooms[name];
            }
            else
            {
                room = { 
                directions : {}
                };
                this.rooms[name] = room;
            }
            
        

            for(let direction of d.directions)
            {
                if(direction in room.directions) continue;

                room.directions[direction] = this.UNKNOWN;
            }

            room.items = [];
            for(let item of d.items)
            {
                    room.items.push(item);
            }

            if("alert" in d)
            {
                room.alert = d.alert;
            }

            //last
            if(this.room != undefined && this.room != name && this.lastdirection != undefined)
            {
                let mirrorDirection = this.mirror[this.lastdirection];
                //console.log("came from "+this.room+" walked "+this.lastdirection+" mirror "+mirrorDirection);
                let r1 = this.rooms[this.room];
                let r2 = this.rooms[name];
                r1.directions[this.lastdirection] = name;
                r2.directions[mirrorDirection] = this.room;
            }
            this.room = name;
            this.lastdirection = undefined;

        }

       
        
    }

    nextUnexplored(): string {

        let queue : string[] = [];
        let visited : any = {};
        let path : any = {};
        queue.push(this.room);
        visited[this.room] = true;
        path[this.room] = [];
        while(queue.length > 0)
        {
            let name = queue.shift();
            let room = this.rooms[name];
            for(let d of ["north" , "south", "west", "east"])
            {
                if(d in room.directions)
                {
                    let next = room.directions[d];
                    if(next in visited) continue;
                    visited[next] = true;
                    path[next] = [...path[name]];
                    path[next].push(d);
                    if(next == this.UNKNOWN)
                    {
                        let n = path[next][0];
                        this.lastdirection = n;
                        return n;
                    }
                    queue.push(next);
                }
            }
        }
        return undefined;
    }

    getPathTo(toName: string) {
        let queue : string[] = [];
        let visited : any = {};
        let path : any = {};
        queue.push(this.room);
        visited[this.room] = true;
        path[this.room] = [];
        while(queue.length > 0)
        {
            let name = queue.shift();
            let room = this.rooms[name];
            if(name == toName)
            {
                return path[name];
            }
            for(let d of ["north" , "south", "west", "east"])
            {
                if(d in room.directions)
                {
                    let next = room.directions[d];
                    if(next in visited) continue;
                    visited[next] = true;
                    path[next] = [...path[name]];
                    path[next].push(d);
                    queue.push(next);
                }
            }
        }
        return undefined;    }
    
    dump() {
       
        for(let name of Object.keys(this.rooms))
        {
            console.log(name + " => "+JSON.stringify(this.rooms[name]));
        }
        console.log("current position "+this.room);
    }
    

}
export { Map };
class Maze
{
    width : number = 0;
    height : number = 0;

    fields : any = {};
    portals: any = {};

    set(x: number, y: number, c: string) {
        let k = x+","+y;
        this.fields[k] = c;
    }

    load(lines : Array<String>) : void
    {
        this.height = lines.length;
        this.width = lines[0].length;

        for(let y = 0;y < this.height;y++)
        {
            for(let x = 0;x < this.width;x++)
            {
                let c = lines[y].charAt(x);
                if(c.match(/[A-Z]/))
                {
                    this.portals[x+","+y] = { letter : c , coords:  {x : x , y : y } };
                }

                if(c == " ") continue; //ignore
                this.set(x,y,c)
            }
        }


        //find neigbours
        for(let k1 of Object.keys(this.portals))
        {
            let p1 = this.portals[k1];

            if(p1.portal) continue;

            let k2 = undefined;
            for(let d of [{ x : 0, y: 1} , { x : 0, y: -1},{ x : 1, y: 0},{ x : -1, y: 0}])
            {
                k2 = (p1.coords.x + d.x)+","+(p1.coords.y + d.y);
                if(k2 in this.portals) break;
                k2 = undefined;
            }
            let p2 = this.portals[k2];
            
            let p :any = {
                portal : [ p1.letter , p2.letter].sort().join(''),
                coords : [p1.coords , p2.coords]
            };
            console.log("Portal "+JSON.stringify(p));
            this.portals[k1] = p;
            this.portals[k2] = p;

        }

    }

    // tag::findPath[]
    findPath(fromNode : string,toNode : string) : number {
       
        let queue : Array<any> = [];

        let visited : any = {};
        let distance : any = {};


        let p : any =  Object.values(this.portals).filter((p : any) => p.portal == fromNode)[0];
       
        for(let coords of p.coords)
        {
            queue.push(coords);
            let k =coords.x+","+coords.y;
            visited[k] = true;
            distance[k] = 0;
        }
        while(queue.length > 0)
        {
            let u : { x: number; y : number } = queue.shift();
            let uk = u.x+","+u.y;

            let next : Array<{ x: number, y:number}> = this.getNext(u);


            //console.log(uk +" "+JSON.stringify(next));

            for(let v of next)
            {
                let vk = v.x +","+ v.y;
                if(!(vk in this.fields)) continue; //out of field
                if(this.fields[vk] == '#') continue; //Wall
                if(vk in visited) continue;
                
                //setze distance(v) = distance(u)+1
                //setze predecessor(v) = u
                distance[vk] = distance[uk] + 1;
                visited[vk] = true;
               
                if(vk in this.portals)
                {
                    let p1 = this.portals[vk];
                    //Portal 
                    console.log("Portal "+JSON.stringify(p1));

                    if(p1.portal == toNode)
                        return distance[vk] - 2;
                }
               
                queue.push(v);

            }

        }
        return -1;
    }
    // end::findPath[]

    // tag::getNext[]
    getNext(u: { x: number; y: number; }): { x: number; y: number; }[] {

      
        let next : { x: number; y: number; }[]  = [];
        for(let d of [{ x : 0, y: 1} , { x : 0, y: -1},{ x : 1, y: 0},{ x : -1, y: 0}])
        {
            let v = { x: u.x + d.x , y: u.y + d.y};
            let vk = v.x +","+ v.y;
            let vp = this.portals[vk];
            if(vp)
            {
                //Partner - Portal 
                let p2 : any = Object.values(this.portals)
                    .filter((p : any) => p.portal == vp.portal  
                        && ((p.coords[0].x != vp.coords[0].x) || (p.coords[0].y != vp.coords[0].y)))[0];
                
                        if(p2)
                {
                    //hat ein Partner Portal
                    console.log(JSON.stringify(vp) +" => "+JSON.stringify(p2));

                    for(let coords of p2.coords)
                    {
                        let c = { x: coords.x , y: coords.y };
                        for(let d2 of [{ x : 0, y: 1} , { x : 0, y: -1},{ x : 1, y: 0},{ x : -1, y: 0}])
                        {
                            let v2 = { x: c.x + d2.x , y: c.y + d2.y};
                            let v2k = v2.x +","+ v2.y;
                            let v2p = this.portals[v2k];
                            if(v2p) continue;
                            next.push(v2);
                        }
                    }
                    //ignore ist schon erreicht
                    continue;
                }
               
            }
            

            next.push(v);
        }
        return next;
    }
    // end::getNext[]

    
  
   
  
} 
export { Maze }
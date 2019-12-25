function onlyUnique(value : any, index : number, self : any) { 
    return self.indexOf(value) === index;
}

class TunnelMap
{
    width : number = 0;
    height : number = 0;

    robot : number = 0;

    fields : any = {};
    keys: any = {};
    graph: any = {};

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
               
                if(c == "@")
                {
                    this.keys[""+this.robot++] = { x: x, y:y };
                    c = ".";
                }
                if(c.match(/[a-z]/))
                {
                    this.keys[c] = { x: x, y:y };
                }
                this.set(x,y,c)
            }
        }

        for(let k of Object.keys(this.keys))
        {
            this.graph[k] = this.findNeigbours(k);
            console.log(k +" = "+JSON.stringify(this.graph[k]));
        }
        
    }
   
    findNeigbours(n : string) : Array<any> {
       
        let queue : Array<{ x: number; y : number }> = [];

        let visited : any = {};
        let distance : any = {};
        let doors : any = {};
        let ways : any = {};

        let nodes : Array<any> = [];

        queue.push(this.keys[n]);
        let k =this.keys[n].x+","+this.keys[n].y;
        visited[k] = true;
        distance[k] = 0;
        doors[k] = [];
        ways[k] = [];

        while(queue.length > 0)
        {
            let u : { x: number; y : number } = queue.shift();

            for(let d of [{ x : 0, y: 1} , { x : 0, y: -1},{ x : 1, y: 0},{ x : -1, y: 0}])
            {
                let v = { x: u.x + d.x , y: u.y + d.y};
                let k = v.x +","+ v.y;

                if(!(k in this.fields)) continue; //out of filed
                if(this.fields[k] == '#') continue; //Wall
                if(k in visited) continue;
                
                //setze distance(v) = distance(u)+1
                //setze predecessor(v) = u
                distance[k] = distance[u.x+","+u.y] + 1;
                doors[k] = [...doors[u.x+","+u.y]];
                ways[k] = [...ways[u.x+","+u.y]];

                visited[k] = true;

                if(this.fields[k].match(/[A-Z]/))
                {
                    //Doors 
                    //console.log("Doors "+this.fields[k]);
                    doors[k].push(this.fields[k]);
                }

                if(this.fields[k].match(/[a-z]/))
                {
                    //Key 
                    //console.log("Key "+this.fields[k]+" dist:"+distance[k]+" doors:"+doors[k]);
                    nodes.push({
                        key : this.fields[k],
                        distance: distance[k],
                        doors: doors[k],
                        ways: [...ways[k]]
                    });
                    ways[k].push(this.fields[k]);
                }
                queue.push(v);
            }
        }
        return nodes;
    }

    findShortestPath(): number {
        let steps = 0;
        for(let r = 0;r < this.robot;r++)
        {
            let k : string = ""+r;
            // {"key":"d","distance":6,"doors":["C","B","A"],"ways":[]}
            let keys = this.graph[k].map((i : any) => i.key);
            
            let visited = this.graph[k]
                              .map((i : any) => i.doors)
                              .reduce((a:any , b:any) => a.concat(b))
                              .filter( (value : any, index : number, self : any) =>  { return self.indexOf(value) === index;})
                              .map((i : string) => i.toLowerCase())
                              .filter((i : string) => !keys.includes(i));
           
            console.log(k + " Use visited "+visited);


            steps += this.findShortest(k,visited);
        }
        return steps;
    }

    wayIsVisited(way: string[], keys: string[]) : boolean {

        for(let waypoint of way)
        {
            if(keys.includes(waypoint)) continue;
            return false;
        }
        return true;
    }

    canUnlock(doors: string[], keys: string[]) : boolean {

        for(let door of doors)
        {
            if(keys.includes(door.toLowerCase())) continue;
            return false;
        }
        return true;
    }
    cache : any = {};
    findShortest(n : string,visited: string []): number {
        
        
        console.log("findShortest "+visited);

        // tag::cache[]
        let cacheK = n + "-" +visited.sort().join(",");
        if(cacheK in this.cache)
        {
            return this.cache[cacheK];
        }
        // end::cache[]


        let min = -1;
        
        let reachable: Array<any> = [];
        for(let c of this.graph[n])
        {
            //{"key":"g","distance":58,"doors":["U"],"ways":["a"]},
            if(visited.includes(c.key)) continue;
            if(!this.canUnlock(c.doors,visited)) continue;
            if(!this.wayIsVisited(c.ways,visited)) continue;
            reachable.push(c);
        }

        reachable = reachable.sort((a,b) => a.distance - b.distance);
        //console.log(visited +" => reachable keys "+JSON.stringify(reachable));  
       

        if(reachable.length == 0)
        {
            min = 0;
        }
        else {
            for(let c of reachable)
            {
                let l = c.distance + this.findShortest(c.key,visited.concat([c.key]));

                //console.log(" can visit "+JSON.stringify(c)+" visited:"+visited+" l="+l);
                if(min == -1 || l < min)
                    min = l;
            
            }
        }

        //cache
        this.cache[cacheK] = min;
        return min;
    }
   
   

    
  
   
  
} 
export { TunnelMap }
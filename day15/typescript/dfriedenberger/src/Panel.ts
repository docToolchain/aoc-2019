class Panel
{
  
    x : number = 0;
    y : number = 0;
    fields : any = {};

    minx : number = 0;
    maxx : number = 0;
    miny : number = 0;
    maxy : number = 0;

    set(t : number)
    {
        let k = this.x +"," + this.y;
        this.fields[k] = t;
    }
  
    getXY(x : number,y:number)
    {
        let k = x +"," + y;
        if(!(k in this.fields)) return -1;
        return this.fields[k];
    }
    getXYSymbol(x : number,y:number)
    {
        let k = x +"," + y;

        if(x == this.x && y == this.y) return "D";
        if(!(k in this.fields)) return ' ';
        switch(this.fields[k])
        {
            case 0:
                return '#';
            case 1:
                return '.';
        }
        return "-";
    }

    moveForward(direction : number) :void
    {
        //north (1), south (2), west (3), and east (4)
        switch(direction)
        {
            case 1:
                this.y--;
                break;
            case 2:
                this.y++;
                break;   
            case 3:
                this.x--;
                break;
            case 4:
                this.x++;
                break;     
        }

        this.set(1);

        this.minx = Math.min(this.minx,this.x);
        this.maxx = Math.max(this.maxx,this.x);
        this.miny = Math.min(this.miny,this.y);
        this.maxy = Math.max(this.maxy,this.y);  
        this.set(1);
    }

    setWall(direction : number) :void
    {
        let sx = this.x;
        let sy = this.y;

        this.moveForward(direction);
        this.set(0);

        this.x = sx;
        this.y = sy;
    }
  
    directions : Array<any>= [{ d: 1, x:0 , y:-1} , { d: 2, x:0 , y:1} , { d: 3, x:-1 , y:0} , { d: 4, x:1 , y:0}];

    //tag::getCandidates[]
    getCandidates(x : number, y : number,way : Array<number>,visited : any) : Array<any>
    {
        
        let candidates : Array<any> = [];
        for(let i = 0;i < 4;i++)
        {
            let direction = this.directions[i];
            let xn = x + direction.x;
            let yn = y + direction.y;
            let k = xn+","+yn;

            if(k in visited) continue;
            visited[k] = true;
            let t = this.getXY(xn,yn);
            candidates.push({
                x: xn,
                y: yn,
                w: way.concat([direction.d]),
                d: xn * xn + yn * yn,
                t : t
            });

        }
        //console.log("get "+x+","+y+" => "+JSON.stringify(candidates));

        return candidates;
    }
    //end::getCandidates[]

   
    getDirectionForNearestUnknonwField() : Array<number> {

        let visited : any = {};
        let w : Array<number> = [];
        let coordinates : Array<any> = [{ x: this.x , y : this.y , w: w }];

        visited[this.x+","+this.y] = true;
        let l = 0;
        while(true)
        {
                //get candidate
                l++;
                let candidates : Array<any> = [];
                for(let c of coordinates)
                {
                    let nc = this.getCandidates(c.x,c.y,c.w,visited);
                    candidates = candidates.concat(nc);
                }


                //select best candidates
                let newfields = candidates.filter(item => item.t == -1).sort((a, b) => a.l - b.l);
                coordinates = candidates
                    .filter(item => item.t == 1)
                    .map((o : any ) =>  {return { x : o.x, y : o.y , w : o.w }});

                console.log("found "+candidates.length+" candidates for length "+l+" unvisited: "+newfields.length+" next: "+coordinates.length);
                if(newfields.length > 0)
                {
                    return newfields[0].w;
                }

                if(coordinates.length == 0)
                {
                    return [];
                }
                
        }      
    }
    //tag::getDistance[]
    getDistance(x :number, y:number) : number {

        let visited : any = {};
        let w : Array<number> = [];
        let coordinates : Array<any> = [{ x: 0 , y : 0 , w: w }];

        visited[0+","+0] = true;
        let l = 0;
        while(true)
        {
                //get candidate
                l++;
                let candidates : Array<any> = [];
                for(let c of coordinates)
                {
                    let nc = this.getCandidates(c.x,c.y,c.w,visited);
                    candidates = candidates.concat(nc);
                }

                //console.log(JSON.stringify(candidates));

                //select best candidates
                let newfields = candidates.filter(item => ((item.x == x) && (item.y == y)));
                coordinates = candidates
                    .filter(item => item.t == 1)
                    .map((o : any ) =>  {return { x : o.x, y : o.y , w : o.w }});

                console.log("found "+candidates.length+" candidates for length "+l+" unvisited: "+newfields.length+" next: "+coordinates.length);
                if(newfields.length > 0)
                {
                    console.log(JSON.stringify(newfields));
                    return newfields[0].w.length;
                }

        }      
    }
    //end::getDistance[]

    dump() : void
    {
        for(let y = this.miny;y <= this.maxy;y++)
        {
            let line = "";
            for(let x = this.minx;x <= this.maxx;x++)
            {
                line += this.getXYSymbol(x,y);
            }
            console.log(line);
        }
    }

}
export { Panel }


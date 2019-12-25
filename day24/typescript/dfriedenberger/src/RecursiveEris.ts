class RecursiveEris {
    
   
    height: number;
    width: number;
    grid: any  = {};
    maxlevel: number = 0;
    minlevel: number = 0;
    borders: any = {};

    constructor()
    {
        this.borders["1,2"] = Array.apply(null, {length: 5}).map(Number.call, Number).map((i : number) => { return { x : 0 , y : i };});
        this.borders["3,2"] = Array.apply(null, {length: 5}).map(Number.call, Number).map((i : number) => { return { x : 4 , y : i };});
        this.borders["2,1"] = Array.apply(null, {length: 5}).map(Number.call, Number).map((i : number) => { return { x : i , y : 0 };});
        this.borders["2,3"] = Array.apply(null, {length: 5}).map(Number.call, Number).map((i : number) => { return { x : i , y : 4 };});
    }

    load(rows : string[]) : void
    {
        this.height = rows.length;
        this.width = rows[0].length;
        for(let y = 0;y < this.height;y++)
        {
            let cells : string[] = rows[y].split('');
            for(let x = 0;x < this.width;x++)
            {
                this.set(x,y,0,cells[x]);
            }
        }

    }

    set(x: number, y: number, level : number,t: string) {
        this.grid[level+":"+x+","+y] = t;
    }

   
    next(): RecursiveEris {

        let next : RecursiveEris = new RecursiveEris();
        next.width = this.width;
        next.height = this.height;


        next.minlevel = this.minlevel;
        next.maxlevel = this.maxlevel;

        if(this.countBugsForLevel(next.minlevel) > 0)
            next.minlevel--;
        if(this.countBugsForLevel(next.maxlevel) > 0)
            next.maxlevel++;
        for(let l = next.minlevel;l <= next.maxlevel;l++)
        {
            for(let y = 0;y < this.height;y++)
            {
                for(let x = 0;x < this.width;x++)
                {
                    if(this.isCenter(x,y,l)) 
                    {
                        next.set(x,y,l,"?");
                        continue;
                    }
                    let bugs : number = this.countBugs(x,y,l);
                    if(this.isBug(x,y,l))
                    {
                        if(bugs != 1)  //bug dies
                            next.set(x,y,l,".");
                        else
                            next.set(x,y,l,"#");
                    }
                    else
                    {
                        if(bugs == 1 || bugs == 2)  //becomes infested
                            next.set(x,y,l,"#");
                        else
                            next.set(x,y,l,".");
                    }

                }

            }
        }
        return next;
    }

   

    isCenter(x: number, y: number, l: number) {
       return (x == 2) && (y == 2);
    }

    isOutside(x: number, y: number, l: number) {
        if(x < 0)
            return { x : 1 , y : 2};
        if(x > 4)
            return { x : 3 , y : 2};
        if(y < 0)
            return { x : 2 , y : 1};
        if(y > 4)
            return { x : 2 , y : 3};

        return undefined;
    }

    isBug(x: number, y: number, level : number) : boolean {

        let k : string = level +":"+x+","+y;
        return ((k in this.grid) && (this.grid[k] == '#'));

    }

    countBugs(x: number, y: number, level : number): number {

        let bugs = 0;
        for(let d of [{ x: 1, y : 0 },{ x: -1, y : 0 },{ x: 0, y : 1 },{ x: 0, y : -1 }])
        {
            let outside : any = this.isOutside(x + d.x,y + d.y,level)
            if(outside)
            {
                //look at level - 1
                //look for outer tile    
                //console.log("level = "+level+" " + x+","+y+"  => "+outside.x+","+outside.y+" => bugs "+this.isBug(outside.x,outside.y,level-1))
           
                if(this.isBug(outside.x,outside.y,level-1))
                    bugs++;
            }
            if(this.isCenter(x + d.x,y + d.y,level))
            {
                //look at level + 1
                //look for the 5 inner tiles
                                  
                for(let pos of this.borders[x+","+y])
                {
                    //console.log("level = "+level+" " + x+","+y+"  => "+pos.x+","+pos.y+" => bugs "+this.isBug(pos.x,pos.y,level+1))
                    if(this.isBug(pos.x,pos.y,level+1))
                    {
                        bugs++;         
                    }
                }   
            }
            if(this.isBug(x + d.x,y + d.y,level))
                bugs++;
        }
        return bugs;
    }
   
   
   
    biodiversity(level: number): number {

        let b = 0;
        let i = 0;
        for(let y = 0;y < this.height;y++)
        {
            for(let x = 0;x < this.width;x++)
            {
                if(this.isBug(x,y,level))
                {
                    b += Math.pow(2,i);
                }
                i++;
            }
        }
        return b;

    }
    
    countAllBugs() : number {
        let b = 0;
        for(let l = this.minlevel;l <= this.maxlevel;l++)
            b += this.countBugsForLevel(l);
        return b;
    }

    countBugsForLevel(level: number) : number {
        let b = 0;
        for(let y = 0;y < this.height;y++)
        {
            for(let x = 0;x < this.width;x++)
            {
                if(this.isBug(x,y,level))
                {
                    b ++;
                }
            }
        }
        return b;    
    }
    dump(level: number) : void
    {
        console.log("------ "+level+"--------");
        for(let y = 0;y < this.height;y++)
        {
            let line = "";            
            for(let x = 0;x < this.width;x++)
            {
                line += this.grid[level+":"+x+","+y];
            }
            console.log(line);

        }
    }

    dumpAll() {
        console.log("==========================");
        for(let l = this.minlevel;l <= this.maxlevel;l++)
        {
            this.dump(l);
        }
    }
}
export {RecursiveEris }
class Eris {
   
    height: number;
    width: number;
    grid: any  = {};


    load(rows : string[]) : void
    {
        this.height = rows.length;
        this.width = rows[0].length;
        for(let y = 0;y < this.height;y++)
        {
            let cells : string[] = rows[y].split('');
            
            for(let x = 0;x < this.width;x++)
            {
                this.set(x,y,cells[x]);
            }
        }

    }

    set(x: number, y: number, t: string) {
        this.grid[x+","+y] = t;
    }

    next(): Eris {

        let next : Eris = new Eris();
        next.width = this.width;
        next.height = this.height;

        for(let y = 0;y < this.height;y++)
        {
            for(let x = 0;x < this.width;x++)
            {
                let bugs : number = this.countBugs(x,y);
                if(this.isBug(x,y))
                {
                    if(bugs != 1)  //bug dies
                        next.set(x,y,".");
                    else
                        next.set(x,y,"#");
                }
                else
                {
                    if(bugs == 1 || bugs == 2)  //becomes infested
                        next.set(x,y,"#");
                    else
                        next.set(x,y,".");
                }

            }

        }
        return next;
    }

    isBug(x: number, y: number) : boolean {

        let k : string = x+","+y;
        return ((k in this.grid) && (this.grid[k] == '#'));
    }

    countBugs(x: number, y: number): number {

        let bugs = 0;
        for(let d of [{ x: 1, y : 0 },{ x: -1, y : 0 },{ x: 0, y : 1 },{ x: 0, y : -1 }])
        {
            if(this.isBug(x + d.x,y + d.y))
                bugs++;
        }
        return bugs;
    }
   
    biodiversity(): number {

        let b = 0;
        let i = 0;
        for(let y = 0;y < this.height;y++)
        {
            for(let x = 0;x < this.width;x++)
            {
                if(this.isBug(x,y))
                {
                    b += Math.pow(2,i);
                }
                i++;
            }
        }
        return b;

    }
    

    dump() : void
    {
        for(let y = 0;y < this.height;y++)
        {
            let line = "";            
            for(let x = 0;x < this.width;x++)
            {
                line += this.grid[x+","+y];
            }
            console.log(line);

        }
    }
}
export {Eris }
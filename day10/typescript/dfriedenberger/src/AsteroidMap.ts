class AsteroidMap 
{
    width: number = 0;
    height: number = 0;
    map : any = {};
    candidates : any = [];

    add(x : number,y : number) : void
    {
        this.map[x+","+y] = 0;
    }

    is(x : number,y : number) : boolean
    {
        return ((x+","+y) in this.map);
    }

    addRow(line : string)
    {
        let c = line.split('');
        if(this.height == 0)
            this.width = c.length;
        else if(this.width != c.length)
            throw new Error(this.width +" != "+ c.length);

        for(let x = 0;x < this.width;x++)
        {
            switch(c[x])
            {
                case '#': //asteroid
                  this.add(x,this.height);
                  break;
                case '.': //space
                  //ignore
                  break;
                default:
                    throw new Error("Unknown "+ c[x]);
            }
        }
        this.height++;    
    }

    count() : number {
        return Object.keys(this.map).length;
    }

    diagonale(x0 : number,y0 : number,xoff : number,yoff : number) : number
    {
        let c = 0;
        let x = x0;
        let y = y0;
        while(true)
        {
            x += xoff;
            y += yoff;
            if(x < 0) break;
            if(x >= this.width) break;
            if(y < 0) break;
            if(y >= this.height) break;
            if(this.is(x,y))
                c+= 1;
        }
        return c;
    }

    createCandidates() : void {

        if(this.candidates.length > 0) return;


        let deep = this.width > this.height ? this.width : this.height;
        //console.log("deep = "+deep);
        for(let j = deep * -1; j <= deep;j++)
            for(let i = deep * -1; i <= deep;i++)
            {
                if(i == 0 && j == 0) continue;
                
                //is vielfaches
                let ignore = false;
                for(let f = 2;f <= deep;f++)
                {
                    if(i % f == 0 && j % f == 0) ignore = true;
                }
                if(ignore) continue;
                this.candidates.push([j,i]);
            }


    }

    countxy(x : number,y : number) : number
    {
        this.createCandidates();

        let s = this.count() -1; //myself



        for(let i = 0;i < this.candidates.length;i++)
        {
            let candidate = this.candidates[i];
            let c = this.diagonale(x,y,candidate[0],candidate[1]);
            if(c > 1)
                s -= c - 1;
        }

        return s;
    }

    findmaxcountxy() : object {
        let found = {
            max : 0,
            x : -1,
            y : -2
        };
        
      
        for(let x = 0;x < this.width;x++)
            for(let y = 0;y < this.height;y++)
            {
                if(!this.is(x,y)) continue;

                let c = this.countxy(x,y);
                if(c > found.max)
                {
                    found.max = c;
                    found.x = x;
                    found.y = y;
                }
            }

        return found;

    }

    angle(v1 : any , v2 : any)
    {
        //cos y = v1 * v2 / |v1| * |v2|
        let a = Math.sqrt(v1[0] * v1[0] + v1[1] * v1[1]);
        let b = Math.sqrt(v2[0] * v2[0] + v2[1] * v2[1]);
        let c = (v1[0] * v2[0] + v1[1] * v2[1]);
        
        let d= c / (a * b);

        let w = Math.acos(d);

        if(v2[0] < 0)
            return 6.3 - w;
        return w;

    }
    distance(x : any , y : any) : number
    {
        return Math.sqrt(x * x + y * y);
    }

    factor(x : any , y : any) :number
    {
        let factor = 1;
        for(let f = 2;f <= Math.max(x,y);f++)
        {
            if(x % f == 0 && y % f == 0) 
                factor = f;
        }
        return factor;
    }
    vaporizationOrder(xl : number,yl :number) : any
    {
        let list : any = [];
        for(let x = 0;x < this.width;x++)
            for(let y = 0;y < this.height;y++)
            {
                if(x == xl && y == yl) continue;
                if(!this.is(x,y)) continue;

                var xr = x -xl;
                var yr = y -yl;

                let f = this.factor(xr,yr);

                list.push({
                    x : x,
                    y : y,
                    a : this.angle([0,-1], [xr/f,yr/f]),
                    d : this.distance(x - xl,y - yl),
                    k : x * 100 + y
                });
            }


            let list2 : any = [];
           
            var sorted =  list.sort(function(a :any , b : any) {

               

                if(a.a == b.a)
                    return (a.d - b.d);
                return a.a - b.a;
            });


            while(sorted.length > 0)
            {
                var last = -1;
                var rest = [];
                while(sorted.length > 0)
                {
                    var e = sorted[0];
                    sorted.splice(0,1);
                    if(e.a == last)
                    {
                        rest.push(e);
                    }
                    else
                    {
                        last = e.a;
                        list2.push(e);
                    }
                }
                sorted = rest;
            }
            return list2;
       
    }

}
export { AsteroidMap };



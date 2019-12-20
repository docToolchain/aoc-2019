class Panel
{
    // tag::getPath[]
    getPath(): string {

        let cmd :string = "L";
        let d = { x : -1, y : 0 }
        let x = 46;
        let y = 50;
        while(true)
        {
            //go forward
            let l = 0;
            while(this.is(x + d.x, y +d.y,35))
            {
                x += d.x;
                y += d.y;
                l++;
            }
            cmd += ","+l;

            //get possible direction from this position
            let vd = [];
            if(this.is(x + 1,y,35))
                vd.push({ x : 1, y : 0 });
            if(this.is(x - 1,y,35))
                vd.push({ x : -1, y : 0 });
            if(this.is(x,y+1,35))
                vd.push( {x : 0, y : 1 });
            if(this.is(x,y-1,35))
                vd.push({ x : 0, y : -1 });

            //Ende
            if(vd.length == 1)
            {
                console.log("End found");
                break;
            }

            if(vd.length != 2)
                throw Error(JSON.stringify(vd));

            vd = vd.filter(i => Math.abs(d.x + i.x) == 1 &&  Math.abs(d.y + i.y) == 1);
            if(vd.length != 1)
                throw Error(JSON.stringify(vd));

            let nd = vd[0];
          
            if(d.x == -1 && nd.y == -1) cmd +=",R";
            if(d.x == -1 && nd.y == 1)  cmd +=",L";
            if(d.x ==  1 && nd.y == -1) cmd +=",L";
            if(d.x ==  1 && nd.y == 1)  cmd +=",R";
            if(d.y == -1 && nd.x == -1) cmd +=",L";
            if(d.y == -1 && nd.x == 1)  cmd +=",R";
            if(d.y ==  1 && nd.x == -1) cmd +=",R";
            if(d.y ==  1 && nd.x == 1)  cmd +=",L";
         
            d = nd;

        }

        return cmd;
    }
    // end::getPath[]

    x : number = 0;
    y : number = 0;
  
    fields : any = {};

  
    width : number = 0;
    height : number = 0;
    
    isIntersection(x: number, y: number) {
       
        return this.is(x,y,35) 
            && this.is(x+1,y,35) 
            && this.is(x-1,y,35) 
            && this.is(x,y+1,35) 
            && this.is(x,y-1,35);
    }
    is(x: number, y: number, c: number) {
        let k = x+","+y;
        if(k in this.fields)
            return this.fields[k] == c;
        return false;
    }
   
    getColorXY(x : number,y : number) : number
    {
        let k = x+","+y;
        if(k in this.fields)
            return this.fields[k];
        return 0;
    }
    setColorXY(x : number,y : number,c : number) : void
    {
        let k = x+","+y;
        this.fields[k] = c;
    }
    
    // tag::load[]
    load(input: number[]) {


        let x = 0;
        let y = 0;

        for(let c of input)
        {
           if(c == 10)
           {
               this.width = Math.max(x,this.width);
               x = 0;
               y++;
               continue;
           }
           if(c != 35 && c != 46)
           {
               console.log(x+","+y+" = "+c);
           }
           this.setColorXY(x,y,c);
           x++;
        }
        this.height = y;

    }
    // end::load[]

    // tag::getIntersectionAlignmentSum[]
    getIntersectionAlignmentSum() {
        let sum = 0;
        for(let y = 0;y <= this.height;y++)
        {
            for(let x = 0;x <= this.width;x++)
            {
                if(this.isIntersection(x,y))
                    sum += x  * y;
            }
        }
        return sum;
    }
    // end::getIntersectionAlignmentSum[]

    
  

    dump() : void
    {
        for(let y = 0;y <= this.height;y++)
        {
            let line = "";
            for(let x = 0;x <= this.width;x++)
            {
                line += String.fromCharCode(this.getColorXY(x,y));
            }
            console.log(line);
        }
    }

}
export { Panel }


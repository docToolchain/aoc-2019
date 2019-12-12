class Panel
{
    x : number = 0;
    y : number = 0;
    degree : number = 0;
    colors : any = {};

    minx : number = 0;
    maxx : number = 0;
    miny : number = 0;
    maxy : number = 0;

    getColor() : number
    {
        let k = this.x+","+this.y;
        if(k in this.colors)
            return this.colors[k];
        return 0;
    }
    getColorXY(x : number,y : number) : number
    {
        let k = x+","+y;
        if(k in this.colors)
            return this.colors[k];
        return 0;
    }
    setColor(c : number) : void
    {
        let k = this.x+","+this.y;
        this.colors[k] = c;
       

    }
    turn(dir : number) : void
    {
        //0 means it should turn left 90 degrees, and 1 means it should turn right 90 degrees.
        if(dir == 0)
            this.degree = (this.degree + 270)%360;
        else
            this.degree = (this.degree + 90)%360;
    }
    moveForward() :void
    {
        switch(this.degree)
        {
            case 0:
                this.y--;
                break;
            case 90:
                this.x++;
                break;   
            case 180:
                this.y++;
                break;
            case 270:
                this.x--;
                break;     
        }
        this.minx = Math.min(this.minx,this.x);
        this.maxx = Math.max(this.maxx,this.x);
        this.miny = Math.min(this.miny,this.y);
        this.maxy = Math.max(this.maxy,this.y);
    }
    countPanels() : number
    {
        return Object.keys(this.colors).length;
    }
    dump() : void
    {
        for(let y = this.miny;y <= this.maxy;y++)
        {
            let line = "";
            for(let x = this.minx;x <= this.maxx;x++)
            {
                line += this.getColorXY(x,y) == 0? ".":"#";
            }
            console.log(line);
        }
    }

}
export { Panel }


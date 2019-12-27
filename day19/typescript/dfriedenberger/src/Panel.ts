class Panel
{

    colors : any = {};
    width: number = 0;
    height: number = 0;

    getXY(x : number,y : number) : number
    {
        let k = x+","+y;
        if(k in this.colors)
            return this.colors[k];
        return 0;

    }
    setXY(x: number,y:number,c : number) : void
    {
        let k = x+","+y;
        this.colors[k] = c;
        this.width = Math.max(this.width,x+1);
        this.height = Math.max(this.height,y+1);
    }
    
    dump() : void
    {
        for(let y = 0;y < this.height;y++)
        {
            let line = "";
            for(let x = 0;x < this.width;x++)
            {
                line += this.getXY(x,y) == 0? ".":"#";
            }
            console.log(line);
        }
    }

}
export { Panel }


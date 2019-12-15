var Jimp = require('jimp');

class Panel
{
   
    colors : any = {};

    minx : number = 0;
    maxx : number = 0;
    miny : number = 0;
    maxy : number = 0;
    score: number = 0;
    padle: any = { x: -1 , y: -1 };
    ball: any = { x: -1 , y: -1 };

    load(a: number[]) {

        let l = a.length;
        for(let i = 0;i < l;i+=3)
        {
            let x = a[i];
            let y = a[i+1];
            let t = a[i+2];

            if(x == -1 && y == 0)
            {
                this.score = t;
                continue;
            }

            let k = x+","+y;
            this.colors[k] = t;

            if(t == 3) //paddle
            {
                this.padle.x = x;
                this.padle.y = y;
            }
            if(t == 4) //paddle
            {
                this.ball.x = x;
                this.ball.y = y;
            }
            this.minx = Math.min(this.minx,x);
            this.maxx = Math.max(this.maxx,x);
            this.miny = Math.min(this.miny,y);
            this.maxy = Math.max(this.maxy,y);
        }



    }
   
    getPadlePosition() {
        return this.padle;
    }
    getBallPosition() {
        return this.ball;
    }
   
  
    count(t : number) : number
    {
        let cnt = 0;
        for(let k of Object.keys(this.colors))
        {
            if(this.colors[k] == t)
                cnt++;
        }
        return cnt;
    }
    dump() : void
    {
        for(let y = this.miny;y <= this.maxy;y++)
        {
            let line = "";
            for(let x = this.minx;x <= this.maxx;x++)
            {
                line += this.getXY(x,y);
            }
            console.log(line);
        }
    }
    createImage(path: string,i : number,ext : string) : void {

        var s = "000000000" + i;
        let filename =  path + s.substr(s.length-6) + "." + ext;

        let heigth = this.maxy - this.miny;
        let width = this.maxx - this.minx;
        let symbol :any = {};
        for(let y = 0;y <= heigth;y++)
        {
            for(let x = 0;x <= width;x++)
            {
                let p = ['.','#','+','-','*'];
                symbol[x+","+y] = p[this.getXY(x + this.minx,y + this.miny)];
            }
        }

        //305, 355
   
        let xoff = (640 - 305) / 2;
        new Jimp(640, 360, 0x0, function (err : any, image : any) {
            Jimp.loadFont(Jimp.FONT_SANS_16_BLACK).then((font: any)  => {
                
                for(let y = 0;y <= heigth;y++)
                {
                    for(let x = 0;x <= width;x++)
                    {
                        image.print(font,xoff + x * 8 ,  y * 16,symbol[x+","+y] );
                    }
                }
             
                console.log("create "+filename);
                image.write(filename);

            }).catch(function (err: any) {
                console.error(err);
            });
        });

      

    }
    getXY(x: number, y: number) {

        let k = x+","+y;
        if(k in this.colors)
            return this.colors[k];
        return -1;
    }

}
export { Panel }


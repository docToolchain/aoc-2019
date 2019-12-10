
class Orbits {
    
   map : any =  {};

   // tag::add[]
   add(key : string, adj : string)
   {
       if(!(key in this.map)) 
            this.map[key] = [];
       this.map[key].push(adj);
   }
   // end::add[]

   // tag::count[]
   count(key : string,deep : number) : number {

        let c = deep;
        var keyList = this.map[key];
        if(keyList)
        {
            for(let k of keyList)
            {
                c += this.count(k,deep + 1);
            }
        }
        return c;
   }
   // end::count[]

   // tag::find[]
    find(key : string,list : any) : number {

        let sum = 0;
        if(key == "YOU") return 1;
        if(key == "SAN") return 1;

        var keyList = this.map[key];

        let cnt = 0;
        if(keyList)
        {
            for(let k of keyList)
            {
                let c = this.find(k,list);
                if(c > 0)
                {
                    cnt++;
                    sum += c + 1;
                }
            }
        }

        switch(cnt)
        {
            case 0: //nothing
            return 0;
            case 1:
              console.log("on the way "+sum);
              return sum;
            case 2:
                console.log("found "+(sum - 4));
                return 0;  
            default:
                throw new Error();
        }
   }
   // end::find[]

}

export { Orbits };
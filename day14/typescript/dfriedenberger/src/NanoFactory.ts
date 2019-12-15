import { Reaction } from './Reaction';
import { Chemical } from './Chemical';
import { ChemicalSet } from './ChemicalSet';
var fs = require('fs');

class NanoFactory {

    reactions: any = {};

    private parseChemical(chemical: string): Chemical {
        var p : Array<string> = chemical.split(' ');
        return new Chemical(parseInt(p[0]),p[1]);
    }

    //1 A, 2 B, 3 C => 2 D
    add(reaction : string) : void
    {
        var p : Array<string> = reaction.split("=>");
        let r : Reaction  = new Reaction(this.parseChemical(p[1].trim()));
        for(let chemical of p[0].split(","))
        {
            r.addInput(this.parseChemical(chemical.trim()));
        }

        let k : string = r.result.name;
        if(!(k in this.reactions))
            this.reactions[k] = [];
        this.reactions[k].push(r);

    }

    static load(filename : string) : NanoFactory
    {
        let data : string = fs.readFileSync(filename).toString('utf-8');

        let factory : NanoFactory = new NanoFactory();
        
        for(let line  of data.split('\r\n'))
        {
            factory.add(line);
        }

        return factory;
    }
    // tag::calculateOre[]
    calculateOre(cnt : number,chemical : string,rest : ChemicalSet) : number
    {
        let minore : number = -1;

        for(let r of this.reactions[chemical])
        {
           let ore = 0;

           var produce = r.result.cnt;
           var factor = 1;
           while(produce < cnt)
           {
               factor++;
               produce += r.result.cnt;
           }

           for(let c of r.inputs)
           {
             var needCnt = factor * c.cnt;
             if(c.name == "ORE")
                ore += needCnt;
             else
             {
                let re : number = rest.getCnt(c.name);
                if(re >= needCnt)
                {
                    rest.delete(needCnt,c.name);
                    continue;
                }
                
                rest.delete(re,c.name);

                ore += this.calculateOre(needCnt - re,c.name,rest);
             }
           }

           
           if((produce - cnt) > 0)
                rest.add(produce -cnt,chemical);
           if(minore == -1 || ore < minore)
           {
               minore = ore;
           }
        }

        //console.log(minore +" ore produce "+cnt+" "+chemical+" zuviel: "+JSON.stringify(rest.count));

        return minore;
    }
    // end::calculateOre[]

}

export { NanoFactory }


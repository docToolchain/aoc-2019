import { NanoFactory} from './NanoFactory';
import { ChemicalSet} from './ChemicalSet';



 let factory : NanoFactory = NanoFactory.load("input.txt");

 let min = 2690379; //Math.round(1000000000000 / 371695);

 let max = 5525109;
 while(min <= max)
 {
    let cnt = Math.round((min + max) / 2);
    let ore = factory.calculateOre(cnt,"FUEL",new ChemicalSet());
    console.log(cnt + " needs "+ore);
    console.log(" min "+min);
    console.log(" max "+max);

    if(ore > 1000000000000)
    {
        max = cnt - 1;
    }
    else
    {
        min = cnt + 1;
    }
 }

console.log("My puzzle answer is last result");
import { NanoFactory} from './NanoFactory';
import { ChemicalSet} from './ChemicalSet';

let factory : NanoFactory = NanoFactory.load("input.txt");

console.log("My puzzle answer is " + factory.calculateOre(1,"FUEL",new ChemicalSet()));
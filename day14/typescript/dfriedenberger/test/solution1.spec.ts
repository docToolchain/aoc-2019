import { expect } from 'chai';
import { NanoFactory } from '../src/NanoFactory';
import { ChemicalSet } from '../src/ChemicalSet';

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
            
         
         let factory : NanoFactory = new NanoFactory();
        
         factory.add("10 ORE => 10 A");
         factory.add("1 ORE => 1 B");
         factory.add("7 A, 1 B => 1 C");
         factory.add("7 A, 1 C => 1 D");
         factory.add("7 A, 1 D => 1 E");
         factory.add("7 A, 1 D => 1 E");
         factory.add("7 A, 1 E => 1 FUEL");
            

         expect(factory.calculateOre(1,"FUEL",new ChemicalSet())).to.equal(31);

        });

        it("run simple program2", () => {
            
         
            let factory : NanoFactory = new NanoFactory();
           
            factory.add("9 ORE => 2 A");
            factory.add("8 ORE => 3 B");
            factory.add("7 ORE => 5 C");
            factory.add("3 A, 4 B => 1 AB");
            factory.add("5 B, 7 C => 1 BC");
            factory.add("4 C, 1 A => 1 CA");
            factory.add("2 AB, 3 BC, 4 CA => 1 FUEL");

            expect(factory.calculateOre(1,"FUEL",new ChemicalSet())).to.equal(165);
   
           });


           it("run simple program3", () => {
            
         
                let factory : NanoFactory = NanoFactory.load("testcase1.txt");

                expect(factory.calculateOre(1,"FUEL",new ChemicalSet())).to.equal(13312);

           });


           it("run simple program4", () => {
            
         
            let factory : NanoFactory = NanoFactory.load("testcase2.txt");

            expect(factory.calculateOre(1,"FUEL",new ChemicalSet())).to.equal(2210736);

       });
    })
});
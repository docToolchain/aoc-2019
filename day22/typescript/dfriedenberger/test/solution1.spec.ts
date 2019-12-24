import { expect } from 'chai';
import { SpaceCards } from '../src/SpaceCards';
import { ShuffleService } from '../src/ShuffleService';

describe("Computer", () => {
    describe("run", () => {

        it("deal with new stack", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();

            cards =shuffleService.newStack(cards)

            
            expect(cards.cards.join(',')).to.equal("9,8,7,6,5,4,3,2,1,0");

        });

        it("cut 3", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();

            cards =shuffleService.cut(cards,3)

            
            expect(cards.cards.join(',')).to.equal("3,4,5,6,7,8,9,0,1,2");

        });

        it("cut -4", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();

            cards =shuffleService.cut(cards,-4)

            
            expect(cards.cards.join(',')).to.equal("6,7,8,9,0,1,2,3,4,5");

        });

        it("deal with increment 3", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();

            cards =shuffleService.increment(cards,3)

            
            expect(cards.cards.join(',')).to.equal("0,7,4,1,8,5,2,9,6,3");

        });

        it("deal with increment 7", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();

            cards =shuffleService.increment(cards,7)

            
            expect(cards.cards.join(',')).to.equal("0,3,6,9,2,5,8,1,4,7");

        });


        it("big usecase", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();


            let commands : string[] = [
            "deal with increment 7",
            "deal into new stack",
            "deal into new stack"];
             
            cards = shuffleService.shuffle(cards,commands);

            
            expect(cards.cards.join(',')).to.equal("0,3,6,9,2,5,8,1,4,7");

        });



        it("big usecase", () => {
            

            let cards = new SpaceCards(10);
            cards.init();

            let shuffleService = new ShuffleService();


            let commands : string[] = [
            "deal into new stack",
            "cut -2",
            "deal with increment 7",
            "cut 8",
            "cut -4",
            "deal with increment 7",
            "cut 3",
            "deal with increment 9",
            "deal with increment 3",
            "cut -1"];
             
            cards = shuffleService.shuffle(cards,commands);

            
            expect(cards.cards.join(',')).to.equal("9,2,5,8,1,4,7,0,3,6");

        });



        
    })
});
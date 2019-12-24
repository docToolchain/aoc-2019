
import { SpaceCards } from './SpaceCards';

class ShuffleService
{
    newStack(cards: SpaceCards): SpaceCards {
        let ncards: SpaceCards = new SpaceCards(cards.size);
        for(let i = 0;i < cards.size;i++)
        {
            ncards.cards[cards.size -1 - i] = cards.cards[i];
        }
        return ncards;
    }
    cut(cards: SpaceCards, n: number) : SpaceCards {

        //console.log("cut "+n);
        let ncards: SpaceCards = new SpaceCards(cards.size);
        for(let i = 0;i < cards.size;i++)
        {
            ncards.cards[(cards.size - n + i) % cards.size] = cards.cards[i];
        }

        return ncards;
    }
    increment(cards: SpaceCards, incr: number): SpaceCards {

        //console.log("deal with increment "+incr);
        let ncards: SpaceCards = new SpaceCards(cards.size);
        for(let i = 0;i < cards.size;i++)
        {
            ncards.cards[(i * incr) % cards.size] = cards.cards[i];
        }

        return ncards;

    }
    shuffle(cards: SpaceCards, commands: string[]): SpaceCards {
      
        for(let cmd of commands)
        {
            let p : string[] = cmd.split(" ");

            if(p[3] == "stack")
                cards = this.newStack(cards);
            if(p[0] == "cut")
                cards = this.cut(cards,parseInt(p[1]));
            if(p[2] == "increment")
                cards = this.increment(cards,parseInt(p[3]));
        }
        return cards;
    }

}
export { ShuffleService }
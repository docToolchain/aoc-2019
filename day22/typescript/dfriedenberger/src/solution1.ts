var fs = require('fs');
import { SpaceCards } from './SpaceCards';
import { ShuffleService } from './ShuffleService';


let cards = new SpaceCards(10007);
cards.init();

let shuffleService = new ShuffleService();


let data : string = fs.readFileSync("input.txt").toString('utf-8');

let commands : string[] = data.split('\r\n');

cards = shuffleService.shuffle(cards,commands);

for(let i = 0;i < cards.size;i++)
{
    if(cards.cards[i] == 2019)
        console.log("My puzzle answer is " + i);
}


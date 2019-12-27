class SpaceCards
{
    
    cards: number[];
    size: number;
    constructor(size : number)
    {
        this.size = size;
        this.cards = new Array(size);
    }
    init() : void {
        for(let i = 0;i < this.size;i++)
            this.cards[i] = i;
    }
}
export { SpaceCards };
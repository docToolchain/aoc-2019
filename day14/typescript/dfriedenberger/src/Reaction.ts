import { Chemical } from "./Chemical";

class Reaction {
   
    result: Chemical;
    inputs : Array<Chemical> = [];
    constructor(result: Chemical)
    {
        this.result = result;
    }
    addInput(input: Chemical) {
        this.inputs.push(input);
    }
}
export { Reaction };
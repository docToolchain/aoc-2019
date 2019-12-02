class Modul {
    
    mass: number;

    constructor(public pMass: number) {
        this.mass = pMass;
    }

    getMass() : number 
    {
        return this.mass;
    }
   
}

export { Modul };

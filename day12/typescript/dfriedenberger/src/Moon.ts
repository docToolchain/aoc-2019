class Moon
{
    x : Array<number> = [];
    v : Array<number> = [0,0,0];

    constructor({ x, y, z }: { x: number; y: number; z: number; })
    {
        this.x[0] = x;
        this.x[1] = y;
        this.x[2] = z;
    }

    private caclulateEnergy(a : Array<number>) : number {
        let e = 0;
        for(let i = 0;i < 3;i++)
            e += Math.abs(a[i]);
        return e;
    }

    getPotentialEnergy() : number {
       return this.caclulateEnergy(this.x);
    }

    getKineticEnergy() : number {
        return this.caclulateEnergy(this.v);
    }
   
}
export { Moon }
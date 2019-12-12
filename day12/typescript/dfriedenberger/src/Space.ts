import { Moon } from './Moon';
class Space
{
    moons : Array<Moon> = [];
    add(moon : Moon)
    {
        this.moons.push(moon);
    }

    timeStep() : void
    {
        //update velocity by gravity
        let l = this.moons.length;
        let velocityChange = (x: number, y: number): number => {
            if(x == y) return 0;
            return (x < y) ? 1 : -1;    
        }

        for(let i = 0;i < l;i++)
            for(let j = i+1;j < l;j++)
            {
                let m1 : Moon = this.moons[i];
                let m2 : Moon = this.moons[j];
                //foreach Coordinate
                for(let k = 0;k < 3;k++)
                {
                    m1.v[k] += velocityChange(m1.x[k],m2.x[k]);
                    m2.v[k] += velocityChange(m2.x[k],m1.x[k]);
                }
            }

        //update position
        for(let i = 0;i < l;i++)
        {
            let m : Moon = this.moons[i];
            for(let k = 0;k < 3;k++)
            {
                m.x[k] += m.v[k];
            }
        }
    }

    getEnergy() :number {

        let energy = 0;
        let l = this.moons.length;
        for(let i = 0;i < l;i++)
        {
            let m1 : Moon = this.moons[i];
            energy += m1.getPotentialEnergy() * m1.getKineticEnergy();
        }    
        return energy;
    }

    getDataSet(x : number) : Array<number>
    {
        let a = [];
        let l = this.moons.length;
        for(let i = 0;i < l;i++)
        {
            let m : Moon = this.moons[i];
            a.push(m.x[x]);
            a.push(m.v[x]);
        }    
        return a;
    }
}
export {Space }
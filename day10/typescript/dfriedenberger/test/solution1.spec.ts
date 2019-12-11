import { expect } from 'chai';
import { AsteroidMap } from '../src/AsteroidMap';

describe("Computer", () => {
    describe("run", () => {

       
        it("run simple program1", () => {
            
            let map = new AsteroidMap();

            map.addRow(".#..#");
            map.addRow(".....");
            map.addRow("#####");
            map.addRow("....#");
            map.addRow("...##");
            
            expect(map.count()).to.be.equal(10);
            expect(map.is(0,0)).to.be.false;
            expect(map.is(1,0)).to.be.true;

            expect(map.factor(12,3)).to.be.equal(3);

            expect(map.diagonale(0,0,1,0)).to.be.equal(2);
            expect(map.diagonale(0,0,0,1)).to.be.equal(1);


            expect(map.countxy(3,4)).to.be.equal(8);
            expect(JSON.stringify(map.findmaxcountxy())).to.be.equal(JSON.stringify({ max: 8, x: 3, y: 4 }));

        });

        it("run simple program2", () => {
            let map = new AsteroidMap();

            map.addRow(".#..##.###...#######");
            map.addRow("##.############..##.");
            map.addRow(".#.######.########.#");
            map.addRow(".###.#######.####.#.");
            map.addRow("#####.##.#.##.###.##");
            map.addRow("..#####..#.#########");
            map.addRow("####################");
            map.addRow("#.####....###.#.#.##");
            map.addRow("##.#################");
            map.addRow("#####.##.###..####..");
            map.addRow("..######..##.#######");
            map.addRow("####.##.####...##..#");
            map.addRow(".#####..#.######.###");
            map.addRow("##...#.##########...");
            map.addRow("#.##########.#######");
            map.addRow(".####.#.###.###.#.##");
            map.addRow("....##.##.###..#####");
            map.addRow(".#.#.###########.###");
            map.addRow("#.#.#.#####.####.###");
            map.addRow("###.##.####.##.#..##");

            expect(map.countxy(11,13)).to.be.equal(210);

            expect(JSON.stringify(map.findmaxcountxy())).to.be.equal(JSON.stringify({ max: 210, x: 11, y: 13 }));

        });

    })
});
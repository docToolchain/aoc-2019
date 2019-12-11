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
            
           
            let a = map.vaporizationOrder(3,4);

            expect(a.length).to.be.equal(9);
            expect(a[0].k).to.be.equal(302);
            expect(a[1].k).to.be.equal(400);


        });

        it("run simple program1.1", () => {
            
            let map = new AsteroidMap();

            map.addRow(".#....#####...#..");
            map.addRow("##...##.#####..##");
            map.addRow("##...#...#.#####.");
            map.addRow("..#.....#...###..");
            map.addRow("..#.#.....#....##");

           
            let a = map.vaporizationOrder(8,3);
            
            expect(a[0].k).to.be.equal(801);

            expect(a[1].k).to.be.equal(900);
            expect(a[2].k).to.be.equal(901);
            expect(a[3].k).to.be.equal(1000);
            expect(a[4].k).to.be.equal(902);
            expect(a[5].k).to.be.equal(1101);
            expect(a[6].k).to.be.equal(1201);
            expect(a[7].k).to.be.equal(1102);
            expect(a[8].k).to.be.equal(1501);

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

            let a = map.vaporizationOrder(11,13);
            
            expect(a.length).to.be.equal(299);

            expect(a[0].k).to.be.equal(1112);  
            expect(a[1].k).to.be.equal(1201);
            expect(a[2].k).to.be.equal(1202);
            expect(a[9].k).to.be.equal(1208);
            expect(a[19].k).to.be.equal(1600);
            expect(a[49].k).to.be.equal(1609);
            expect(a[99].k).to.be.equal(1016);
            expect(a[198].k).to.be.equal(906);
            expect(a[199].k).to.be.equal(802);
            expect(a[200].k).to.be.equal(1009);
            expect(a[298].k).to.be.equal(1101);

        });

    })
});
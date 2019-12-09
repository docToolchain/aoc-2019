import { expect } from 'chai';
import { Layer } from "../src/Layer";
import { LayerManager } from "../src/LayerManager";

describe("Computer", () => {
    describe("run", () => {

        it("run simple program1", () => {
           
            
            let layer : Layer = new Layer(3,2);

            layer.set("123456");

            expect(layer.get(0,0)).to.be.equal("1");
            expect(layer.get(1,0)).to.be.equal("2");
            expect(layer.get(0,1)).to.be.equal("4");

           
        });

        it("run simple program2", () => {
           
            let code = "0222112222120000";
            
            let layerManager : LayerManager = new LayerManager(2,2);

            layerManager.load(code);


            expect(layerManager.layers.length).to.be.equal(4);
          
            let layer : Layer = layerManager.joinLayers();

            expect(layer.code).to.be.equal("0110");

           
        });
      
    })
});
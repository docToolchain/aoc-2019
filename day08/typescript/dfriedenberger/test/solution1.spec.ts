import { expect } from 'chai';
import { Layer } from "../src/Layer";
import { LayerManager } from "../src/LayerManager";

describe("Computer", () => {
    describe("run", () => {
        it("run simple program1", () => {
           
            let code = "1222223";
            
            let layer : Layer = new Layer(3,2);

            layer.set(code.substr(0,6));
            console.log(layer.codes);

            expect(layer.count('1')).to.be.equal(1);
            expect(layer.count('2')).to.be.equal(5);
            expect(layer.count('3')).to.be.equal(0);

           
        });

        it("run simple program2", () => {
           
            let code = "123456789012";
            
            let layerManager : LayerManager = new LayerManager(3,2);

            layerManager.load(code);


            expect(layerManager.layers.length).to.be.equal(2);
          
            let layer : Layer = layerManager.selectWithFewestDigits('0');

            expect(layer.count('6')).to.be.equal(1);
            expect(layer.count('5')).to.be.equal(1);


           
        });
      
    })
});
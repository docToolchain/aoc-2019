var fs = require('fs');
import { Layer } from "./Layer";
import { LayerManager } from "./LayerManager";

let layerManager : LayerManager = new LayerManager(25,6);

let code = fs.readFileSync("input.txt").toString('utf-8');


layerManager.load(code);

// tag::star2[]
let layer : Layer = layerManager.joinLayers();
for(let t = 0;t < 6;t++)
{
    let line = "";
    for(let w = 0;w < 25;w++)
    {
        let c = layer.get(w,t);
        line += (c == '1')?"*":" ";
    }
    console.log(line);
}
// end::star2[]



console.log("My puzzle answer is " +"(see image)");

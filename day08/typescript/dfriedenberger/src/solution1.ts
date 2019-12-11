var fs = require('fs');
import { Layer } from "./Layer";
import { LayerManager } from "./LayerManager";

let layerManager : LayerManager = new LayerManager(25,6);

let code = fs.readFileSync("input.txt").toString('utf-8');
layerManager.load(code);


// tag::star1[]
let layer : Layer = layerManager.selectWithFewestDigits('0');
var result = layer.count('1') * layer.count('2');
// end::star1[]




console.log("My puzzle answer is " +result);

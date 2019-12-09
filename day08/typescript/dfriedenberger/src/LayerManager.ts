import { Layer } from "./Layer";

class LayerManager
{
    wide : number;
    tall : number;
    layers : Array<Layer> = [];
    constructor(wide : number, tall : number)
    {
        this.wide = wide;
        this.tall = tall;
    }    

    // tag::load[]
    load(code : string) : void
    {
        let size = this.wide * this.tall;
        let length = code.length;
        for(let i = 0;i < length;i += size)
        {
            let layer : Layer = new Layer(this.wide,this.tall);
            layer.set(code.substr(i,size));
            this.layers.push(layer);
        }
    }
    // end::load[]

    // tag::selectWithFewestDigits[]
    selectWithFewestDigits(digit : string) :Layer 
    {
        let minLayer : Layer = undefined;
        this.layers.forEach(layer => {
            
            if((minLayer == undefined) || (layer.count(digit) < minLayer.count(digit)))
            {
                minLayer = layer;
            }

        });
        return minLayer;
    }
    // end::selectWithFewestDigits[]

    // tag::joinLayers[]
    joinLayers() : Layer
    {
        let code : string = "";
        
        for(let t = 0;t < this.tall;t++)
        {
            for(let w = 0;w < this.wide;w++)
            {
                //get code for Layer first non 2 wins
                let c;
                for(let i = 0;i < this.layers.length;i++)
                {
                    c = this.layers[i].get(w,t);
                    if(c != '2') break;
                }
                code += c;
            }
        }

        let layer : Layer = new Layer(this.wide,this.tall);
        layer.set(code);
        return layer;
    }
    // end::joinLayers[]

} 


export {LayerManager}

class Layer {

   wide : number;
   tall : number;
   code : string;
   codes : any = {};
   constructor(wide : number, tall : number)
   {
       this.wide = wide;
       this.tall = tall;
   }
  
   set(code : string) : void
   {
       this.code = code;
        code.split('').forEach(c => {
            if(!(c in this.codes))
                this.codes[c] = 0;
            this.codes[c]++; 
        });
    
   }

   get(w : number, t : number) : string
   {
       return this.code.substr(t * this.wide + w,1);
   }

   count(digit : string)
   {
     if(!(digit in this.codes))
        return 0;
     return this.codes[digit];
   }

}

export { Layer };
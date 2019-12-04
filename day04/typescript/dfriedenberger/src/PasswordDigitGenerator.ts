import { PasswordDigitValidator } from "./PasswordDigitValidator";

class PasswordDigitGenerator {
    passwordDigitValidator : PasswordDigitValidator;
    min : number;
    max : number;
    cnt : number = 0;
    constructor(min : number, max : number, passwordDigitValidator : PasswordDigitValidator)
    {
        this.min = min;
        this.max = max;
        this.passwordDigitValidator = passwordDigitValidator;
    }

    getCnt() : number
    {
        return this.cnt;
    }

    gen() : void
    {
        // tag::start[]
        /* It is a six-digit number. => Starts Digit 1 */
        this.digit({ digits: [0, 0, 0, 0, 0, 0], sumprev: 0, pos: 0, start: 1 });
        // end::start[]
    }

    // tag::digit[]
    digit({ digits, sumprev, pos, start }: 
        { digits: any; sumprev: number; pos: number; start: number; }) : void
    {
        //Going from left to right, the digits never decrease.
        for(let i = start;i <= 9;i++)
        {
                digits[pos] = i;
                let sum = sumprev * 10 + i;
                
                // tag::validate[]
                if(pos == 5)
                {
                    //The value is within the range given in your puzzle input.
                    if(sum < this.min) continue;
                    if(sum > this.max) continue;

                    
                    if(!this.passwordDigitValidator.validate(digits)) continue;

                    this.cnt++;
                    continue;
                }
                // end::validate[]

                //start is set to current digit
                this.digit({ digits, sumprev: sum, pos: pos + 1, start: i });
        }
    }   
    // end::digit[]

}

export { PasswordDigitGenerator };
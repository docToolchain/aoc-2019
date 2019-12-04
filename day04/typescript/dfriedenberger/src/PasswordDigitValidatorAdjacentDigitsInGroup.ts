import { PasswordDigitValidator } from "./PasswordDigitValidator";

class PasswordDigitValidatorAdjacentDigitsInGroup implements PasswordDigitValidator {

    validate(digits : Array<number>) : boolean
    {
        //Two adjacent digits are the same (like 22 in 122345).
        for(let x = 1;x < 6;x++)
        {
            if((x > 1) && (digits[x-2] == digits[x])) continue;
            if((x < 5) && (digits[x+1] == digits[x])) continue;

            if(digits[x-1] == digits[x])
                return true;
        }
        return false;
    }

}

export { PasswordDigitValidatorAdjacentDigitsInGroup };
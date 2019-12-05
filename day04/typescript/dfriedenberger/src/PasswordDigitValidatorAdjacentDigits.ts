import { PasswordDigitValidator } from "./PasswordDigitValidator";

class PasswordDigitValidatorAdjacentDigits implements PasswordDigitValidator {

    validate(digits : Array<number>) : boolean
    {
        //Two adjacent digits are the same (like 22 in 122345).
        for(let x = 1;x < 6;x++)
        {
           if(digits[x-1] == digits[x])
                return true;
        }
        return false;
    }

}

export { PasswordDigitValidatorAdjacentDigits };
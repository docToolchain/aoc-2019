import { expect } from 'chai';
import { PasswordDigitValidatorAdjacentDigits } from "../src/PasswordDigitValidatorAdjacentDigits";

describe("PasswordDigitValidator", () => {
    describe("validate", () => {
        it(" 123456 is false ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigits();

          expect(passwordDigitValidator.validate([1,2,3,4,5,6])).to.be.false;

        });

        it(" 123446 is true ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigits();

          expect(passwordDigitValidator.validate([1,2,3,4,4,6])).to.be.true;

        });

        it(" 123444 is true ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigits();

          expect(passwordDigitValidator.validate([1,2,3,4,4,4])).to.be.true;

        });
    })
});
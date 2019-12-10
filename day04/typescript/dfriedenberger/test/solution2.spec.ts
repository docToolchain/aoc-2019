import { expect } from 'chai';
import { PasswordDigitValidatorAdjacentDigitsInGroup } from "../src/PasswordDigitValidatorAdjacentDigitsInGroup";

describe("PasswordDigitValidator", () => {
    describe("validate", () => {
        it(" 123456 is false ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigitsInGroup();

          expect(passwordDigitValidator.validate([1,2,3,4,5,6])).to.be.false;

        });

        it(" 123446 is true ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigitsInGroup();

          expect(passwordDigitValidator.validate([1,2,3,4,4,6])).to.be.true;

        });

        it(" 123444 is false ", () => {
          
          let passwordDigitValidator = new PasswordDigitValidatorAdjacentDigitsInGroup();

          expect(passwordDigitValidator.validate([1,2,3,4,4,4])).to.be.false;

        });
    })
});
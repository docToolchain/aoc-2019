import { PasswordDigitValidatorAdjacentDigits } from "./PasswordDigitValidatorAdjacentDigits";
import { PasswordDigitGenerator } from "./PasswordDigitGenerator";


let generator = new PasswordDigitGenerator(353096,843212,new PasswordDigitValidatorAdjacentDigits()); 

generator.gen();

console.log("My puzzle answer is " + generator.getCnt());

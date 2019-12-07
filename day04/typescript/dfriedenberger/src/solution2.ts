import { PasswordDigitValidatorAdjacentDigitsInGroup } from "./PasswordDigitValidatorAdjacentDigitsInGroup";
import { PasswordDigitGenerator } from "./PasswordDigitGenerator";


let generator = new PasswordDigitGenerator(353096,843212,new PasswordDigitValidatorAdjacentDigitsInGroup()); 

generator.gen();

console.log("My puzzle answer is " + generator.getCnt());

println findValidNumbers(245182, 790572) + " valid numbers for Star 1 found"
println findValidNumbersStar2(245182, 790572) + " valid numbers for Star 2 found"

// tag::Star1[]
def findValidNumbers(min, max){
    validNumbers = 0
    for(int i = min; i <= max; i++){
        foundDigitTwin = false
        isValidNumber = true

        digits = splitNumberInDigits(i)

        int previousDigit = digits[0]
        for(int j = 1; j < 6; j++){
            currentDigit = digits[j]
            if(previousDigit > currentDigit){
                isValidNumber = false
                break
            }
            if(previousDigit == currentDigit){
                foundDigitTwin = true
            }
            previousDigit = currentDigit
        }

        if(isValidNumber && foundDigitTwin){
            validNumbers++
        }
    }
    return validNumbers
}
// tag::Star1[]

// tag::Star2[]
def findValidNumbersStar2(min, max){
    validNumbers = 0
    for(int i = min; i <= max; i++){
    
        isValidNumber = true

        twinDigitList = []
        moreThanTwinList = []

        digits = splitNumberInDigits(i)

        int previousDigit = digits[0]
        for(int j = 1; j < 6; j++){
            currentDigit = digits[j]
            if(previousDigit > currentDigit){
                isValidNumber = false
                break
            }
            if(previousDigit == currentDigit){

                if(twinDigitList.contains(currentDigit)){
                    twinDigitList -= currentDigit
                    moreThanTwinList += currentDigit
                } 
                if(!(moreThanTwinList.contains(currentDigit))){
                    twinDigitList += currentDigit
                }                
            }
            previousDigit = currentDigit
        }

        if(isValidNumber && !twinDigitList.isEmpty()){
            validNumbers++
        }
    }
    return validNumbers
}
// tag::Star2[]

Integer[] splitNumberInDigits(Integer number){
    digits =  new Integer[6]
    intermediate = number
    for(int j = 5; j >= 0; j--){
        curDigit = intermediate % 10
        intermediate = (intermediate / 10).trunc().intValue() 
        digits[j] = curDigit
    }
    return digits
}
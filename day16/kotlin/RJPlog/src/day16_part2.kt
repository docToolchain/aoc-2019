fun main(args: Array<String>) {

// day 16 FFT in Kotlin, 16.1.2020
// part2, runtime ~ 14hours :-)
	
var puzzle_input: IntArray = intArrayOf(5,9,7,6,8,0,9,2,8,3,9,9,2,7,7,5,8,5,6,5,1,9,1,2,9,8,6,2,5,2,1,5,1,0,6,3,7,1,8,9,0,1,1,8,0,5,1,4,2,6,2,5,0,8,5,5,9,2,4,7,6,4,1,9,4,4,1,1,5,2,8,0,0,4,7,1,8,7,0,9,8,8,6,4,0,2,9,0,3,4,3,5,5,6,9,6,2,7,9,8,2,4,8,5,3,0,1,9,2,1,6,4,9,2,4,0,8,2,0,0,5,9,8,2,7,1,6,1,0,2,4,6,3,1,6,1,2,2,9,0,0,0,5,1,0,6,3,0,4,7,2,4,8,4,6,6,8,0,4,1,5,6,9,0,1,8,3,3,7,1,4,6,9,0,3,7,4,1,8,1,2,6,3,8,3,4,5,0,3,7,0,7,4,1,0,7,8,6,8,4,9,7,4,5,9,8,6,6,2,6,4,2,9,5,6,7,9,4,0,1,2,8,2,5,2,7,1,4,8,7,3,2,9,2,4,3,5,8,3,1,1,7,5,3,7,8,7,3,5,6,5,3,3,2,1,6,6,7,4,4,1,2,8,8,4,5,0,0,6,8,0,6,8,7,8,7,1,7,9,5,5,9,4,6,5,3,4,1,5,8,8,3,7,3,7,0,4,5,1,9,3,5,9,1,9,7,9,0,4,6,9,8,1,5,1,4,3,3,4,1,5,9,9,8,2,0,0,1,6,4,6,9,3,6,8,6,8,4,8,9,3,1,2,2,7,6,6,8,5,7,2,6,1,4,2,6,7,9,9,6,3,6,5,5,9,5,2,5,0,0,3,8,7,7,0,9,0,5,7,9,8,4,5,7,2,5,6,7,6,4,8,1,2,7,6,9,7,7,7,8,1,2,7,0,6,2,7,5,5,8,9,0,1,4,3,3,5,0,1,5,6,5,3,3,7,4,0,9,7,1,6,8,5,8,9,4,9,2,0,3,4,3,0,1,8,1,1,0,3,2,7,8,1,9,4,4,2,8,5,4,6,3,8,5,0,6,3,9,1,1,2,3,9,4,7,8,8,0,4,7,1,7,7,4,4,9,7,7,9,9,8,8,4,1,4,3,4,0,6,1,6,8,8,0,0,0,3,8,3,4,5,6,1,7,6,4,9,4,2,1,0,6,9,1,8,6,1,9,5,7,2,4,3,3,7,0,2,4,5,1,7,0,2,2,3,8,6,2,3,0,4,6,6,3,9,3,2,8,7,4,4,5,4,6,2,4,2,3,4,2,2,6,3,6,1,6,4,2,6,7,8,2,5,9,0,2,0,0,9,4,8,0,1,7,7,4,8,2,5,6,9,4,4,2,3,0,6,0,7,0,0,3,1,2,5,0,4,2,8,6,4,7,5,3,0,5,6,7,4,8,6,4,4,4,2,2,5,0,7,0,9,0,2,9,8,1,2,3,7,9)
	
// preparation of input vektor
var FFTinput = IntArray(6500005) {0}
var FFToutput= IntArray(6500005) {0}
var FFTfactor= IntArray(6500005) {0}
	
for (j in 0 .. 649){ 
	FFTinput[j+3]= puzzle_input[j]
}			

for (i in 0 ..9999)	{
	for (j in 0 .. 649){
		FFTinput[j+3+i*650]= puzzle_input[j]
	}			
}
println("array preparation ready")	
	
// main loop
var NumofValues: Int = 6500000
var offset: Int = 5976809 //5976809
var toggle: Int 
var togglecount: Int 
	
//Schleife für Iterationen
for (intK in 1 .. 100) {
//Berechnen der Muster entsprechend des Anzahl der Eingangswerte
println(intK)
	for (intJ in offset .. NumofValues) {
    toggle = 0
    togglecount = intJ - 1
    for (intI in intJ .. NumofValues + 1) { // '+1 is due to the offset of one!
        if (toggle == 0)  {
            FFTfactor[intI + 1] = 0
		} else if (toggle == 1) {
             FFTfactor[intI + 1] = 1
		} else if (toggle == 2) {
             FFTfactor[intI + 1] = 0
		} else if (toggle == 3) {
            FFTfactor[intI + 1] = -1
		} // End If
        togglecount = togglecount + 1
        if (togglecount >= intJ) {
            togglecount = 0
            toggle = toggle + 1
            if (toggle == 4) {
                toggle = 0
			} // End If
		} // End If
	} // Next intI
    // 'Berechnen der einzelnen Ausgangswerte
    for (intI in intJ .. NumofValues) {
		FFToutput[intJ + 2] = FFToutput[intJ + 2] + (FFTinput[intI + 2] * FFTfactor[intI + 2])
	} // Next intI
} // Next intJ

// Abschneiden der letzten Stelle und schreiben als input
for (intJ in offset .. NumofValues) {
	FFTinput[intJ + 2] = FFToutput[intJ + 2] % 10
	if (FFTinput[intJ + 2] < 0) {FFTinput[intJ + 2] = - FFTinput[intJ + 2]}
FFToutput[intJ + 2] = 0
} // Next intJ


} // Next intK
	
println("solution:")	
	print(FFTinput[3+offset])
	print(FFTinput[4+offset])
	print(FFTinput[5+offset])
	print(FFTinput[6+offset])
	print(FFTinput[7+offset])
	print(FFTinput[8+offset])
	print(FFTinput[9+offset])
	println(FFTinput[10+offset])
	
}	

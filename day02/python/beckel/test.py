import computer

test_input = [ [1,9,10,3,2,3,11,0,99,30,40,50], [1,0,0,0,99], [2,3,0,3,99], [2,4,4,5,99,0], [1,1,1,4,99,5,6,0,99] ]
test_output = [ [3500,9,10,70,2,3,11,0,99,30,40,50], [2,0,0,0,99], [2,3,0,6,99], [2,4,4,5,99,9801], [30,1,1,4,2,5,6,0,99] ]

for i in range(0, len(test_input)):
    computer.compute(test_input[i])
    if (test_input[i] == test_output[i]):
        print("Test " + str(i) + " successful.")
    else:
        print("Test " + str(i) + " FAILED.")
        
        
import pytest
from computer import Computer

program_1 = [109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99]
program_2 = [1102,34915192,34915192,7,4,7,99,0]
program_3 = [104,1125899906842624,99]

computer1 = Computer(program_1, [])
computer1.run()
result_1 = computer1.get_whole_output()
print("Result test 1: " + str(result_1))
if (result_1 == program_1):
   print("Test 1 successful")

computer2 = Computer(program_2, [])
computer2.run()
result_2 = computer2.get_last_output()
print("Result test 2: " + str(result_2))
if (result_2 == 1219070632396864):
    print("Test 2 successful")

computer3 = Computer(program_3, [])
computer3.run()
result_3 = computer3.get_last_output()
print("Result test 3: " + str(result_3))
if (result_3 == 1125899906842624):
    print("Test 3 successful")


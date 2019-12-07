import pytest
import amplifier_sequence

program_1 = [3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0]
program_2 = [3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0]
program_3 = [3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0]

phase_setting_1 = [4,3,2,1,0]
phase_setting_2 = [0,1,2,3,4]
phase_setting_3 = [1,0,4,3,2]

signal_output_1 = 43210
signal_output_2 = 54321
signal_output_3 = 65210

result_1 = amplifier_sequence.run_standalone(program_1, phase_setting_1)
assert(result_1 == signal_output_1)

result_1 = amplifier_sequence.run_standalone(program_2, phase_setting_2)
assert(result_1 == signal_output_2)

result_1 = amplifier_sequence.run_standalone(program_3, phase_setting_3)
assert(result_1 == signal_output_3)
from computer import Computer

def run_standalone(program, phase_configuration):
    in_val = 0
    for i in phase_configuration:
        amplifier = Computer(program.copy(), [i, in_val])
        amplifier.run()
        out_val = amplifier.get_last_output()
        in_val = out_val

    return out_val

def run_feedback(program, phase_configuration):

    in_val = 0
    num_amplifiers = len(phase_configuration)
    
    # initialize amplifiers
    amplifiers = []
    for i in phase_configuration:
        amplifiers.append(Computer(program.copy(), [i]))
    
    # start first amplifier
    amplifiers[0].provide_input(in_val)

    # run amplifier and connect output to next amplifier (if not halted)
    i = 0
    while(True):
        amplifiers[i].run()
        output = amplifiers[i].get_last_output()
        i = (i+1) % num_amplifiers
        if (amplifiers[i].has_terminated() == False):
            amplifiers[i].provide_input(output)
        else:
            return output

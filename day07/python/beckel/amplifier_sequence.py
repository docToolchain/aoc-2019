from computer import Computer

def run_standalone(program, phase_configuration):
    in_val = 0
    for i in phase_configuration:
        amplifier = Computer(program.copy(), [i, in_val])
        amplifier.run()
        out_val = amplifier.get_last_output()
        in_val = out_val

    return out_val
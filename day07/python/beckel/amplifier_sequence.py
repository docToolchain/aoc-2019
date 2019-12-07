import computer

def run_standalone(program, phase_configuration):
    in_val = 0
    for i in phase_configuration:
        program_copy = program.copy()
        out_val = computer.compute(program_copy, [i, in_val])
        in_val = out_val

    return out_val
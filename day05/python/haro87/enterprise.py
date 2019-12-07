import logging
from enum import Enum

logging.basicConfig(level=logging.ERROR)

class Instruction(Enum):
    ADD = 1
    MULTIPLY = 2
    STORE = 3
    OUTPUT = 4
    JUMP_IF_TRUE = 5
    JUMP_IF_FALSE = 6
    LESS_THAN = 7
    EQUALS = 8

class Mode(Enum):
    POSITION = 0
    IMMEDIATE = 1

class Command:
    inst = 0
    modes = [].copy()
    def __init__(self, opcode):
        self.inst = opcode // 10**0 % 10
        del self.modes[:]
        self.modes.append(opcode // 10**2 % 10)
        self.modes.append(opcode // 10**3 % 10)
        self.modes.append(opcode // 10**4 % 10)


    def instruction(self):
        return self.inst

    def parameter_modes(self):
        return self.modes

class MontgomeryScott:

    def __init__(self, input):
        self.input = input

    def _add(self, one, two):
        return one + two

    def _multiply(self, one, two):
        return one * two

    def _store(self, value, add, program):
        if add < len(program):
            program[add] = value
        else:
            logging.error("ERROR -> Address: {0} out of bounds".format(add))

    def _output(self, add, program):
        if add < len(program):
            return program[add]
        else:
            logging.error("ERROR -> Address: {0} out of bounds".format(add))
            return None

    def _get_parameters(self, count, command, program):
        params = []
        pos = count + 1
        for mode in command.parameter_modes():
            if command.instruction() > 4 and command.instruction() < 7 and len(params) == 2:
                break
            if mode == Mode.IMMEDIATE.value:
                if pos < len(program):
                    params.append(program[pos])
                else:
                    logging.error("ERROR -> Address: {0} out of bounds".format(pos))
            if mode == Mode.POSITION.value:
                if pos < len(program):
                    add = program[pos]
                    if len(params) == 2:
                        if  add < len(program):
                            params.append(add)
                        else:
                            logging.error("ERROR -> Address: {0} out of bounds".format(add))
                    else:    
                        if  add < len(program):
                            params.append(program[add])
                        else:
                            logging.error("ERROR -> Address: {0} out of bounds".format(add))
                else:
                    logging.error("ERROR -> Address: {0} out of bounds".format(pos))
            pos = pos + 1

        if command.instruction() > 2 and command.instruction() <= 4:
             params = [params[0]]
        logging.info("INFO -> Collected paramters: {0}".format(params))

        return params

    def translate(self, code):
        
        trans = str(code).split(',')
        trans = list(map(int, trans))
        return trans
        

    def execute(self, commands):
        output = []
        count = 0
        while count <= len(commands):
            c =  commands[count]
            if c == 99:
                break
            command = Command(c)
            if command.instruction() < 1 or command.instruction() > 8:
                count = count + 1
                continue
            logging.info("INFO -> Instruction: {0}".format(command.instruction()))
            if (command.instruction() > 0 and command.instruction() < 3) or command.instruction() > 4:
                params = self._get_parameters(count, command, commands)
                logging.info("INFO -> Parameters: {0}".format(params))
            if command.instruction() == Instruction.ADD.value:
                if len(params) < 3:
                    logging.error("ERROR -> Not enough paramters for ADD at position {0}".format(count))
                    continue
                self._store(self._add(params[0], params[1]), params[2], commands)
                count = count + 4
            elif command.instruction() == Instruction.MULTIPLY.value:
                if len(params) < 3:
                    logging.error("ERROR -> Not enough paramters for MULTIPLY at position {0}".format(count))
                    continue
                self._store(self._multiply(params[0], params[1]), params[2], commands)
                count = count + 4
            elif command.instruction() == Instruction.STORE.value:
                self._store(self.input, commands[count + 1], commands)
                count = count +2
            elif command.instruction() == Instruction.OUTPUT.value:
                out = self._output(commands[count + 1], commands)
                output.append(out)
                print("-->OUTPUT: {0}".format(out))
                count = count + 2
            elif command.instruction() == Instruction.JUMP_IF_TRUE.value:
                if params[0] != 0:
                    count = params[1]
                else:
                    count = count + 3
            elif command.instruction() == Instruction.JUMP_IF_FALSE.value:
                if params[0] == 0:
                    count = params[1]
                else:
                    count = count + 3
            elif command.instruction() == Instruction.LESS_THAN.value:
                if params[0] < params [1]:
                    self._store(1, params[2], commands)
                else:
                    self._store(0, params[2], commands)
                count = count + 4
            elif command.instruction() == Instruction.EQUALS.value:
                if params[0] == params[1]:
                    self._store(1, params[2], commands)
                else:
                    self._store(0, params[2], commands)
                count = count + 4
            else:
                count = count + 1
        return output


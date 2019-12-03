class MontgomeryScott:

    def __init__(self):
        pass

    def _add(self, one, two):
        return one + two

    def _multiply(self, one, two):
        return one * two

    def translate(self, code):
        trans = str(code).split(',')
        trans = list(map(int, trans))
        return trans
        

    def execute(self, commands):
        count = 0
        while count <= len(commands):
            cmd = commands[count:count+4]
            if cmd[0] == 1:
                commands[cmd[3]] = self._add(commands[cmd[1]], commands[cmd[2]])
            if cmd[0] == 2:
                commands[cmd[3]] = self._multiply(commands[cmd[1]], commands[cmd[2]])
            count = count +  4
            if cmd[0] == 99:
                break

        return commands


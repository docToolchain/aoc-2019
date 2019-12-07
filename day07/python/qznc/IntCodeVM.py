#!/usr/bin/env python3

def assert_eq(x,y):
    if x == y: return
    print(x,"!=",y)
    assert(x == y)

class IntCodeVM:
    def __init__(self, prog, inputs=list(), outputs=list()):
        self.prog = prog[:] # copy to avoid side effects
        self.log = list() # for debugging
        self.inputs = inputs
        self.outputs = outputs
        self.ip = 0
    def run(self, inputs):
        self.inputs = inputs[:] # copy to avoid side effects
        self.ip = 0
        self.all_steps()
        return self.outputs
    def step(self):
        self.ip = exec(self.ip, self.prog, self.inputs, self.outputs)
    def all_steps(self):
        while self.prog[self.ip] != 99:
            self.step()
    def hasHalted(self):
        return self.prog[self.ip] == 99

def read(x, prog, mode=0):
    if mode == 0: # position mode
        i = prog[x]
        assert 0 <= i, i
        assert len(prog) > i, (i, prog)
        #print("RRR", i, prog)
        return prog[i]
    elif mode == 1: # immediate mode
        assert len(prog) > x, (x, prog)
        return prog[x]
    else: assert(False)
assert_eq(read(1, [4,3,2,1], mode=0), 1)
assert_eq(read(2, [4,3,2,1], mode=0), 2)
assert_eq(read(1, [4,3,2,1], mode=1), 3)

def write(x, val, prog, mode=0):
    if mode == 0: # position mode
        i = prog[x]
        assert i >= 0, i
        assert len(prog) > i, (i, prog)
        #print("write", i, val)
        prog[i] = val
    elif mode == 1: # immediate mode
        #print("write", x, val)
        prog[x] = val
    else: assert(False)
# testing
prog = [5,4,3,2,1]
write(1, 2, prog, mode=0)
assert_eq(prog[4], 2)
write(1, 2, prog, mode=1)
assert_eq(prog[1], 2)

def op_parse(num):
    op = num % 100
    m = num // 100
    modes = [0,0,0]
    modes[0] = m % 10
    m = m // 10
    modes[1] = m % 10
    m = m // 10
    modes[2] = m % 10
    return op, modes
assert_eq((2, [1,0,1]), op_parse(10102))

def exec(pos,prog,inputs,outputs):
    op, modes = op_parse(prog[pos])
    #print(". pos=", pos, "op=", op, modes, "prog[pos..]=", prog[pos:pos+4])
    if op == 1: # add
        x = read(pos+1, prog, modes[0])
        y = read(pos+2, prog, modes[1])
        write(pos+3, x+y, prog, modes[2])
        return pos+4
    elif op == 2: # multiply
        x = read(pos+1, prog, modes[0])
        y = read(pos+2, prog, modes[1])
        write(pos+3, x*y, prog, modes[2])
        return pos+4
    elif op == 3: # input
        write(pos+1, inputs.pop(0), prog, modes[2])
        return pos+2
    elif op == 4: # output
        val = read(pos+1, prog, modes[2])
        #print("output val=", val)
        outputs.append(val)
        return pos+2
    elif op == 5: # jump-if-true
        x = read(pos+1, prog, modes[0])
        if x == 0:
            return pos+3
        y = read(pos+2, prog, modes[1])
        return y
    elif op == 6: # jump-if-false
        x = read(pos+1, prog, modes[0])
        if x != 0:
            return pos+3
        y = read(pos+2, prog, modes[1])
        return y
    elif op == 7: # less-than
        x = read(pos+1, prog, modes[0])
        y = read(pos+2, prog, modes[1])
        write(pos+3, x<y, prog, modes[2])
        return pos+4
    elif op == 8: # equals
        x = read(pos+1, prog, modes[0])
        y = read(pos+2, prog, modes[1])
        write(pos+3, x==y, prog, modes[2])
        return pos+4
    elif op == 99: # halt
        return pos # noop
    else:
        #print("Error: op=%d at pos %d in:" % (op, pos), prog)
        assert(False)

vm = IntCodeVM([3,0,4,0,99])
outputs = vm.run([42])
assert outputs == [42]
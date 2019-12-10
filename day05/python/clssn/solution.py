

class IntcodeComputer:
    def __init__(self, mem):
        self.m = mem
        self.pc = 0

    def compute(self, input=[]):
        output=[]
        class Param:
            def __init__(self, m, addr, mode):
                self.m = m
                self.addr = addr
                self.mode = mode
            
            def get(self):
                if self.mode:
                    return self.m[self.addr]
                else:
                    return self.m[self.m[self.addr]]

            def set(self, v):
                if self.mode:
                    self.m[self.addr] = v
                else:
                    self.m[self.m[self.addr]] = v

        def add(a, b, r):
            r.set(a.get() + b.get())
        
        def mul(a, b, r):
            r.set(a.get() * b.get())

        def inp(a):
            a.set(input.pop(0))

        def outp(a):
            output.append(a.get())

        def jnz(a, b):
            if a.get():
                self.pc = b.get()
        
        def jz(a, b):
            if not a.get():
                self.pc = b.get()
        
        def lt(a, b, r):
            r.set(1 if a.get() < b.get() else 0)
        
        def eq(a, b, r):
            r.set(1 if a.get() == b.get() else 0)

        op = { 1: add, 2: mul, 3: inp, 4: outp, 5: jnz, 6: jz, 7: lt, 8: eq}
        while True:
            pc = self.pc
            instr = self.m[pc]
            o = instr % 100
            if o == 99:
                break
            n = op[o].__code__.co_argcount
            mode = [ instr//(10**(i+2))%10 for i in range(0, n) ]
            par = [ Param(self.m, pc+1+i, mode[i]) for i in range(0, n)]
            op[o](*par)
            if pc == self.pc:
                self.pc += 1+n
        return output

if __name__ == "__main__":

    input = []
    with open('input.txt') as f:
        for line in f:
            input += [ int(x) for x in line.strip().split(',') ]

    def comp(inp):
        c = IntcodeComputer(list(input))
        return c.compute(inp)

    print("Result first star: {}".format(comp([1])))
    print("Result second star: {}".format(comp([5])))
    

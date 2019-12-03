input = []
with open('input.txt') as f:
    for line in f:
        input += [ int(x) for x in line.strip().split(',') ]

op = { 1: lambda a,b: a+b, 2: lambda a,b: a*b}

def solve(m, noun, verb):
    m[1] = noun
    m[2] = verb

    pc = 0
    while True:
        o, a, b, d = m[pc:pc+4]
        if o == 99:
            break
        r = op[o](m[a], m[b])
        m[d] = r
        pc += 4
    return m[0]

print("Result first star: {}".format(solve(list(input),12,2)))

for noun in range(0, 99):
    for verb in range(0, 99):
        try:
            r = solve(list(input), noun, verb)
        except(KeyError):
            pass
        if r == 19690720:
            print("Result second star: {}".format(noun * 100 + verb))
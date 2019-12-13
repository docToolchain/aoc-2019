import numpy

class Moon:
    def __init__(self, x, y, z):
        self.p = [x, y, z]
        self.v = [0, 0, 0]

    def move(self):
        p = self.p
        v = self.v
        self.p = [p[0]+v[0], p[1]+v[1], p[2]+v[2]] 
    
    def energy(self):
        p = self.p
        v = self.v
        return (abs(p[0])+abs(p[1])+abs(p[2]))*(abs(v[0])+abs(v[1])+abs(v[2]))

def applyGravity(m1, m2):
    if m1.p[0] < m2.p[0]:
        m1.v[0] += 1
        m2.v[0] -= 1
    elif m1.p[0] > m2.p[0]:
        m1.v[0] -= 1
        m2.v[0] += 1
    if m1.p[1] < m2.p[1]:
        m1.v[1] += 1
        m2.v[1] -= 1
    elif m1.p[1] > m2.p[1]:
        m1.v[1] -= 1
        m2.v[1] += 1
    if m1.p[2] < m2.p[2]:
        m1.v[2] += 1
        m2.v[2] -= 1
    elif m1.p[2] > m2.p[2]:
        m1.v[2] -= 1
        m2.v[2] += 1

def generateString(moons, d):
    string = ""
    for i in range(len(moons)):
        string += str(moons[i].p[d])+str(moons[i].v[d])
    return string

#def lcm(a, b, c):
#    if a > b and a > c:
#        x = a
#        y = b
#        z = c
#    elif b > a and b > c:
#        x = b
#        y = a
#        z = c
#    elif c > a and c > b:
#        x = c
#        y = a
#        z = b
#    else:
#        print("error in lcm")
#        return 0
#    lcm = x
#    while 1:
#        if not lcm % x and not lcm % y and not lcm % z:
#            break
#        else:
#            lcm += x
#    return lcm

moons = []
moons.append(Moon(-3, 10, -1))
moons.append(Moon(-12, -10, -5))
moons.append(Moon(-9, 0, 10))
moons.append(Moon(7, -5, -3))
t = 0
Xperiod = 0
Yperiod = 0
Zperiod = 0
Xinitial = generateString(moons, 0)
Yinitial = generateString(moons, 1)
Zinitial = generateString(moons, 2)
while 1:
    for i in range(len(moons)):
        for j in range(len(moons)):
            if i >= j:
                continue
            applyGravity(moons[i], moons[j])
        moons[i].move()
    t += 1
    newXString = generateString(moons, 0)
    newYString = generateString(moons, 1)
    newZString = generateString(moons, 2)
    if newXString == Xinitial:
        Xperiod = t
    if newYString == Yinitial:
        Yperiod = t
    if newZString == Zinitial:
        Zperiod = t
    if Xperiod and Yperiod and Zperiod:
        print("found periods in all three axes: {}, {} and {}".format(Xperiod, Yperiod, Zperiod))
        break
    if t == 1000:
        energy = 0
        for i in range(len(moons)):
            energy += moons[i].energy()
        print("star 1:"+str(energy))
print("star2 (LCM of x, y and z periods): "+str(numpy.lcm(numpy.lcm(Xperiod, Yperiod), Zperiod)))
